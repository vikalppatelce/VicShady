package com.exampels.androidgooglemaps;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class AndroidGoogleMapsActivity extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    MapView mapView = (MapView) findViewById(R.id.mapView);
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}