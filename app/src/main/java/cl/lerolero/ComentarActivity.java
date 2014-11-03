package cl.lerolero;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.lerolero.libreria.UserFunctions;

public class ComentarActivity extends Activity {
    long idemotion;
    UserFunctions userFunctions = new UserFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Bundle datos = this.getIntent().getExtras();
        final String id = datos.getString("idsucursal");
        final String nombre = datos.getString("nombre");
        final String direccion = datos.getString("direccion");
        final Double lat = datos.getDouble("lat");
        final Double lng = datos.getDouble("lng");


        TextView nombreBanco = (TextView)findViewById(R.id.txtTitulo);
        TextView direccionBanco = (TextView)findViewById(R.id.txtDireccion);

        nombreBanco.setText(nombre);
        direccionBanco.setText(direccion);

        Spinner emotionSpinner = (Spinner)findViewById(R.id.emotionSpinner);
        List<String> listEmotion = new ArrayList<String>();

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(ComentarActivity.this, R.array.emotion, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionSpinner.setAdapter(dataAdapter);


        //Boton enviar del comentario
        Button btnAcceptComment = (Button)findViewById(R.id.btnAcceptComment);
        btnAcceptComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtComment = (EditText)findViewById(R.id.txtComment);

                String commnet = txtComment.getText().toString();
                String email = userFunctions.getUserLoggedIn(getApplicationContext());
                String emotion = String.valueOf(idemotion);
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(lng);
                String sucursal = String.valueOf(id);

                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.registerComment(commnet, email, emotion, latitude, longitude, sucursal);
                Log.e("JSON", commnet.toString());
                Log.e("JSON", email.toString());
                Log.e("JSON", emotion.toString());
                //Log.e("JSON", latitude.toString());
                //Log.e("JSON", longitude.toString());
                finish();
            }
        });
        Button btnCancelComment = (Button)findViewById(R.id.btnCancelComment);
        btnCancelComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
