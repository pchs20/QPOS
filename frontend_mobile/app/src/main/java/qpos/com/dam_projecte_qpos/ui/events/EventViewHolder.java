package qpos.com.dam_projecte_qpos.ui.events;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import qpos.com.dam_projecte_qpos.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView titolTextView, dataTextView, duradaTextView, ubicacioTextView;
    public ImageView imatgeEvent;

    public EventViewHolder (View view){
        super(view);

        titolTextView = view.findViewById(R.id.cardTitle);
        dataTextView = view.findViewById(R.id.cardDate);
        ubicacioTextView = view.findViewById(R.id.cardUbicacio);
        imatgeEvent = view.findViewById(R.id.cardImage);

    }
}
