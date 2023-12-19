package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.CupoClient;
import qpos.com.dam_projecte_qpos.ui.cupons.CuponsListAdapter;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.CrearCuponsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActiusFragment extends Fragment {

    private ActiusViewModel mViewModel;
    private List<Cupons> cupons;
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private APiService apiService;
    private String token;
    private int idClient;
    private List<CupoClient> cupoClientList;
    private Context context;
    private List<Cupons> cuponsActius;
    private ProgressBar progressBar;

    public static ActiusFragment newInstance() {
        return new ActiusFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actius, container, false);

        cupons = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);
        apiService = retrofit.create(APiService.class);
        context = getContext();
        progressBar = view.findViewById(R.id.progressBar);

        Call<List<CupoClient>> call = apiService.obtenirCupons(token, idClient);
        call.enqueue(new Callback<List<CupoClient>>() {
            @Override
            public void onResponse(Call<List<CupoClient>> call, Response<List<CupoClient>> response) {
                cupoClientList = response.body();
                if(!cupoClientList.isEmpty()) {

                    cuponsActius = new ArrayList<Cupons>();

                    Call<Cupons> callCupo = apiService.cuponsDisponibles(token, cupoClientList.get(0).getCupo());
                    callCupo.enqueue(new Callback<Cupons>() {
                        @Override
                        public void onResponse(Call<Cupons> call, Response<Cupons> response) {
                            progressBar.setVisibility(View.GONE);
                            for (int i = 0; i < cupoClientList.get(0).getQuantitat(); i++) {
                                cuponsActius.add(response.body());
                                Log.e("FOOR", cuponsActius.get(i) + "");
                            }

                            CuponsListAdapter adapter = new CuponsListAdapter(context, cuponsActius);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(getContext(), DetallCupoActivity.class);
                                    Cupons cupoSeleccionat = cuponsActius.get(i);
                                    intent.putExtra("activity", "actius");
                                    intent.putExtra("cupo", cupoSeleccionat);
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Cupons> call, Throwable t) {

                        }
                    });
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No tens cap cupó encara", Toast.LENGTH_LONG);
                }

            }

            @Override
            public void onFailure(Call<List<CupoClient>> call, Throwable t) {

            }
        });


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton2);
        listView = view.findViewById(R.id.listView_actius);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearCupo(v);
            }
        });

        return view;
    }

    public void CrearCupo(View view) {
        Intent intent = new Intent(getContext(), CrearCuponsActivity.class);
        startActivity(intent);
    }
}
