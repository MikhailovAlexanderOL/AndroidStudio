package com.example.city_sight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.FilteringMode;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import java.sql.Driver;

public class sightMap extends AppCompatActivity {
    MapView mapview;
    TextView textView;
    TextView textView2;
    Button showLocation;
    Button showAttraction;
    private LocationManager locationManager;
    private LocationManager attractionManager;
    private static final int REQUEST_CODE_LOCATION = 1;

    Sight sight = new Sight();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_map);
        Bundle arguments = getIntent().getExtras();
        sight.setTitle(arguments.getString("title"));
        sight.setFullDisc(arguments.getString("fullDisc"));
        sight.setWorkHours(arguments.getString("workHours"));
        sight.setCoordinates(new Point((Double) arguments.get("latitude"), (Double) arguments.get("longitude")));
        mapview = (MapView)findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(sight.getCoordinates(), 16.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        mapview.getMap().getMapObjects().addPlacemark(sight.getCoordinates());

        textView = (TextView) findViewById(R.id.mapTitle);
        textView.setText((sight.getTitle()));
        textView2 = (TextView) findViewById(R.id.discTitle);
        textView2.setText((sight.getFullDisc()));
        showLocation = (Button) findViewById(R.id.showLocation);
        showAttraction = (Button) findViewById(R.id.showAttraction);
        locationManager = MapKitFactory.getInstance().createLocationManager();



        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // If the permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        }

        showAttraction.setOnClickListener(v->{
            locationManager.subscribeForLocationUpdates(0, 0, 0, false, FilteringMode.OFF, new LocationListener() {
                @Override
                public void onLocationUpdated(@NonNull Location location) {
                    sight.setCoordinates(new Point((Double) arguments.get("latitude"), (Double) arguments.get("longitude")));
                    mapview = (MapView)findViewById(R.id.mapview);
                    mapview.getMap().move(
                            new CameraPosition(sight.getCoordinates(), 16.0f, 0.0f, 0.0f),
                            new Animation(Animation.Type.SMOOTH, 0),
                            null);
                    mapview.getMap().getMapObjects().addPlacemark(sight.getCoordinates());
                }

                @Override
                public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {

                }
            });
        });
        showLocation.setOnClickListener(v -> {
            if (locationManager != null) {
                // Request location updates
                locationManager.subscribeForLocationUpdates(0,0, 0, true, FilteringMode.ON, new LocationListener() {
                    @Override
                    public void onLocationUpdated(@NonNull Location location) {
                        // Here you can get the user's location and move the map to it
                        Point userLocation = new Point(location.getPosition().getLatitude(), location.getPosition().getLongitude());
                        //System.out.println(userLocation.getLatitude() + "   " + userLocation.getLongitude());
                        mapview.getMap().move(
                                new CameraPosition(new Point(location.getPosition().getLatitude(), location.getPosition().getLongitude()), 14.0f, 0.0f, 0.0f),
                                new Animation(Animation.Type.SMOOTH, 0),
                                null);

                        // Add the user location marker to the map
                        mapview.getMap().getMapObjects().addPlacemark(userLocation);

                    }
                    @Override
                    public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
                        // Handle location status updates
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}