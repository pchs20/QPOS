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

public class ProductosListAdapter extends ArrayAdapter<LiniaCompra> {
    public ProductosListAdapter(Context context, List<LiniaCompra> compra) {
        super(context, 0, compra);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LiniaCompra compra = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_productos_factura, parent, false);

        }
        TextView textView1 = convertView.findViewById(R.id.textCodiBarres);
        TextView textView2 = convertView.findViewById(R.id.textNomProducte);
        TextView textView3 = convertView.findViewById(R.id.textImportProducte);
        TextView textView4 = convertView.findViewById(R.id.textImportTotalProducte);


        textView1.setText(String.valueOf(compra.getId()));
        textView2.setText(compra.getQuantitat() + "X " +compra.getProducte().getNom());
        textView3.setText(String.valueOf(compra.getProducte().getPreu()) + "€");
        textView4.setText(String.valueOf(compra.getProducte().getPreu() * compra.getQuantitat()) + "€");





        return convertView;
    }
}
