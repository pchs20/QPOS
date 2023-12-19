package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;
import qpos.com.dam_projecte_qpos.ui.cupons.CuponsListAdapter;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius.DetallCupoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearCuponsActivity extends AppCompatActivity {
    private APiService apiService;
    private List<Cupons> cupons;
    private ListView listView;
    private Context context;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cupons);
        context = this;

        cupons = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APiService.class);
        listView = findViewById(R.id.listView_crearCupo);
        Call<List<Cupons>> call = apiService.cuponsDisponibles();
        progressBar = findViewById(R.id.progressBar);

        call.enqueue(new Callback<List<Cupons>>() {
            @Override
            public void onResponse(Call<List<Cupons>> call, Response<List<Cupons>> response) {
                progressBar.setVisibility(View.GONE);
                cupons = response.body();
                CuponsListAdapter adapter = new CuponsListAdapter(context, cupons);

                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Cupons>> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cupons cupoSeleccionat = cupons.get(position);
                Log.e("cupo", cupoSeleccionat.getNom());
                obrirDetallCupo(cupoSeleccionat);


            }
        });

    }
    private void obrirDetallCupo(Cupons cupo) {
        Intent intent = new Intent(context, DetallCupoActivity.class);
        intent.putExtra("cupo", cupo);
        // Agrega más extras según sea necesario
        startActivity(intent);
    }
}