package qpos.com.dam_projecte_qpos.ui.factures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;

public class DetallFacturaActivity extends AppCompatActivity {

    private TextView dataFactura, nomClient, dniClient, descompte, importFinal, formaPagament;
    private ListView facturaProductes;
    private Compra compra;
    private List<LiniaCompra> llistaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_factura);

        dataFactura = findViewById(R.id.textData);
        nomClient = findViewById(R.id.textNom);
        dniClient = findViewById(R.id.textDNI);
        descompte = findViewById(R.id.textDescompte);
        importFinal = findViewById(R.id.textImportFinal);
        facturaProductes = findViewById(R.id.listViewProductes);
        llistaCompra = new ArrayList<LiniaCompra>();
        formaPagament = findViewById(R.id.textFormaPagament);

        Intent intent = getIntent();
        compra = (Compra) intent.getSerializableExtra("compra");

        String data = compra.getData();
        String[] dataFinal = data.split(" ");

        dataFactura.setText("Factura: " + dataFinal[0]);
        nomClient.setText("Client: " + compra.getClient().getNom() + " " + compra.getClient().getCognoms() );
        dniClient.setText("DNI: " + compra.getClient().getDni());
        descompte.setText("Descompte: " + compra.getDescompte() + "€");
        importFinal.setText("Import Total: " + compra.getImportFinal() + "€");
        formaPagament.setText("Mètode de Pagament: " + compra.getMetodePagament());



        for (int i = 0; i < compra.getLiniesCompra().size(); i++){
            llistaCompra.add(compra.getLiniesCompra().get(i));
        }

        ProductosListAdapter adapter = new ProductosListAdapter(this, llistaCompra);
        facturaProductes.setAdapter(adapter);

    }
}