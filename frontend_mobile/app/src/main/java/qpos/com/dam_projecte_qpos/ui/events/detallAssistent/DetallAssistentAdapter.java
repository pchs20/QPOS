package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.R;

public class DetallAssistentAdapter extends RecyclerView.Adapter<DetallAssistentViewHolder> {

    private List<Assistencies> clientList;
    private Context context;

    public DetallAssistentAdapter(List<Assistencies> clientList, Context context) {
        this.clientList = clientList;
        this.context = context;
    }
    @NonNull
    @Override
    public DetallAssistentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_assistents, parent, false);
        return new DetallAssistentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetallAssistentViewHolder holder, int position) {
        Assistencies client = clientList.get(position);
        holder.nomAssistent.setText(client.getClient().getNom() + " " + client.getClient().getCognoms());
        holder.imatgeAssistent.setImageResource(R.drawable.imagenusuario);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DadesAssistentActivity.class);
                intent.putExtra("dadesClient", client);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }


}
