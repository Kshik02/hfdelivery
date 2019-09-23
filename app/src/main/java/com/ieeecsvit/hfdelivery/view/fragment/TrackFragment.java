package com.ieeecsvit.hfdelivery.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ieeecsvit.hfdelivery.R;
import com.ieeecsvit.hfdelivery.model.OrderClass;
import com.ieeecsvit.hfdelivery.view.adapter.TrackAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackFragment extends Fragment {

    private DatabaseReference userRef;
    private DatabaseReference vendorRef;

    private String fromLatitude, toLatitude, fromLongitude , toLongitude,  from , to, orderID, toID, fromID;

    private ArrayList<OrderClass> delivery = new ArrayList<OrderClass>();

    RecyclerView recyclerView;
    TextView noDeliveries;
    private ProgressBar updateProgress;

    DatabaseReference ordersRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track, container, false);

        updateProgress = view.findViewById(R.id.track_progressbar);

        recyclerView = view.findViewById(R.id.track_recyclerview);
        recyclerView.setVisibility(View.GONE);

        noDeliveries = view.findViewById(R.id.nodelivery_text);
        noDeliveries.setVisibility(View.GONE);


        //Initialise Firebase
        //firebaseDatabase = FirebaseDatabase.getInstance();
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");
        ordersRef.keepSynced(true);



        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //if(snapshot.child("Status").getValue().toString().equals("0")) {
                        orderID = snapshot.getKey();
                        /*userRef = firebaseDatabase.getReference().child("User").child(snapshot.child("To").getValue().toString());
                        vendorRef = firebaseDatabase.getReference().child("Vendor").child(snapshot.child("From").getValue().toString());*/
                        to = "TO";
                        toLatitude = snapshot.child("To_Longitude").getValue().toString();
                        fromLatitude = snapshot.child("From_Latitude").getValue().toString();
                        fromLongitude = snapshot.child("From_Longitude").getValue().toString();
                        toLongitude = snapshot.child("To_Longitude").getValue().toString();
                        from = "From";

                        delivery.add(new OrderClass(orderID,from, fromLatitude,fromLongitude, to, toLatitude, toLongitude));
                    Log.e("Size",String.valueOf(delivery.get(0).getFromLatitude())) ;
                    //}
                }

                updateProgress.setVisibility(View.GONE);
                Log.e("Sizezz",String.valueOf(delivery.size())) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        TrackAdapter adapter = new TrackAdapter(delivery, getContext());
        adapter.notifyDataSetChanged();
        //Log.e("Size",String.valueOf(delivery.get(0).getFromLatitude())) ;

        if(delivery.size()>0) {
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);

        }
        else{
            noDeliveries.setVisibility(View.VISIBLE);
        }

        return view;
    }

}
