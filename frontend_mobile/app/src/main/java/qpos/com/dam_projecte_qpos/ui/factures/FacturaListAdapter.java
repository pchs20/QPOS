package qpos.com.dam_projecte_qpos.ui.factures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;

public class FacturaListAdapter extends ArrayAdapter<Compra> {
    public FacturaListAdapter(Context context, List<Compra> compra) {
        super(context, 0, compra);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Compra compra = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_factura, parent, false);

        }
        String data = String.valueOf(compra.getData());
        String[] dataFinal = data.split(" ");
        ImageView imageView = convertView.findViewById(R.id.iconFactura);
        imageView.setImageResource(R.drawable.icon_factura);

        // Accede a los elementos de vista dentro del diseño personalizado
        TextView textView1 = convertView.findViewById(R.id.dataFactura);
        TextView textView2 = convertView.findViewById(R.id.importFactura);

        textView1.setText(dataFinal[0]);
        textView2.setText(String.valueOf(compra.getImportFinal()) + "€");


        return convertView;
    }
}
