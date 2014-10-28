package cl.lerolero.libreria;

import android.content.Context;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserFunctions {

    private JSONParser jsonParser;
    //http://daniel.arroyo.cl/android/
    private static String loginURL = "http://lerolero.cl/app/";
    private static String registerURL = "http://lerolero.cl/app/";
    private static String commentURL = "http://lerolero.cl/app/";
    private static String getcommentURL = "http://lerolero.cl/app/";

    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String comment_tag = "comment";
    private static String getcomment_tag = "getcomment";
    private static String getbanks_tag = "getbanks";

    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }

    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }

    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }

    public JSONObject registerComment(String comment, String email ,String emotion, String latitude, String longitude, String sucursal){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", comment_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("emotion", emotion));
        params.add(new BasicNameValuePair("comment", comment));
        params.add(new BasicNameValuePair("latitude", latitude));
        params.add(new BasicNameValuePair("longitude", longitude));
        params.add(new BasicNameValuePair("sucursal", sucursal));

        JSONObject json = jsonParser.getJSONFromUrl(commentURL, params);
        //Log.e("JSON", json.toString());
        return json;
    }
    public JSONObject getBanks(){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getbanks_tag));

        JSONObject json = jsonParser.getJSONFromUrl(commentURL, params);
        return json;
    }
    public JSONObject getComments(String sucursal){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag",getcomment_tag));
        params.add(new BasicNameValuePair("sucursal",sucursal));

        JSONObject json = jsonParser.getJSONFromUrl(getcommentURL, params);

        return json;
    }

    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
    //Conocer el correo del usuario
    public String getUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        HashMap<String, String> source = new HashMap<String, String>();
        source = db.getUserDetails();
        String email;
        email = source.get("email");

        return email;

    }




    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }

}
