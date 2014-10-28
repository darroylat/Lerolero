package cl.lerolero;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import cl.lerolero.libreria.UserFunctions;

public class MapsActivity extends FragmentActivity implements LocationListener {
    UserFunctions userFunctions;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    HashMap<String, HashMap> extraMarkerInfo = new HashMap<String, HashMap>();
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);


        setUpMapIfNeeded();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                HashMap<String, String> marker_data = extraMarkerInfo.get(marker.getId());

                String markerid = marker_data.get("idbank");
                String markerTitulo = marker_data.get("nbank");
                String markerSubtitulo = marker_data.get("dbank");

                Location bn ;
                bn = getLocation();
                Double lat = bn.getLatitude();
                Double lng = bn.getLongitude();

                Intent i = new Intent(MapsActivity.this, ComentarActivity.class);
                i.putExtra("id",markerid);
                i.putExtra("titulo",markerTitulo);
                i.putExtra("subtitulo",markerSubtitulo);
                i.putExtra("lat",lat);
                i.putExtra("lng",lng);
                startActivity(i);
                return false;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        getSucursales();
        getLocation();
    }

    public void getSucursales(){
        UserFunctions userFunction = new UserFunctions();
        JSONObject json = userFunction.getBanks();

        try{
            JSONArray jArray = json.optJSONArray("bancos");
            for(int i = 0;i < jArray.length(); i++){
                JSONObject jsonBancoObject = jArray.getJSONObject(i);

                String id = jsonBancoObject.getString("sucursalid");
                String name  = jsonBancoObject.getString("establecimientoname");
                String direction  = jsonBancoObject.getString("sucursaladress");
                String latitude  = jsonBancoObject.getString("sucursallatitude");
                String longitude  = jsonBancoObject.getString("sucursallongitude");
                //No utilizable//setLocationBanks(Integer.parseInt(id), name, direction, Double.valueOf(latitude).doubleValue(), Double.valueOf(longitude).doubleValue());
                Log.i("Json", String.valueOf(jsonBancoObject));

                double lat = Double.valueOf(latitude);
                double lng = Double.valueOf(longitude);
                Integer bankid = Integer.valueOf(id);
                LatLng coordinate = new LatLng(lat, lng);

                Marker sucursales = mMap.addMarker(new MarkerOptions()
                        .position(coordinate)
                        .title(name)
                        .snippet(direction)
                        .icon(BitmapDescriptorFactory.defaultMarker()));

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("idbank", String.valueOf(bankid));
                data.put("nbank",name);
                data.put("dbank",direction);

                extraMarkerInfo.put(sucursales.getId(),data);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Location getLocation(){

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
            // Do something with the recent location fix
            //  otherwise wait for the update below
            Toast.makeText(this, "Calculo", Toast.LENGTH_LONG).show();
            setMarker(location);
        }else{
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Toast.makeText(this,"Ubicacion", Toast.LENGTH_LONG ).show();
            getLocation();
        }
        return location;
    }

    public void setMarker(Location location){
        double latitud = location.getLatitude();
        double longitud = location.getLongitude();
        LatLng coordenadas = new LatLng(latitud,longitud);
        /*
        Marker ubicacion = mMap.addMarker(new MarkerOptions()
                .position(cordenadas)
                .title("Aqui Estas!!")
                .icon(BitmapDescriptorFactory.defaultMarker()));
        */
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_refresh:

                //getComments("-12");
                getSucursales();


                return true;
            case R.id.action_loguot:
                userFunctions = new UserFunctions();
                userFunctions.logoutUser(getApplicationContext());
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                // Closing dashboard screen
                finish();
            case R.id.action_location:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
