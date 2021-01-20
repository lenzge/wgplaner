package de.hdm_stuttgart_mi.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExternMethods {

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
       return phoneNumber.matches("^(\\d+)$");
    }
    public static boolean validName(String name){
        return name != null && !(name.equals("")) && name.matches("([a-zA-Z]+)");
    }
    public static boolean validPassword(String password){
        return password != null && !(password.equals("")) && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
                                                                            //min 1 number, min 1 lower/min 1 upper alphabet/no whitespaces/at least 8 characters
    }
}
