package fr.minint.ppol.dostl.neogeo2;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //Step 1 : déclaration de mon bouton
    Button btnGetLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: cast de mon bouton
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);

        //Step 3 : demande à l'utilisateur l'autorisation d'accèder à la position de l'appareil
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);

        //Step 4 : mise en place d'un listener sur mon bouton
        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  utilisation de ma class NetworkTracker
                NetworkTracker nwt = new NetworkTracker(getApplicationContext());
                Location l = nwt.getLocation();
                if(l != null){
                    double latitude = l.getLatitude();
                    double longitude = l.getLongitude();
                    /*String provider = l.getProvider();
                    float accuracy = l.getAccuracy();
                    double altitude = l.getAltitude();
                    float bearing = l.getBearing();
                    long elapsedRealtimeNanos = l.getElapsedRealtimeNanos();
                    Bundle extras = l.getExtras();
                    float speed = l.getSpeed();
                    long time = l.getTime();*/

                    Toast.makeText(getApplicationContext(),
                            "Latitude : "+latitude
                            +"\nLongitude : "+longitude
                            /*+"\naccuracy : "+accuracy
                            +"\nProvider : "+provider
                            +"\nTime : "+time
                            +"\naltitude : "+altitude
                            +"\nbearing : "+bearing
                            +"\nelapsedRealtimeNanos : "+elapsedRealtimeNanos
                            +"\nextras : "+extras
                            +"\nspeed : "+speed*/
                            ,Toast.LENGTH_LONG).show();


                }
            }//fin de onClick()
        });
    }//fin de onCreate()
}
