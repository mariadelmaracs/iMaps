package com.example.imap;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private final LatLng BASILICA = new LatLng(3.900731, -76.302219);
    private final LatLng LAGOON = new LatLng(3.869735, -76.338337);
    private final LatLng MOUNTAIN = new LatLng(3.882254,  -76.284473);
    private final LatLng STADIUM = new LatLng(3.891237,  -76.300820);
    private LatLng SELECTED;

    private Marker markerBasilica;
    private Marker markerLagoon;
    private Marker markerMountain;
    private Marker markerStadium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("Ubicacion");
        if (location.equals(getResources().getString(R.string.b_location))) {
            SELECTED = BASILICA;
        }
        if (location.equals(getResources().getString(R.string.l_location))) {
            SELECTED = LAGOON;
        }
        if (location.equals(getResources().getString(R.string.d_location))) {
            SELECTED = MOUNTAIN;
        }
        if (location.equals(getResources().getString(R.string.e_location))) {
            SELECTED = STADIUM;
        }
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
        // Add some markers to the map, and add a data object to each marker.
        markerBasilica = googleMap.addMarker(new MarkerOptions()
                .position(BASILICA)
                .title(getResources().getString(R.string.b_location))
                .snippet(getResources().getString(R.string.b_description))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_marker_b)));
        markerBasilica.setTag(0);

        markerLagoon = googleMap.addMarker(new MarkerOptions()
                .position(LAGOON)
                .title(getResources().getString(R.string.l_location))
                .snippet(getResources().getString(R.string.l_description))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_marker_b)));
        markerLagoon.setTag(0);

        markerMountain = googleMap.addMarker(new MarkerOptions()
                .position(MOUNTAIN)
                .title(getResources().getString(R.string.d_location))
                .snippet(getResources().getString(R.string.d_description))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_marker_b)));
        markerMountain.setTag(0);

        markerStadium = googleMap.addMarker(new MarkerOptions()
                .position(STADIUM)
                .title(getResources().getString(R.string.e_location))
                .snippet(getResources().getString(R.string.e_description))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icons8_marker_b)));
        markerStadium.setTag(0);

        // Set a listener for marker click.
        googleMap.setOnMarkerClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SELECTED, 20));

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has sido clickeado " + clickCount + " veces.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}