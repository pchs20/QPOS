package qpos.com.dam_projecte_qpos.ui.events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.events.detall.DetallEventActivity;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> listaEventos;

    public EventAdapter(List<Event> listaEventos) {
        this.listaEventos = listaEventos;
    }
    public void updateEventList(List<Event> listaEventos) {
        this.listaEventos = listaEventos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_eventos, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = listaEventos.get(position);
        holder.titolTextView.setText(event.getNom());
        holder.ubicacioTextView.setText(event.getUbicacio());
        holder.imatgeEvent.setImageResource(R.drawable.imatge_event);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetallEventActivity.class);
                intent.putExtra("evento", event);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }


}
