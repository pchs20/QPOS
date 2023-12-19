package qpos.com.dam_projecte_qpos.ui.factures;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacturesFragment extends Fragment {

    private FacturesViewModel mViewModel;
    private List<Compra> facturasList;
    private ListView listViewFacturas;
    private APiService apiService;
    private String token;
    private int idClient;
    private Spinner spinnerMetodePagament;
    private ProgressBar progressBar;

    public static FacturesFragment newInstance() {
        return new FacturesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factures, container, false);

        facturasList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APiService.class);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);
        progressBar = view.findViewById(R.id.progressBar);
        spinnerMetodePagament = view.findViewById(R.id.spinnerFiltrePagament);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.spinner_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMetodePagament.setAdapter(adapter);

        listViewFacturas = view.findViewById(R.id.listView_facturas);

        spinnerMetodePagament.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccion = adapterView.getItemAtPosition(i).toString();

                if (seleccion.equals("Efectiu")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<List<Compra>> call = apiService.obtenirCompresFiltrades(token, idClient, "Efectiu");
                    call.enqueue(new Callback<List<Compra>>() {
                        @Override
                        public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                            progressBar.setVisibility(View.GONE);
                            facturasList = response.body();
                            Log.d("COMPRES", "Hem obtingut alguna cosa " + facturasList.get(0).getLiniesCompra().get(0).getProducte().getPreu());
                            FacturaListAdapter facturaListAdapter = new FacturaListAdapter(getContext(), facturasList);
                            listViewFacturas.setAdapter(facturaListAdapter);

                            listViewFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    Intent intent = new Intent(getContext(), DetallFacturaActivity.class);
                                    intent.putExtra("compra", facturasList.get(i));
                                    startActivity(intent);
                                }
                            });
                        }
                        @Override
                        public void onFailure(Call<List<Compra>> call, Throwable t) {

                        }
                    });
                } else if (seleccion.equals("Targeta")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<List<Compra>> call = apiService.obtenirCompresFiltrades(token, idClient, "Targeta");
                    call.enqueue(new Callback<List<Compra>>() {
                        @Override
                        public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                            progressBar.setVisibility(View.GONE);
                            facturasList = response.body();
                            Log.d("COMPRES", "Hem obtingut alguna cosa " + facturasList.get(0).getLiniesCompra().get(0).getProducte().getPreu());
                            FacturaListAdapter facturaListAdapter = new FacturaListAdapter(getContext(), facturasList);
                            listViewFacturas.setAdapter(facturaListAdapter);

                            listViewFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    Intent intent = new Intent(getContext(), DetallFacturaActivity.class);
                                    intent.putExtra("compra", facturasList.get(i));
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<Compra>> call, Throwable t) {

                        }
                    });

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    Call<List<Compra>> call = apiService.obtenirCompres(token, idClient);

                    call.enqueue(new Callback<List<Compra>>() {
                        @Override
                        public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                            progressBar.setVisibility(View.GONE);
                            facturasList = response.body();
                            Log.d("COMPRES", "Hem obtingut alguna cosa " + facturasList.get(0).getLiniesCompra().get(0).getProducte().getPreu());
                            FacturaListAdapter facturaListAdapter = new FacturaListAdapter(getContext(), facturasList);
                            listViewFacturas.setAdapter(facturaListAdapter);

                            listViewFacturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    Intent intent = new Intent(getContext(), DetallFacturaActivity.class);
                                    intent.putExtra("compra", facturasList.get(i));
                                    startActivity(intent);
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<Compra>> call, Throwable t) {
                            Log.d("COMPRES", "No hemos obtenido na " + t.getMessage());
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });



        return view;
    }

}