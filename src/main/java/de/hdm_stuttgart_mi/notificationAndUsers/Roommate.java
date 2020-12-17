package de.hdm_stuttgart_mi.notificationAndUsers;

import java.util.Date;


public class Roommate {
    private String firstname;
    private String lastname;
    private int ID;
    private String phonenumber;
    private  boolean current;
    private Date moveInDate;
    private Date birthday;
    private String profilepic;

    public Roommate(String firstname, String lastname, int ID, String phonenumber, boolean current, Date moveInDate, Date birthday,String profilepic){
        this.firstname= firstname;
        this.lastname=lastname;
        this.ID=ID;
        this.phonenumber=phonenumber;
        this.current=current;
        this.moveInDate=moveInDate;
        this.birthday=birthday;
        this.profilepic= profilepic;
    }
    public Roommate(){}
    @Override
    public String toString(){
        return   firstname + " "+ lastname+" zog am " + moveInDate + " ein";
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

    public Date getMoveInDate() {
        return moveInDate;
    }

    public Date getBirthday() {
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setProfilepic(String profilepicURL) {
        this.profilepic = profilepicURL;
    }
}
