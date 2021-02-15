package de.hdm_stuttgart_mi.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExternMethods {
    private static final Logger log = LogManager.getRootLogger();
  /**
     ImageView filler and creater
   */

    public static ImageView getImageView(String imagePath,double width, double height) throws FileNotFoundException {

        Image image = new Image(new FileInputStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
    public static ImageView fillImageView(ImageView imageView, String imagePath,double width, double height) throws FileNotFoundException {

        Image image = new Image(new FileInputStream(imagePath));
        imageView.setImage(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
    /**
     * Validator
     */
    public static boolean validPhoneNumber(String phoneNumber){
        if(phoneNumber.matches("^(\\d+)$")){
         return true;
        }
        else {
            log.info("no valid Phonenumber");
            return false;
        }
    }
    public static boolean validName(String name){
        if(name != null && !(name.equals("")) && name.matches("([a-zA-Z]+)")){
            return true;
        }
        else {
            log.info("no valid name");
            return false;
        }
    }

    public static boolean validPassword(String password){
        if(password != null && !(password.equals("")) && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$")) //min 1 number, min 1 lower/min 1 upper alphabet/no whitespaces/at least 8 characters
        {
            return true;
        }
        else {
            log.info("no valid password");
            return false;
        }


    }
}
