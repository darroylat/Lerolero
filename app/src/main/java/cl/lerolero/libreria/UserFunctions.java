package cl.lerolero.libreria;

import android.content.Context;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cl.lerolero.R;

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
    private static String getinfo_tag = "getinfo";
    private static String like_tag = "like";
    private static String newPass_tag = "cambio";


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
    public JSONObject getInfo(String user){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag",getinfo_tag));
        params.add(new BasicNameValuePair("email",user));

        JSONObject json = jsonParser.getJSONFromUrl(getcommentURL, params);

        return json;
    }
    public JSONObject setLike (String comentario, String usuario){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag",like_tag));
        params.add(new BasicNameValuePair("comentario",comentario));
        params.add(new BasicNameValuePair("usuario",usuario));

        JSONObject json = jsonParser.getJSONFromUrl(getcommentURL, params);

        return json;
    }

    public JSONObject setNewPassword (String email, String password){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag",newPass_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

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



    public Integer getLogoEstablecimiento(String id){
        int establecimiento = Integer.valueOf(id);
        switch (establecimiento){
            case 1:
                return R.drawable.bancoestado;
            case 2:
                return R.drawable.bancochile;
            case 3:
                return R.drawable.bancoitau;
            case 4:
                return R.drawable.santander;
            case 5:
                return R.drawable.scotiabank;
            case 6:
                return R.drawable.inacap;
            case 7:
                return R.drawable.andresbello;
            default:
                return R.drawable.map_marker;
        }

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
