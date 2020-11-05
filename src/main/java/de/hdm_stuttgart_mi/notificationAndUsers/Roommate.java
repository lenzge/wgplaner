package de.hdm_stuttgart_mi.notificationAndUsers;

public class Roommate {

    static CurrentRoommate currentRoommateList[];
    static FormerRoommate formerRoommateList[];
    public static CurrentRoommate getCurrent(int i){
        return currentRoommateList[i];
    }
    public static FormerRoommate getFormer(int i){
        return formerRoommateList[i];
    }
    public static void addCurrentRoomate(CurrentRoommate newMate){

    }
}
