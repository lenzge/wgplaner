package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GroceryList {

    //Collection
    List<Iitem> itemList;

    //Date Format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //JSON File
    File file = new File("src\\main\\resources\\JSON\\items.json");


    //When the Program is started, every Item from the JSON file has to be written into the collection
    public void initItems(){
        itemList = new ArrayList<Iitem>();
        JSONParser parser = new JSONParser();

        try{
            String jsonContent = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = (JSONObject) parser.parse(jsonContent);

            JSONArray jsonArray = (JSONArray) json.get("items");
            for (Object o : jsonArray) {
                JSONObject tempJasonObject = (JSONObject) o;

                String type = (String) tempJasonObject.get("type");
                String content = (String) tempJasonObject.get("content");
                String author = (String) tempJasonObject.get("author");


                if (tempJasonObject.get("price") == null) {
                    Iitem item = ItemFactory.getInstance(type, content, author);
                    itemList.add(0,item);
                } else if (LocalDate.now().minusMonths(1).compareTo(LocalDate.parse((String) tempJasonObject.get("boughtDate"), formatter))> 0){

                }
                else {
                    String price = (String) tempJasonObject.get("price");
                    LocalDate boughtDate = LocalDate.parse((String) tempJasonObject.get("boughtDate"), formatter);
                    String boughtBy = (String) tempJasonObject.get("boughtBy");
                    Iitem item = ItemFactory.getInstance(type, content, author, price, boughtDate, boughtBy);
                    itemList.add(item);
                }

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //When the Program is closed, the ItemList has to be safed in the JSONFile
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Iitem> getItemList() {
        return itemList;
    }

    public void addItem(String type, String content, Roommate author){
        Iitem item = ItemFactory.getInstance(type, content, author.getFullname());
        itemList.add(item);
    }

    public void deleteItem(Iitem item){
        itemList.removeIf(value -> value.getContent().equals(item.getContent()));
    }

    public GroceryList() {
        initItems();
    }

    public void boughtItem (Iitem item, String price, Roommate boughtBy){
        for (Iitem value : itemList) {
            if (value.getContent().equals(item.getContent())) {
                value.boughtItem(price, LocalDate.now(), boughtBy.getFullname());
            }
        }
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
