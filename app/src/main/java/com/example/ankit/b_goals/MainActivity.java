package com.example.ankit.b_goals;

import android.Manifest;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.design.widget.Snackbar;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleMap.OnMyLocationButtonClickListener,OnMapReadyCallback {
SupportMapFragment sMapFragment;


    public int Signed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sMapFragment = SupportMapFragment.newInstance();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Signed = 0;






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



// --------------------------------------------SIGN IN OUT TOGGLE ------------------------------------------------------------
        //   int checkval = 0;
        // int checkval = getIntent().getIntExtra("check",signed);

        // MenuItem SignInButton = (MenuItem) findViewById(R.id.nav_signin);
       // MenuItem SignOutButton = (MenuItem)findViewById(R.id.nav_signout);
        // if(checkval == 1) {
        //   SignInButton.setVisible(false);
        //   SignOutButton.setVisible(true);
        // }
        // else
        // {
        //    SignInButton.setVisible(true);
      //  SignOutButton.setTitle("Signing in");
        // }


//---------------------------------------------------------------------------------------------------------------------------





        sMapFragment.getMapAsync(this);
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sfm = getSupportFragmentManager();
        if(!sMapFragment.isAdded())
        sfm.beginTransaction().add(R.id.map_frame,sMapFragment).commit();
        else
            sfm.beginTransaction().show(sMapFragment).commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.search_bar) {
            Toast temp = Toast.makeText(MainActivity.this, "Search Bar is clicked", Toast.LENGTH_SHORT);
            temp.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent i = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_goals) {

        } else if (id == R.id.nav_bussiness) {

        } else if (id == R.id.nav_checkinout) {

        } else if (id == R.id.nav_maptype) {
            if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
            {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
            else
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        } else if (id == R.id.nav_signin) {

            if( Signed == 0 )
            {
                Signed = 1;
                Intent i = new Intent(MainActivity.this,MainLoginActivity.class);
                startActivity(i);

            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this, "Already Signed In!", Toast.LENGTH_SHORT);
                temp.show();
            }

        } else if(id == R.id.nav_signout)
        {
            if(Signed == 1) {
                Toast temp = Toast.makeText(MainActivity.this, "Signed Out Successfully!", Toast.LENGTH_SHORT);
                temp.show();
                Signed = 0;
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this, "Sign In First!", Toast.LENGTH_SHORT);
                temp.show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

/*    public void changeType(View v)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }


    public void onSearch(View view) {

        if (view.getId() == R.id.Bsearch) {
            int flag=0;

            EditText location_tf = (EditText) findViewById(R.id.TFaddress);
            String location = location_tf.getText().toString();
            if (isNetworkAvailable()) {
                List<Address> addressesList = null;

                if(location.equals("")||location==null) {

                    Toast temp = Toast.makeText(MainActivity.this, "Please enter a location!", Toast.LENGTH_SHORT);
                    temp.show();
                }
                else
                {
                    Geocoder geocoder = new Geocoder(this);
                    if(geocoder.isPresent()) {

                        try {
                            addressesList = geocoder.getFromLocationName(location, 1);
                        } catch (IOException e) {
                            Toast temp = Toast.makeText(MainActivity.this, "Please enter a valid location!", Toast.LENGTH_SHORT);
                            temp.show();
                        }
                        finally {

                            Address address = addressesList.get(0);
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(address.getFeatureName().toString()));
                            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        }
                    }
                }
            } else {
                Toast temp = Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
    }
*/
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }


}
