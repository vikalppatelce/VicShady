package com.examples.androidgooglemaps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	   
	   private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	   
	   private Context context;
	   
	   public AddItemizedOverlay(Drawable defaultMarker) {
	        super(boundCenterBottom(defaultMarker));
	   }
	   
	   public AddItemizedOverlay(Drawable defaultMarker, Context context) {
	        this(defaultMarker);
	        this.context = context;
	   }

	   @Override
	   protected OverlayItem createItem(int i) {
	      return mapOverlays.get(i);
	   }

	   @Override
	   public int size() {
	      return mapOverlays.size();
	   }
	   
	   @Override
	   protected boolean onTap(int index) {
		  Log.e("Tap", "Tap Performed");
	      return true;
	   }
	   
	   public void addOverlay(OverlayItem overlay) {
	      mapOverlays.add(overlay);
	       this.populate();
	   }
	   
	   /**
	    * Getting Latitude and Longitude on Touch event
	    * **/
	   @Override
       public boolean onTouchEvent(MotionEvent event, MapView mapView) 
       {   
           
           if (event.getAction() == 1) {                
               GeoPoint geopoint = mapView.getProjection().fromPixels(
                   (int) event.getX(),
                   (int) event.getY());
               // latitude
               double lat = geopoint.getLatitudeE6() / 1E6;
               // longitude
               double lon = geopoint.getLongitudeE6() / 1E6;
               Toast.makeText(context, "Lat: " + lat + ", Lon: "+lon, Toast.LENGTH_SHORT).show();
           }                            
           return false;
       } 

	}