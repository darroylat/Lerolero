package cl.lerolero;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import cl.lerolero.libreria.UserFunctions;


public class PrincipalActivity extends Activity implements View.OnClickListener {
    UserFunctions userFunctions;
    ImageButton bancochile,bancoestado,santander,bancoparis,scotiabank,bancofalabella,bbva,bci,bancoitau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
            setContentView(R.layout.activity_principal);

            bancochile = (ImageButton)findViewById(R.id.ibtn_bancochile);
            bancoestado = (ImageButton)findViewById(R.id.ibtn_bancoestado);
            santander = (ImageButton)findViewById(R.id.ibtn_santander);
            bancoparis = (ImageButton)findViewById(R.id.ibtn_bancoparis);
            scotiabank = (ImageButton)findViewById(R.id.ibtn_scotiabank);
            bancofalabella = (ImageButton)findViewById(R.id.ibtn_bancofalabella);
            bbva = (ImageButton)findViewById(R.id.ibtn_bbva);
            bci = (ImageButton)findViewById(R.id.ibtn_bci);
            bancoitau = (ImageButton)findViewById(R.id.ibtn_bancoitau);

            bancochile.setOnClickListener(this);
            bancoestado.setOnClickListener(this);
            santander.setOnClickListener(this);
            bancoparis.setOnClickListener(this);
            scotiabank.setOnClickListener(this);
            bancofalabella.setOnClickListener(this);
            bbva.setOnClickListener(this);
            bci.setOnClickListener(this);
            bancoitau.setOnClickListener(this);

        }else{
            // user is not logged in show login screen
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            // Closing dashboard screen
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibtn_bancochile:
                Intent bancochile = new Intent(getApplicationContext(), MapsActivity.class);
                bancochile.putExtra("Banco",1);
                bancochile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bancochile);
            case R.id.ibtn_bancoestado:
                Intent bancoestado = new Intent(getApplicationContext(), MapsActivity.class);
                bancoestado.putExtra("Banco",2);
                bancoestado.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bancoestado);
            case R.id.ibtn_santander:
                Intent santander = new Intent(getApplicationContext(), MapsActivity.class);
                santander.putExtra("Banco",3);
                santander.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(santander);
            case R.id.ibtn_bancoparis:
                Intent bancoparis = new Intent(getApplicationContext(), MapsActivity.class);
                bancoparis.putExtra("Banco",4);
                bancoparis.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bancoparis);
            case R.id.ibtn_scotiabank:
                Intent scotiabank = new Intent(getApplicationContext(), MapsActivity.class);
                scotiabank.putExtra("Banco",5);
                scotiabank.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(scotiabank);
            case R.id.ibtn_bancofalabella:
                Intent bancofalabella = new Intent(getApplicationContext(), MapsActivity.class);
                bancofalabella.putExtra("Banco",6);
                bancofalabella.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bancofalabella);
            case R.id.ibtn_bbva:
                Intent bbva = new Intent(getApplicationContext(), MapsActivity.class);
                bbva.putExtra("Banco",7);
                bbva.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bbva);
            case R.id.ibtn_bci:
                Intent bci = new Intent(getApplicationContext(), MapsActivity.class);
                bci.putExtra("Banco",8);
                bci.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bci);
            case R.id.ibtn_bancoitau:
                Intent bancoitau = new Intent(getApplicationContext(), MapsActivity.class);
                bancoitau.putExtra("Banco",9);
                bancoitau.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(bancoitau);

            default:
                Intent mostrar = new Intent(getApplicationContext(), MapsActivity.class);
                mostrar.putExtra("Banco",0);
                mostrar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mostrar);
        }

    }
}
