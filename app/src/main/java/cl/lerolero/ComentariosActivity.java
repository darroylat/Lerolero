package cl.lerolero;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cl.lerolero.libreria.UserFunctions;

public class ComentariosActivity extends Activity {
    UserFunctions userFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle datos = this.getIntent().getExtras();
        String idsucursal = datos.getString("sucursal");
        String nombre_establecimiento = datos.getString("nombre");
        String direccion_sucursal = datos.getString("direccion");

        TextView txtNombre = (TextView)findViewById(R.id.nombre_establecimiento);
        TextView txtDireccion = (TextView)findViewById(R.id.direccion_sucursal);
        txtNombre.setText(nombre_establecimiento);
        txtDireccion.setText(direccion_sucursal);

        ListView miLista = (ListView)findViewById(R.id.listView);

        ArrayList<String> miColeccion = new ArrayList<String>();
        userFunctions = new UserFunctions();
        JSONObject json = userFunctions.getComments(idsucursal);

        try {

            JSONArray jArray = json.optJSONArray("comentarios");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jsonProductObject = jArray.getJSONObject(i);
                miColeccion.add(jsonProductObject.getString("comentarioid"));
                miColeccion.add(jsonProductObject.getString("comentarioemotion"));
                miColeccion.add(jsonProductObject.getString("comentariocomment"));
                miColeccion.add(jsonProductObject.getString("comentariolatitude"));
                miColeccion.add(jsonProductObject.getString("comentariolongitude"));
                miColeccion.add(jsonProductObject.getString("comentariocreated"));

                Log.i("Json", String.valueOf(jsonProductObject));
            }


        }catch (Exception e){
            e.printStackTrace();
        }







    }



}
