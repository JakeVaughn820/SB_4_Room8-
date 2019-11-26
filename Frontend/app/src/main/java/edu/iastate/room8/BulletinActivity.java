package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;

import edu.iastate.room8.utils.SessionManager;

/**
 * This class is used for the activity of the bulletin feature. Send important messages to your roommates.
 * @author Paul Degnan
 * @author Jake Vaughn
 */
public class BulletinActivity extends AppCompatActivity {
    /**
     * text view that shows the room's bulletin
     */
    private TextView textView;
    /**
     * text the user inputs to be added to bulletin
     */
    private EditText toAddText;
    /**
     * ArrayList that holds all of bulletin entries
     */
    private ArrayList<String> arr;
    /**
     * String with the text to be added
     */
    private String stringToAddText;
    /**
     * mWebSocketClient used for connecting websocket to server.
     */
    private WebSocketClient mWebSocketClient;
    /**
     * session manager used for settings and information for the specific user
     */
    SessionManager sessionManager;
    /**
     * Another web socket client
     */
    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        sessionManager = new SessionManager(this);

        textView = findViewById(R.id.textView);
        Button toAddButton = findViewById(R.id.buttonForAdd);
        toAddText = findViewById(R.id.messageToAdd);
        arr = new ArrayList<>();

        if(sessionManager.getPermission().equals("Viewer")){
            toAddButton.setVisibility(View.INVISIBLE);
            toAddText.setVisibility(View.INVISIBLE);
        }else{
            toAddButton.setVisibility(View.VISIBLE);
            toAddText.setVisibility(View.VISIBLE);
        }

        toAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringToAddText = toAddText.getText().toString();
                if(stringToAddText.equals("")){
                    Toast.makeText(BulletinActivity.this, "Must input a message to display on the bulletin board", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        cc.send(toAddText.getText().toString());
                    }
                    catch (Exception e)
                    {
                        Log.d("ExceptionSendMessage:", "Exception in Web Sockets");
                    }

                }
                toAddText.setText("");
            }
        });

        webSocketWithBackend();
    }

    /**
     * Method that will make sure the messages wont go off the screen.
     * Basically appends here instead of in the parse method
     */
    private void appendTextView(){
        textView.setText("");
        int numMessagesToShow = 50; //CHANGE THIS NUMBER FOR AMOUNT OF MESSAGES TO SHOW UP
        int temp = arr.size()-numMessagesToShow;
        if(temp<0){
            temp=0;
        }
        ArrayList<String> tempArrayList = reverseArrayList(arr);
        for(int i = temp; i<tempArrayList.size(); i++){
            textView.append(tempArrayList.get(i));
        }
    }

    /**
     * Method that reverses an ArrayList. Called by appendTextView so that the first message on top of the screen
     * is the most recent and you can scroll down to older messages.
     * @param arr1 the ArrayList to reverse
     * @return a reverse of the ArrayList received
     */
    private ArrayList<String> reverseArrayList(ArrayList<String> arr1)
    {
        ArrayList<String> reverse = new ArrayList<>();
        for (int i = arr1.size() - 1; i >= 0; i--) {
            reverse.add(arr1.get(i));
        }
        return reverse;
    }

    /**
     * Connects to web sockets for bulletin
     */
    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("wss://echo.websocket.org");
        } catch (
                URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                //mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.textView);
                        String temp = textView.getText() + "\n" + message;
                        textView.setText(temp);
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    /**
     * Sends the message to the web socket
     * @param view view used
     */
    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.messageToAdd);
        mWebSocketClient.send(sessionManager.getName() + ": " + editText.getText().toString());
        editText.setText("");
    }

    /**
     * Web socket that was created to be used with the backend
     */
    private void webSocketWithBackend(){
        Draft[] drafts = {new Draft_6455()};
        String w = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
        w = w + "/" + sessionManager.getName();
        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s=textView.getText().toString();

                    String messageTemp = message + "\n";
                    String toSet = messageTemp+s;
                    textView.setText(toSet);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", "URISyntaxException");
            e.printStackTrace();
        }
        cc.connect();
    }
}
