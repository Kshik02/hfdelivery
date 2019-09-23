package com.ieeecsvit.hfdelivery.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ieeecsvit.hfdelivery.R;
import com.ieeecsvit.hfdelivery.model.OrderClass;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.CustomViewHolder> {

    private ArrayList<OrderClass> dataList;
    private Context mContext;

    public TrackAdapter(ArrayList<OrderClass> dataList, Context mContext){

        this.dataList = dataList;
        this.mContext = mContext;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView orderID, from , to;
        CardView cardView;

        CustomViewHolder(Context context, View itemView) {
            super(itemView);
            orderID = itemView.findViewById(R.id.order_id);
            from = itemView.findViewById(R.id.location_from);
            to = itemView.findViewById(R.id.location_to);
            cardView = itemView.findViewById(R.id.track_cardview);

        }
    }

    @NonNull
    @Override
    public TrackAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_locations, parent, false);
        return new TrackAdapter.CustomViewHolder(parent.getContext(),view);
    }


    @Override
    public void onBindViewHolder(@NonNull final TrackAdapter.CustomViewHolder holder, final int i) {
        holder.orderID.setText(dataList.get(i).getOrderID());
        holder.from.setText(dataList.get(i).getFrom());
        holder.to.setText(dataList.get(i).getTo());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri gmmIntentUri = Uri.parse("google.navigation:q=Connaught+Place,+New+Delhi,Delhi&mode=l");
                String format = "geo:"+dataList.get(i).getFromLatitude()+","+dataList.get(i).getFromLongitude()
                        +"?q=" + dataList.get(i).getToLatitutde() + "," + dataList.get(i).getToLongitude()
                        + "(" + dataList.get(i).getTo() + ")";
                Uri uri = Uri.parse(format);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(mapIntent);
                }
                else {
                    Toast.makeText(mContext,"Install Google Maps", Toast.LENGTH_LONG).show();

                }

            }
        });

    }



    @Override
    public int getItemCount() {
        Log.e("Size",String.valueOf(dataList.size())) ;
        return dataList.size();

    }
}
