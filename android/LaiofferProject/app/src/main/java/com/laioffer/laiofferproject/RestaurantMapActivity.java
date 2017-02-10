package com.laioffer.laiofferproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public final static String EXTRA_LATLNG = "EXTRA_LATLNG";
    private LatLng toMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_map);
        MapFragment mapFragment =
                (MapFragment) getFragmentManager().findFragmentById(R.id.restaurant_map);

        // This function automatically initializes the maps system and the view.
        mapFragment.getMapAsync(this);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            toMark = bundle.getParcelable(EXTRA_LATLNG);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (toMark != null) {
            map.addMarker(new MarkerOptions().position(toMark).title("Marker"));
            map.moveCamera(CameraUpdateFactory.newLatLng(toMark));
            //map.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
            CameraPosition newCamPos = new CameraPosition( toMark, //new LatLng(13.0810,80.2740),
                    11.0f,
                    map.getCameraPosition().tilt, //use old tilt
                    map.getCameraPosition().bearing); //use old bearing
            map.animateCamera(CameraUpdateFactory.newCameraPosition(newCamPos), 2000, null);
        }

    }
}
