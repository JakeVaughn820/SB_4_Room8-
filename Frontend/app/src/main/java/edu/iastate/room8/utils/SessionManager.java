package edu.iastate.room8.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import edu.iastate.room8.HomeActivity;
import edu.iastate.room8.LoginActivity;
import edu.iastate.room8.NewRoomActivity;
import edu.iastate.room8.NewUserRoomJoin;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String ROOM = "ROOM";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name, String email, String id){

        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.putString(ROOM, null);
        editor.apply();
    }

    public void setRoom (String room){
        editor.putString(ROOM, room);
        editor.apply();
    }

    public boolean isInRoom(){
        if (sharedPreferences.getString(ROOM, null) != null){
            return true;
        }
        return false;
    }

    public void checkRoom(){
        if (!this.isInRoom()){
            //TODO this line below pulls it to the wrong room, should be changed to NewUserRoomJoin. I will make a logout in the NewUserRoomJoin
            //TODO then push and then go to bed
            Intent i = new Intent(context, NewUserRoomJoin.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin(){

        if (!this.isLoggin()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(ROOM, sharedPreferences.getString(ROOM, null));

        return user;
    }

    public void logout(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((HomeActivity) context).finish();

    }

}