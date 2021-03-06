package india.abhijeet.k.farmafriend;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String loc;
    String lt,longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);






    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;





        try {

            Intent intent=getIntent();
            loc= intent.getStringExtra(AdminRequestReview.MAP_TEXT);





            lt = loc.substring(12, 20);
            longi = loc.substring(23, 32);
        Toast.makeText(this, " " + lt + " " + longi, Toast.LENGTH_LONG).show();


        double latitude=Double.parseDouble(lt);
        double longitude= Double.parseDouble(longi);



            // Location myLoc=new Location(Location.convert(loc));
            //Add a marker in Sydney and move the camera
           LatLng sydney = new LatLng(latitude, longitude);
             mMap.addMarker(new MarkerOptions().position(sydney).title("Marker on Farmer"));

           // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));





          //  Toast.makeText(this, "lat" + lt + "long " + longi, Toast.LENGTH_SHORT).show();

       }catch (Exception ds)
       {

           Toast.makeText(this, "Exception here" , Toast.LENGTH_SHORT).show();

       }
    }
}
