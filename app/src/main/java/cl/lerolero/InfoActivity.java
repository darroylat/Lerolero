package cl.lerolero;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import cl.lerolero.libreria.UserFunctions;


public class InfoActivity extends Activity {
    UserFunctions userFunctions = new UserFunctions();
    Integer porcentaje, menos, nuevo;
    String password, password2 ;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button btnCambiar = (Button)findViewById(R.id.info_cambiar_pass);
        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDialog();
            }
        });


        email = userFunctions.getUserLoggedIn(getApplicationContext());
        JSONObject json = userFunctions.getInfo(email);
        try{
            JSONArray jArray = json.optJSONArray("informacion");
            for(int i = 0;i < jArray.length(); i++){
                JSONObject jsonDatos = jArray.getJSONObject(i);
                String infoid = jsonDatos.getString("infoid");
                String infonombre = jsonDatos.getString("infonombre");
                Integer infopuntos = jsonDatos.getInt("infopuntosnivel");

                TextView nombre = (TextView)findViewById(R.id.info_nombre);
                nombre.setText(infonombre);

                TextView nivel = (TextView)findViewById(R.id.info_nivel);
                ProgressBar progreso = (ProgressBar)findViewById(R.id.progreso);

                if(infopuntos <= 1000){
                    // 0 a 1000 nivel 1
                    porcentaje = infopuntos * 100 / 1000;
                    nivel.setText("Nivel 1");
                    progreso.setProgress(porcentaje);
                }else if(infopuntos > 1000 && infopuntos <= 2500){
                    // 1001 a 2500 nivel 2
                    menos = 1000;
                    nuevo = infopuntos - menos;
                    porcentaje = nuevo * 100/1500;
                    nivel.setText("Nivel 2");
                    progreso.setProgress(porcentaje);
                }else if(infopuntos > 2500 && infopuntos <= 4500){
                    // 2501 a 4500 nivel 3
                    menos = 2500;
                    nuevo = infopuntos - menos;
                    porcentaje = nuevo * 100/2000;
                    nivel.setText("Nivel 3");
                    progreso.setProgress(porcentaje);
                }else if(infopuntos > 4500){
                    // 4501 a 7000 nivel 4
                    menos = 4500;
                    nuevo = infopuntos - menos;
                    porcentaje = nuevo * 100/1000;
                    nivel.setText("Nivel 4");
                    progreso.setProgress(porcentaje);
                }
            }
        }catch (Exception e){

        }

    }

    public void loadDialog(){

        //Inicio comentario, Crea comonente dialog
        final Dialog dialog = new Dialog(InfoActivity.this);
        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.password_dialog);
        dialog.setTitle("Cambio de contraseña");

        final EditText txtpass = (EditText) dialog.findViewById(R.id.txtpass);
        final EditText txtpass2 = (EditText) dialog.findViewById(R.id.txtpass2);

        final TextView aviso = (TextView)dialog.findViewById(R.id.txtAviso);


        //Boton cancelar del comentario
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Boton enviar del comentario
        Button btnAccept = (Button) dialog.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = txtpass.getText().toString();
                password2 = txtpass2.getText().toString();
                email = userFunctions.getUserLoggedIn(getApplicationContext());

                if(password.compareTo(password2) == 0){
                    userFunctions.setNewPassword(email, password);
                    aviso.setText(email);
                    dialog.dismiss();

                }else{
                    aviso.setText("La contraseña no coincide");
                }



                // JSONObject json = userFunction.registerComment(commnet, email, emotion, latitude, longitude);




                //dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
