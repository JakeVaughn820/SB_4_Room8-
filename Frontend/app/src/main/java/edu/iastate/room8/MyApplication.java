package edu.iastate.room8;

import android.app.Application;

public class MyApplication extends Application {

    private String userID;
    private String userName;
    private String roomID;

    public String getUserID() {
        return userID;
    }

    public String getUserName(){
        return userName;
    }

    public String getRoomID(){
        return roomID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setRoomID(String roomID){
        this.roomID = roomID;
    }
}
