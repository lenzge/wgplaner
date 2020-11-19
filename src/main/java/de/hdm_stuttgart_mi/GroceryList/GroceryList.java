package de.hdm_stuttgart_mi.GroceryList;
import de.hdm_stuttgart_mi.notificationAndUsers.Roommate;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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


    //When the Program is started, every Item from the JSON file has to be written into the collection
    public void initItems(){
        File file = new File("src\\main\\resources\\JSON\\items.json");
        try{
            String json_content = new String(Files.readAllBytes(Paths.get(file.toURI())),"UTF-8");
            JSONObject json = new JSONObject(json_content);

            JSONArray jsonArray = json.getJSONArray("items");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject tempJasonObject = jsonArray.getJSONObject(i);

                String content = tempJasonObject.getString("content");
                String author = (String) tempJasonObject.get("author");


                if (tempJasonObject.isNull("price")){
                    Item item = new Item(content, author);
                }
                else {
                    long price = tempJasonObject.getLong("price");
                    LocalDate boughtDate = LocalDate.parse(tempJasonObject.getString("boughtDate"), formatter);
                    String boughtBy = (String) tempJasonObject.get("boughtBy");
                    Item item = new Item(content, author, price, boughtDate, boughtBy);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //When the Program is closed, the ItemList has to be safed in the JSONFile
    public void safeItems() {


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
            sb.append(item.getContent()).append(", ");
            sb.append(item.getAuthor()).append(", ");
            sb.append(item.getPrice()).append(", ");
            sb.append(item.getBoughtDate()).append(", ");
            sb.append(item.getBoughtBy()).append("\n");
        }
        return sb.toString();
    }
}
