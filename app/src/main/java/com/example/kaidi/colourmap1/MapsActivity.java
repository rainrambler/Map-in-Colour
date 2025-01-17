package com.example.kaidi.colourmap1;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.kaidi.colourmap1.R.id.my_toolbar;
import static com.example.kaidi.colourmap1.R.id.webview;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final int LOCATION_REQUEST = 1340;
    final String[] LOCATION_PERMS = {android.Manifest.permission.ACCESS_FINE_LOCATION};
    LocationManager locationManager;
    LocationListener locationListener;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//hide the original bar



        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);


        final WebView webView = (WebView) findViewById(webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "javatojs");////////
        webView.loadUrl("file:///android_asset/map.html");
        //Here to set the leafletmap



        Toolbar myToolbar = (Toolbar) findViewById(my_toolbar);
        //myToolbar.setNavigationIcon(R.drawable.ic_action_add_person);
        //myToolbar.setLogo(R.mipmap.ic_launcher);
        myToolbar.setTitle("Map in colour");
        myToolbar.setTitleTextColor(Color.WHITE);
        //myToolbar.setSubtitle("Subtitle");
        myToolbar.inflateMenu(R.menu.button_clear);



        //Here to set menu item click reaction
        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_item) {
                    //Toast.makeText(MapsActivity.this , R.string.menu_search , Toast.LENGTH_SHORT).show();
                    onClickStartNewActivity();
                } else if (menuItemId == R.id.action_item0) {
                    Toast.makeText(MapsActivity.this , "Mobile Cartography Group Project, made by Kaidi Guo, Jiaqin Ni, Taras Dubrava", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        //DrawerLayout
        final DrawerLayout mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerlayout,
                myToolbar,
                R.string.app_name,
                R.string.app_name
        );
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();





        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int curID = item.getItemId();
                if (curID == R.id.nav_eat) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer1()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer1()");

                    }
                } else if (curID == R.id.nav_buy) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer2()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer2()");

                    }
                } else if (curID == R.id.nav_art) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer3()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer3()");

                    }
                } else if (curID == R.id.nav_relax) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer4()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer4()");

                    }
                } else if (curID == R.id.nav_transport) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer5()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer5()");

                    }
                } else if (curID == R.id.nav_hospital) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer6()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer6()");

                    }
                } else if (curID == R.id.nav_bank) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer7()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer7()");

                    }
                } else if (curID == R.id.nav_wc) {
                    if (item.isChecked()) {
                        item.setChecked(false);
                        webView.loadUrl("javascript:removelayer8()");

                    } else {
                        item.setChecked(true);
                        webView.loadUrl("javascript: addlayer8()");

                    }
                }


                //mDrawerlayout.closeDrawers();
                return true;
            }
        });


        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // log it when the location changes
                if (location != null) {
                    // Log.i("SuperMap", "Location changed : Lat: "
                    //         + location.getLatitude() + " Lng: "
                    //         + location.getLongitude());
                }
            }

            public void onProviderDisabled(String provider) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, LOCATION_PERMS , 1340);

            return;

        }

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        final double latitude = location.getLatitude();
                final double longitude = location.getLongitude();


        final String position= "javascript:relocation("+latitude+","+longitude+")";


        final Button button = (Button) findViewById(R.id.locationbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                webView.loadUrl(position);
                //Toast.makeText(MapsActivity.this , position, Toast.LENGTH_LONG).show();
                // Perform action on click
            }
        });






///////////////////////////////////////////////////////////////////////////////////////////////////////////////





    }

    private void reStartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 0, locationListener);
                    onClickStartNewActivity();//First time start : show second view
                } else {
                    Toast.makeText(this, "Location cannot be obtained due to " + "missing permission.", Toast.LENGTH_LONG).show();
                }
                break;
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClickStartNewActivity() {

        Intent intent = new Intent(this, LegendActivity.class);
        startActivity(intent);
    }



//////////////////////////////////////////////////////




}