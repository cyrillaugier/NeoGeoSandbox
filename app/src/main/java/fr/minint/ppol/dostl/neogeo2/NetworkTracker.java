package fr.minint.ppol.dostl.neogeo2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Cyril Laugier on 27/10/17.
 */

public class NetworkTracker implements LocationListener {

//    un objet permettant de stocker les données de l'appli dans le cash

    Context context;

//    mon constructeur

    public NetworkTracker(Context c) {
        context = c;
    }

//    ma methode

    public Location getLocation() {

//          Step 1 : Je m'assure que j'ai bien obtenu l'autorisation de l'utilisateur

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, "Permission d'accès à la position de l'appareil refusée !", Toast.LENGTH_LONG).show();
            return null;
        }
//          Step 2 : j'enclenche l'API Location

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        /*List<String> lp = lm.getProviders(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
        for (String provider : lp
                ) {

            Toast.makeText(context, provider.toString(), Toast.LENGTH_SHORT).show();
        }*/
//          Step 3 : dès qu'un provider est disponible je recupère la position
        if (isNetworkEnabled) {

//          je demande ma position "réseau" toutes les 5 (milli) secondes, le 3ème paramètre
//          correspond à un second trigger en fonction d'une distance en mètres (this = LocationListener)

            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5 * 1000, 0, this);
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return l;

        } else {
            Toast.makeText(context, "Veuiller activer l'icone \"Position\", SVP", Toast.LENGTH_LONG).show();
        }

        return null;


    }

//      4 méthodes issues de Location que je n'utilise pas

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
