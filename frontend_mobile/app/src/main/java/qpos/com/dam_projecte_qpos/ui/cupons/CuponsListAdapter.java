package qpos.com.dam_projecte_qpos.ui.cupons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import qpos.com.dam_projecte_qpos.R;

public class CuponsListAdapter extends ArrayAdapter<Cupons> {
    public CuponsListAdapter(Context context, List<Cupons> cupons) {
        super(context, 0, cupons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cupons cupons = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items_cupons, parent, false);

        }
        String descompte = String.valueOf(cupons.getDescompte());
        String[] descompteSeparat = descompte.split("\\.");
        ImageView imageView = convertView.findViewById(R.id.imageView_priceCupon);
        imageView.setImageResource(R.drawable.price_cupon);

        // Accede a los elementos de vista dentro del diseño personalizado
        TextView textView1 = convertView.findViewById(R.id.textView_codi);
        TextView textView2 = convertView.findViewById(R.id.textView_import);

        textView1.setText(cupons.getNom());
        textView2.setText(descompteSeparat[0] + "€");


        return convertView;
    }
}

