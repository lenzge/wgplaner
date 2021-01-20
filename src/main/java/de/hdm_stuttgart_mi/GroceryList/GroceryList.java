package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.Users.Roommate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class GroceryList {

    //Logger
    private static final Logger log = LogManager.getLogger(GroceryList.class);

    //Global collection
    List<Iitem> itemList;

    //Date Format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //JSON File
    File file = new File("src\\main\\resources\\JSON\\items.json");


    public List<Iitem> getItemList() { return itemList; }
    public GroceryList() {
        log.debug("try to initialize groceryList");
        initItems();

    }

    //When the Program is started, or the list is updated, every Item from the JSON file has to be written into the collection
    public void initItems(){
        itemList = new ArrayList<Iitem>();
        JSONParser parser = new JSONParser();

        try{
            String jsonContent = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            log.debug("read JSONFile");
            JSONObject json = (JSONObject) parser.parse(jsonContent);

            JSONArray jsonArray = (JSONArray) json.get("items");
            for (Object o : jsonArray) {
                JSONObject tempJasonObject = (JSONObject) o;

                String type = (String) tempJasonObject.get("type");
                String content = (String) tempJasonObject.get("content");
                String author = (String) tempJasonObject.get("author");

                //non-bought items
                if (tempJasonObject.get("price") == null) {
                    Iitem item = ItemFactory.getInstance(type, content, author);
                    itemList.add(0,item); //non-bought items should be on top of the list
                    log.debug("non-bought item: " + item.toString());
                }
                //items, which are older than two weeks will get deleted
                else if (LocalDate.now().minusWeeks(2).compareTo(LocalDate.parse((String) tempJasonObject.get("boughtDate"), formatter))> 0){ }
                //bought items
                else {
                    String price = (String) tempJasonObject.get("price");
                    LocalDate boughtDate = LocalDate.parse((String) tempJasonObject.get("boughtDate"), formatter);
                    String boughtBy = (String) tempJasonObject.get("boughtBy");
                    Iitem item = ItemFactory.getInstance(type, content, author, price, boughtDate, boughtBy);
                    itemList.add(item);
                    log.debug("bought item: " + item.toString());
                }

            }
            log.info("Grocerylist initialized");

        }
        catch (IOException e ) {
            e.printStackTrace();
            log.error("IOException");

        }
        catch (ParseException e) {
            e.printStackTrace();
            log.error("ParseException");

        }

    }

    //When the list gets updated, the ItemList has to be safed in the JSONFile
    public void safeItems()  {
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        for (Iitem item : itemList) {
            JSONObject innerObj = new JSONObject();
            innerObj.put("type", item.getType());
            innerObj.put("content", item.getContent());
            innerObj.put("author", item.getAuthor());
            innerObj.put("price", item.getPrice());
            if (item.getBoughtDate() != null) innerObj.put("boughtDate", item.getBoughtDate().format(formatter));
            else innerObj.put("boughtDate", item.getBoughtDate());
            innerObj.put("boughtBy", item.getBoughtBy());
            list.add(innerObj);
        }
        obj.put("items", list);

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(obj.toJSONString());
            log.info("Items safed");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException");
        }

    }


    //edit itemList

    public void addItem(String type, String content, Roommate author){
        Iitem item = ItemFactory.getInstance(type, content, author.getFullname());
        itemList.add(item);
        log.info(item.toString() + " added");
    }

    public void deleteItem(Iitem item){
        itemList.removeIf(value -> value.getContent().equals(item.getContent()));
        log.info(item.toString() + " deleted");
    }

    public void boughtItem (Iitem item, String price, Roommate boughtBy){
        for (Iitem value : itemList) {
            if (value.getContent().equals(item.getContent())) {
                value.boughtItem(price, LocalDate.now(), boughtBy.getFullname());
            }
        }
        log.info(item.toString() + " bought");
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Iitem item : itemList) {
            sb.append(item.getType()).append(", ");
            sb.append(item.getContent()).append(", ");
            sb.append(item.getAuthor()).append(", ");
            sb.append(item.getPrice()).append(", ");
            sb.append(item.getBoughtDate()).append(", ");
            sb.append(item.getBoughtBy()).append("\n");
        }
        return sb.toString();
    }
}
