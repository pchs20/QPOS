package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import qpos.com.dam_projecte_qpos.R;

public class DetallAssistentViewHolder extends RecyclerView.ViewHolder {
    TextView nomAssistent;
    ImageView imatgeAssistent;

    public DetallAssistentViewHolder(@NonNull View itemView) {
        super(itemView);
        nomAssistent = itemView.findViewById(R.id.nomAssistent);
        imatgeAssistent = itemView.findViewById(R.id.imatgeAssistent);
    }
}
