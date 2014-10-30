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

        ListView lista = (ListView)findViewById(R.id.listView);

        ArrayList<Comentario> miColeccion = new ArrayList<Comentario>();
        userFunctions = new UserFunctions();
        Comentario comentario;
        AdapterComentario adapter = null;
        JSONObject json = userFunctions.getComments(idsucursal);

        try {

            JSONArray jArray = json.optJSONArray("comentarios");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jsonDatos = jArray.getJSONObject(i);
                String id = jsonDatos.getString("comentarioid");
                String emotion = jsonDatos.getString("comentarioemotion");
                String comment = jsonDatos.getString("comentariocomment");
                String created = jsonDatos.getString("comentariocreated");

                String uid = jsonDatos.getString("uid");
                String nombre = jsonDatos.getString("infonombre");
                String infonivel = jsonDatos.getString("infopuntosnivel");

                comentario = new Comentario(getResources().getDrawable(R.drawable.ic_launcher),nombre, created, comment, "43");
                miColeccion.add(comentario);
                Log.i("Json", String.valueOf(jsonDatos));
                Log.i("Json1", String.valueOf(json));
                adapter = new AdapterComentario(this, miColeccion);

            }
            lista.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }







    }
    @Override
    public void onBackPressed(){
        finish();

    }


}
