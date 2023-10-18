package qpos.com.dam_projecte_qpos.ui.cupons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import qpos.com.dam_projecte_qpos.R;

public class CuponsListAdapter extends ArrayAdapter<Item> {
    public CuponsListAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el elemento de datos para esta posición
        Item item = getItem(position);

        // Reutiliza o infla una vista personalizada para el elemento de la lista
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items_cupons, parent, false);

        }
        ImageView imageView = convertView.findViewById(R.id.imageView_priceCupon);
        imageView.setImageResource(R.drawable.price_cupon);

        // Accede a los elementos de vista dentro del diseño personalizado
        TextView textView1 = convertView.findViewById(R.id.textView_codi);
        TextView textView2 = convertView.findViewById(R.id.textView_import);

        // Configura los textos y acciones para los elementos de vista
        textView1.setText(item.getCodi());
        textView2.setText(item.getImporte());


        return convertView;
    }
}

