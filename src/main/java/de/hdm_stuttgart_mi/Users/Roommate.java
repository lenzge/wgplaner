package de.hdm_stuttgart_mi.Users;

import java.time.LocalDate;

import static de.hdm_stuttgart_mi.Controller.ExternMethods.validPassword;
import static de.hdm_stuttgart_mi.Controller.ExternMethods.validPhoneNumber;


public class Roommate {
    private String firstname;
    private String lastname;
    private int ID;
    private String phonenumber;
    private String password;
    private LocalDate moveInDate;
    private LocalDate birthday;
    private String profilepic;

    public Roommate(String firstname, String lastname, int ID, String phonenumber, String password, LocalDate moveInDate, LocalDate birthday, String profilepic){
        this.firstname= firstname;
        this.lastname=lastname;
        this.ID=ID;
        this.phonenumber=phonenumber;
        this.password = password;
        this.moveInDate=moveInDate;
        this.birthday=birthday;
        this.profilepic= profilepic;
    }
    @Override
    public String toString(){
        return   firstname + " "+ lastname+" zog am " + moveInDate + " ein ";
    }


    //getter
    public String getProfilepic() {
        return profilepic;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getID() {
        return ID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getMoveInDate() {
        return moveInDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    //Grüße von Lena, sorry, dass ich hier in deine Klasse schreibe, brauche die Methode für mich xD
    public String getFullname() {return firstname + " " + lastname;}

    //setter

    public void setPhonenumber(String phonenumber) {
        if (validPhoneNumber(phonenumber)) {
            this.phonenumber = phonenumber;
        }
    }


    protected void setID(int ID) {
        this.ID = ID;
    }

    public void changePassword(String password){
        if(validPassword(password)){
            this.password=password;
        }
    }

    public void setProfilepic(String profilepicURL) {
        this.profilepic = profilepicURL;
    }
}
