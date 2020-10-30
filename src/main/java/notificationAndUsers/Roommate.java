package notificationAndUsers;

import java.util.Date;

public class Roommate {
    private String firstname;
    private String lastname;
    private int ID;
    private String phonenumber;
    private  boolean current;
    private Date moveInDate;
    private Date birthday;

    public int getID(){
        return ID;
    }
    @Override
    public String toString(){
        return firstname+" "+ lastname;
    }
}
