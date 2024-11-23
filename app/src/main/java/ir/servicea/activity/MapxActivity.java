package ir.servicea.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.gestures.MoveGestureDetector;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.map.sdk_map.MapirStyle;
import ir.map.sdk_map.maps.MapView;
import ir.servicea.app.G;
import ir.servicea.app.GPSTrack;
import ir.servicea.R;


public class MapxActivity extends AppCompatActivity implements MapboxMap.OnCameraIdleListener, MapboxMap.OnCameraMoveListener {
    MapboxMap map;
    Style mapStyle;
    MapView mapView;
    private MarkerOptions markerOptions;
    Button registerBtn;
    FloatingActionButton myLocationBtn;
    private LatLng samplePoint = new LatLng(34.798510, 48.514853);
    private LatLng location = new LatLng(34.798510, 48.514853);

    private Intent intent;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapx);
        G.Activity = this;
        G.context = this;
        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setTypeface(G.ExtraBold);
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        intent = getIntent();
        registerBtn = findViewById(R.id.register_btn);
        myLocationBtn = findViewById(R.id.myLocationButton);
        if (intent.hasExtra("lat") && intent.hasExtra("lon")) {
            samplePoint = new LatLng(intent.getDoubleExtra("lat", 0), intent.getDoubleExtra("lon", 0));
        }
        if (G.preference.getString("location_lat", "0").length() > 3 && G.preference.getString("location_lon", "0").length() > 3) {
            samplePoint = new LatLng(Double.parseDouble(G.preference.getString("location_lat", "0")), Double.parseDouble(G.preference.getString("location_lon", "0")));
        }
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.0000000");
                double lat = Double.parseDouble(G.converToEn(df.format(location.getLatitude())));
                double lon = Double.parseDouble(G.converToEn(df.format(location.getLongitude())));
                G.preference.edit().putString("location_lat", lat + "").apply();
                G.preference.edit().putString("location_lon", lon + "").apply();
                G.toast("با موفقیت ذخیره شد.");
                finish();
            }
        });
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                map = mapboxMap;
                markerOptions = new MarkerOptions();
                map.getUiSettings().setCompassEnabled(false);
                map.getUiSettings().setLogoEnabled(false);
                map.getUiSettings().setRotateGesturesEnabled(true);
                map.clear();
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(samplePoint, 14f));
                MarkerOptions markerOptions = new MarkerOptions().position(samplePoint);
                map.addMarker(markerOptions);
                if (!(G.preference.getString("location_lat", "0").length() > 3 && G.preference.getString("location_lon", "0").length() > 3)) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myLocationBtn.performClick();

                        }
                    }, 1000);
                }
                myLocationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int MyVersion = Build.VERSION.SDK_INT;
                        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
                            if (!checkIfAlreadyhavePermission()) {
                                requestForSpecificPermission();
                            } else {
                                GPS();
                            }
                        } else {
                            GPS();
                        }


                    }
                });
                map.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapStyle = style;
                        map = mapboxMap;
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(samplePoint, 16f));
                        map.addOnCameraMoveListener(MapxActivity.this);
                        map.addOnCameraIdleListener(MapxActivity.this);
                        map.addMarker(markerOptions.position(samplePoint));
                        map.addOnMoveListener(new MapboxMap.OnMoveListener() {

                            @Override
                            public void onMoveBegin(@NonNull MoveGestureDetector detector) {
                            }

                            @Override
                            public void onMove(@NonNull MoveGestureDetector detector) {

                            }

                            @Override
                            public void onMoveEnd(@NonNull MoveGestureDetector detector) {

                            }
                        });


                        map.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                mapStyle = style;
                                addSymbolSourceAndLayerToMap();
                            }
                        });
                    }
                });
            }
        });
    }

    private void addSymbolSourceAndLayerToMap() {
        List<Feature> samplePointsFeatures = new ArrayList<>();
        Feature sampleFeature = Feature.fromGeometry(Point.fromLngLat(samplePoint.getLongitude(), samplePoint.getLatitude()));
        samplePointsFeatures.add(sampleFeature);
        FeatureCollection featureCollection = FeatureCollection.fromFeatures(samplePointsFeatures);
        GeoJsonSource geoJsonSource = new GeoJsonSource("sample_source_id", featureCollection);
        mapStyle.addSource(geoJsonSource);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.mapbox_marker_icon_default);
        mapStyle.addImage("sample_image_id", icon);
        SymbolLayer symbolLayer = new SymbolLayer("sample_layer_id", "sample_source_id");
        symbolLayer.setProperties(
                PropertyFactory.iconImage("sample_image_id"),
                PropertyFactory.iconSize(1.5f),
                PropertyFactory.iconOpacity(.8f),
                PropertyFactory.textColor("#ff5252")
        );
        mapStyle.addLayer(symbolLayer);
    }

    @Override
    public void onCameraIdle() {
        location = map.getCameraPosition().target;
        MarkerOptions markerOptions = new MarkerOptions().position(map.getCameraPosition().target);
        map.addMarker(markerOptions);
    }

    @Override
    public void onCameraMove() {
        map.clear();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myLocationBtn.performClick();
                } else {
                    G.toast("دسترسی به لوکیشن داده نشده است!");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public boolean GPS() {

        GPSTrack gps = new GPSTrack(MapxActivity.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            if (longitude != 0 && latitude != 0) {
                location.setLatitude(gps.getLatitude());
                location.setLongitude(gps.getLongitude());
                new Handler().postDelayed(() -> {
                    location.setLatitude(gps.getLatitude());
                    location.setLongitude(gps.getLongitude());
                }, 1000);

                map.clear();
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14f));
                MarkerOptions markerOptions = new MarkerOptions().position(location);
                map.addMarker(markerOptions);

            } else {
                new Handler().postDelayed(this::GPS, 1000);
            }
        } else {

            G.toast(" لطفا GPS خود را روشن کنید");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            },1000);

            return false;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        G.context = this;
        G.Activity = this;
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    LatLng lastKnowLatLng = null;
    private LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    private MapxActivity.MyLocationCallback callback = new MapxActivity.MyLocationCallback(this);

    private static class MyLocationCallback implements LocationEngineCallback<LocationEngineResult> {
        private final WeakReference<MapxActivity> activityWeakReference;

        MyLocationCallback(MapxActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(LocationEngineResult result) {
            MapxActivity activity = activityWeakReference.get();
            if (activity != null) {
                Location location = result.getLastLocation();
                if (location == null)
                    return;
                activity.lastKnowLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                if (activity.map != null && result.getLastLocation() != null)
                    activity.map.getLocationComponent().forceLocationUpdate(result.getLastLocation());
            }
        }

        @Override
        public void onFailure(@NonNull Exception exception) {
            MapxActivity activity = activityWeakReference.get();
        }
    }
}


