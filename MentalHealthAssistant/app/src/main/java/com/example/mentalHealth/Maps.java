package com.example.mentalHealth;

import androidx.annotation.NonNull;

import com.applozic.mobicommons.commons.core.utils.Support;
import com.example.mentalHealth.R;
import com.example.mentalHealth.Adapter.ListItemAdapter;
import com.example.mentalHealth.Model.ToDo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.location.Geocoder;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dmax.dialog.SpotsDialog;
import com.google.firebase.database.*;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    ArrayList<String> list = new ArrayList<>();





    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_do);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This was auto-generated to handle app links.


        //Intent appLinkIntent = getIntent();
        //String appLinkAction = appLinkIntent.getAction();
       // Uri appLinkData = appLinkIntent.getData();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {



        Geocoder gc = new Geocoder(this);
        for(int i =0;i<90;i++){
            String str1 = Integer.toString(i);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(str1);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        List<Address> list = null;

                        String name = dataSnapshot.child("Name of Service").getValue().toString();
                        String Addressline1 = dataSnapshot.child("Address Line").getValue().toString();
                        String Addressline2 = dataSnapshot.child("Address Lines").getValue().toString();


                        String eirCode = dataSnapshot.child("Eircode").getValue().toString();
                        String address = (Addressline1 +Addressline2 + eirCode);
                        if(gc.isPresent()) {
                            try {
                                list = gc.getFromLocationName(address, 1);
                            } catch ( IOException e) {
                                e.printStackTrace();
                            }

                            if (!list.isEmpty()) {
                                Address add = list.get(0);
                                double lat = add.getLatitude();
                                double lng = add.getLongitude();



                                map = googleMap;
                                LatLng Child_and_adolescent = new LatLng(lat,lng);
                                map.addMarker(new MarkerOptions().position(Child_and_adolescent).title(name));
                                map.moveCamera(CameraUpdateFactory.newLatLng(Child_and_adolescent));
                            }
                        }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                });

                }

        LatLng currentLocation = new LatLng(53.349804,-6.260310);
        MarkerOptions markerOptions = new MarkerOptions().position(currentLocation);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,10));
        googleMap.addMarker(markerOptions);










        }

}