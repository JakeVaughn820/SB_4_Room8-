package edu.iastate.room8.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.iastate.room8.HomeActivity;
import edu.iastate.room8.LoginActivity;
import edu.iastate.room8.NewUserRoomJoin;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String ROOM = "ROOM";
    private static final String ROOMS = "ROOMS";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //Creates a new session with everything set to null other than the params.
    public void createSession(String name, String email, String id){

        Set<String> set = new HashSet<>();
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.putString(ROOM, null);
        editor.putStringSet(ROOMS, set);
        editor.apply();
    }

    //Adds a room to ROOMS and sets it as current room.
    public void addRoom (String room){
        Set<String> set;
        set = (sharedPreferences.getStringSet(ROOMS, null));
        set.add(room);
        editor.putStringSet(ROOMS, set);
        editor.putString(ROOM, room);
        editor.apply();
    }

    //True if user is currently in a room false if otherwise
    public boolean isRoom (String room){
        Set<String> set;
        set = (sharedPreferences.getStringSet(ROOMS, null));

        return set.contains(room);
    }

    //Sets the current room the user is in.
    public void setRoom (String room){
        if(isRoom(room)){
            editor.putString(ROOM, room);
            editor.apply();
        }
    }

    //Returns true if the user is in a room false if otherwise
    public boolean isInRoom(){
        if (sharedPreferences.getString(ROOM, null) != null){
            return true;
        }
        return false;
    }

    //Checks Room to see if the user is in a room if not it puts user on NewUserRoomJoin
    public void checkRoom(){
        if (!this.isInRoom()){
            Intent i = new Intent(context, NewUserRoomJoin.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    //Returns current room if user is in room
    public String getRoom(){
        return sharedPreferences.getString(ROOM, null);
    }

    //Returns all the rooms the user is a part of.
    public Set<String> getRooms(){
        return sharedPreferences.getStringSet(ROOMS, null);
    }

    //Returns the name of the user.
    public String getName(){
        return sharedPreferences.getString(NAME, null);
    }

    //Returns the Email of the user.
    public String getEmail(){
        return sharedPreferences.getString(EMAIL, null);
    }

    //Returns the ID of the user.
    public String getID(){
        return sharedPreferences.getString(ID, null);
    }

    //Returns true if the user is logged in false if otherwise.
    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    //Checks if the user is logged in if they are not logged in go to LoginActivity
    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    //Returns all of the users Details as a HashMap<"Keyword", "Thing">
    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(ROOM, sharedPreferences.getString(ROOM, null));

        return user;
    }

    //Leaves the current room and switches screen to NewUserRoomJoin.
    public void leaveRoom(){
        editor.putString(ROOM, null);
        editor.apply();
        Intent i = new Intent(context, NewUserRoomJoin.class);
        context.startActivity(i);
        ((HomeActivity) context).finish();
    }

    //Logs out of the session clearing all of the users data from shared preferences.
    public void logout(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((HomeActivity) context).finish();

    }

}