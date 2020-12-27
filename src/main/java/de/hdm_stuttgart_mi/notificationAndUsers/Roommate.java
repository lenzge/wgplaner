package de.hdm_stuttgart_mi.notificationAndUsers;

import java.time.LocalDate;


public class Roommate {
    private String firstname;
    private String lastname;
    private int ID;
    private String phonenumber;
    private  boolean current;
    private LocalDate moveInDate;
    private LocalDate birthday;
    private String profilepic;

    public Roommate(String firstname, String lastname, int ID, String phonenumber, boolean current, LocalDate moveInDate, LocalDate birthday,String profilepic){
        this.firstname= firstname;
        this.lastname=lastname;
        this.ID=ID;
        this.phonenumber=phonenumber;
        this.current=current;
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

    public boolean isCurrent() {
        return current;
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
        this.phonenumber = phonenumber;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setProfilepic(String profilepicURL) {
        this.profilepic = profilepicURL;
    }
}
