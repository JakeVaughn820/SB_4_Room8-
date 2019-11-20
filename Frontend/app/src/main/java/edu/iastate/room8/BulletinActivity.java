package edu.iastate.room8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import edu.iastate.room8.app.AppController;
import edu.iastate.room8.utils.JsonParser;
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
     * request queue
     */
    private RequestQueue mQueue;
    /**
     * when clicked will add message to bulletin
     */
    private Button toAddButton;
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
     * TAG used for the class the request came from
     */
    private String TAG = NewListActivity.class.getSimpleName();
    /**
     * tag for json's object or json array requests
     */
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    /**
     * mWebSocketClient used for connecting websocket to server.
     */
    private WebSocketClient mWebSocketClient;
    /**
     * session manager used for settings and information for the specific user
     */
    SessionManager sessionManager;
//    /**
//     * Button to connect to server
//     */
//    private Button buttonConnect;
//    /**
//     * Name to connect to server
//     */
//    private EditText editTextConnect;
    /**
     * Another web socket client
     */
    private WebSocketClient cc;
//TODO maybe try and make each person color coded?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        sessionManager = new SessionManager(this);
        //connectWebSocket();

        mQueue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.textView);
        toAddButton = findViewById(R.id.buttonForAdd);
        toAddText = findViewById(R.id.messageToAdd);
//        buttonConnect = findViewById(R.id.buttonConnect);
//        editTextConnect = findViewById(R.id.editTextConnect);
        arr = new ArrayList<String>();

        if(sessionManager.getPermission().equals("Viewer")){
            toAddButton.setVisibility(View.INVISIBLE);
            toAddText.setVisibility(View.INVISIBLE);
        }else{
            toAddButton.setVisibility(View.VISIBLE);
            toAddText.setVisibility(View.VISIBLE);
        }


//        try{
//            jsonParse();  //Parses through the json given to frontend from back end
//        }catch(JSONException e){
//            textView.setText("Something went wrong!>!>!>");
//            e.printStackTrace();
//        }

        toAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringToAddText = toAddText.getText().toString();
                if(stringToAddText.equals("")){
                    Toast.makeText(BulletinActivity.this, "Must input a message to display on the bulletin board", Toast.LENGTH_LONG).show();
                }else{
                    //postRequest();
//                    sendMessage(view);
                    try {
                        cc.send(toAddText.getText().toString());
                    }
                    catch (Exception e)
                    {
                        Log.d("ExceptionSendMessage:", e.getMessage().toString());
                    }

                }
                toAddText.setText("");
            }
        });

        webSocketWithBackend();
//        buttonConnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Draft[] drafts = {new Draft_6455()};
//
//                /**
//                 * If running this on an android device, make sure it is on the same network as your
//                 * computer, and change the ip address to that of your computer.
//                 * If running on the emulator, you can use localhost.
//                 */
////                String w = "ws://10.26.13.93:8080/websocket/"+editTextConnect.getText().toString();
//                String w = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
//                w = w + "/" + sessionManager.getName();
//                try {
//                    Log.d("Socket:", "Trying socket");
//                    cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
//                        @Override
//                        public void onMessage(String message) {
//                            Log.d("", "run() returned: " + message);
//                            String s=textView.getText().toString();
//                            //t1.setText("hello world");
//                            //Log.d("first", "run() returned: " + s);
//                            //s=t1.getText().toString();
//                            //Log.d("second", "run() returned: " + s);
//                            String messageTemp = message + "\n";
//                            textView.setText(s+messageTemp);
//                        }
//
//                        @Override
//                        public void onOpen(ServerHandshake handshake) {
//                            Log.d("OPEN", "run() returned: " + "is connecting");
//                        }
//
//                        @Override
//                        public void onClose(int code, String reason, boolean remote) {
//                            Log.d("CLOSE", "onClose() returned: " + reason);
//                        }
//
//                        @Override
//                        public void onError(Exception e)
//                        {
//                            Log.d("Exception:", e.toString());
//                        }
//                    };
//                }
//                catch (URISyntaxException e) {
//                    Log.d("Exception:", e.getMessage().toString());
//                    e.printStackTrace();
//                }
//                cc.connect();
//
//            }
//        });
    }

//    /**
//     * Used to parse JSON Objects in BulletinActivity
//     * Will get the chats from each person in the room and display them
//     * Receives: Header: BulletinBoard. Keys: User. Contents.
//     * @throws JSONException
//     */
//    public void jsonParse() throws JSONException {
//        String url = "https://api.myjson.com/bins/1g4fnt";
//        String url = "https://api.myjson.com/bins/k7wvo";
        String url = "https://api.myjson.com/bins/158r6c";
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/bulletin";
//        url = url + "/" + sessionManager.getRoom();
//        JSONObject json = jsonParser.jsonParse(url);
//        JSONArray jsonArray = json.getJSONArray("BulletinBoard");
//
//        for (int i = 0; i < jsonArray.length(); i++){
//            JSONObject List = jsonArray.getJSONObject(i);
//            String id = List.getString("User");
//            String contents = List.getString("Contents");
//            textView.append(Html.fromHtml("<b>"+ id + ": </b>"));
//            textView.append(contents + "\n");
//        }
//        mQueue.add(jsonParser.returnRequest());
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("BulletinBoard");
//
//                            for (int i = 0; i < jsonArray.length(); i++){
//                                JSONObject List = jsonArray.getJSONObject(i);
//
//                                String id = List.getString("User");
//                                String contents = List.getString("Contents");
////                                textView.append(Html.fromHtml("<b>"+ id + ": </b>"));
////                                textView.append(contents + "\n");
//                                String temp = Html.fromHtml("<b>"+ id + ": </b>") + contents + "\n";
//                                arr.add(temp);
//                                appendTextView();
//                            }
//                        } catch (JSONException e) {
//                            textView.setText("Something went wrong!>!>!>");//yml
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mQueue.add(request);
//    }
//
//
//    /**
//     * Used to send a message to the server to put onto the bulletin
//     * Sends information with keys: User, Contents.
//     */
//    private void postRequest() {
//        String url = "http://coms-309-sb-4.misc.iastate.edu:8080/bulletin";
//        url = url + "/" + sessionManager.getRoom();
//
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("User", sessionManager.getName());
//        params.put("Contents", stringToAddText);
//        JSONObject toPost = new JSONObject(params);
////        Toast.makeText(this, toPost.toString(), Toast.LENGTH_SHORT).show();
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, toPost,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("User", "User");
//                params.put("Contents", stringToAddText);
//
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
//    }

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
        ArrayList<String> reverse = new ArrayList<String>();
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
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(textView.getText() + "\n" + message);
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
     * @param view
     */
    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.messageToAdd);
        mWebSocketClient.send(sessionManager.getName() + ": " + editText.getText().toString());
        editText.setText("");
    }

    /**
     * Web socket that was created to be used with the backend
     */
    public void webSocketWithBackend(){
        Draft[] drafts = {new Draft_6455()};
        String w = "http://coms-309-sb-4.misc.iastate.edu:8080/room";
        w = w + "/" + sessionManager.getName();
        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s=textView.getText().toString();

                    String messageTemp = message + "\n";
                    textView.setText(messageTemp+s);
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
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();
    }
}
