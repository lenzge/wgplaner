package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.ItemFactory.ItemFactory;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class GroceryList {

    //Collection
    private static List<Item> itemList = new ArrayList<Item>();

    //Date Format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    //JSON File
    File file = new File("src\\main\\resources\\JSON\\items.json");


    //When the Program is started, every Item from the JSON file has to be written into the collection
    public void initItems(){
        JSONParser parser = new JSONParser();

        try{
            String json_content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = (JSONObject) parser.parse(json_content);

            JSONArray jsonArray = (JSONArray) json.get("items");
            for(int i=0; i<jsonArray.size();i++){
                JSONObject tempJasonObject = (JSONObject) jsonArray.get(i);

                String type = (String) tempJasonObject.get("type");
                String content = (String) tempJasonObject.get("content");
                String author = (String) tempJasonObject.get("author");


                if (tempJasonObject.get("price") == null){
                    Iitem Item = ItemFactory.getInstance(type, content, author);
                }
                else {
                    long price = (Long) tempJasonObject.get("price");
                    LocalDate boughtDate = LocalDate.parse((String) tempJasonObject.get("boughtDate"), formatter);
                    String boughtBy = (String) tempJasonObject.get("boughtBy");
                    Iitem Item = ItemFactory.getInstance(type, content, author, price, boughtDate, boughtBy);
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
    public void safeItems() throws IOException, ParseException {
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        for (Item item : itemList) {
            JSONObject innerObj = new JSONObject();
            innerObj.put("type", item.getType());
            innerObj.put("content", item.getContent());
                    //" \"author\":\"" + item.getAuthor() + "\",\n" +
                    //" \"price\":\"" + item.getPrice() + "\",\n" +
                    //" \"boughtDate\":\"" + item.getBoughtDate() + "\",\n" +
                    //" \"boughtBy\":\"" + item.getBoughtBy() + "\" \n }, \n");
            list.add(innerObj);
        }
        obj.put("items", list);

        FileWriter writer = new FileWriter(file);
        writer.write(obj.toJSONString());



    }

    public List<Item> getItemList() {
        return itemList;
    }

    public static void putOnList(Item item){
        itemList.add(item);
    }

    public GroceryList() { }

    public void boughtItem (Item item, int price, Roommate boughtBy){
        for (Item value : itemList) {
            if (value.getContent().equals(item.getContent())) {
                value.setPrice(price);
                value.setBoughtBy(boughtBy.getFullname());
                value.setBoughtDate(LocalDate.now());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemList) {
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
