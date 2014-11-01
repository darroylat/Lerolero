package cl.lerolero;

import cl.lerolero.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import cl.lerolero.libreria.UserFunctions;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class InicioActivity extends Activity {
    UserFunctions userFunctions;
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inicio);

        userFunctions = new UserFunctions();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(userFunctions.isUserLoggedIn(getApplicationContext())){
                    Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                    map.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(map);

                    // Closing dashboard screen
                    finish();

                }else{
                    // user is not logged in show login screen
                    Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    // Closing dashboard screen
                    finish();
                }

            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);





    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }


}
