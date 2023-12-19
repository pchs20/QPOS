package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.PrincipalActivity;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.CupoClient;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallCupoActivity extends AppCompatActivity {

    private TextView titolCupo, descCupo, dataCupo, monedesClient, missatgeDeshabilitat;
    private Button activarCupo;
    private ImageView iconaMoneda;
    private SharedPreferences sharedPreferences;
    private APiService apiService;
    private String token;
    private int idClient;
    private Client client;
    private Cupons cupo;
    private List<CupoClient> cupoClientList;
    private String actiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_cupo);

        titolCupo = findViewById(R.id.titolCupo);
        descCupo = findViewById(R.id.DescCupo);
        dataCupo = findViewById(R.id.DataCupo);
        monedesClient = findViewById(R.id.MonedesClient);
        missatgeDeshabilitat = findViewById(R.id.missatgeDeshabilitat);
        activarCupo = findViewById(R.id.BotoCupo);
        iconaMoneda = findViewById(R.id.iconaMoneda);

        actiu = null;
        Intent intent = getIntent();
        cupo = (Cupons) intent.getSerializableExtra("cupo");
        actiu = (String) intent.getSerializableExtra("activity");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APiService.class);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);
        activarCupo.setVisibility(View.GONE);
        monedesClient.setVisibility(View.GONE);
        iconaMoneda.setVisibility(View.GONE);

        Call<Client> call = apiService.obtenerDatos(token, idClient);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful() && response.body() != null && actiu == null) {
                    client = response.body();
                    monedesClient.setText(String.valueOf(client.getPunts()));
                    activarCupo.setBackgroundColor(getResources().getColor(R.color.orange_header));
                    activarCupo.setEnabled(true);
                    missatgeDeshabilitat.setText("");
                    activarCupo.setVisibility(View.VISIBLE);
                    monedesClient.setVisibility(View.VISIBLE);
                    iconaMoneda.setVisibility(View.VISIBLE);
                    if (client.getPunts() < cupo.getPunts()){
                        activarCupo.setBackgroundColor(getResources().getColor(R.color.grisClaro));
                        activarCupo.setEnabled(false);
                        missatgeDeshabilitat.setText("No tens punts suficients");
                        activarCupo.setVisibility(View.VISIBLE);
                        monedesClient.setVisibility(View.VISIBLE);
                        iconaMoneda.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                // Manejar el fallo de la llamada si es necesario
            }
        });

        titolCupo.setText(cupo.getNom());
        descCupo.setText(cupo.getDescripcio());
        String data = cupo.getDataFinal();
        String[] dataFinal = data.split(" ");
        dataCupo.setText("Caducitat: " + dataFinal[0]);
        activarCupo.setText("Activar Cupó (" + cupo.getPunts() + " monedes)");
    }

    public void afegirCupo() {
        getCuponsClient();  // Llamada asíncrona
        // La acción de añadir el cupo debe realizarse dentro del onResponse
    }

    public void getCuponsClient() {
        Call<List<CupoClient>> call = apiService.obtenirCupons(token, idClient);
        call.enqueue(new Callback<List<CupoClient>>() {
            @Override
            public void onResponse(Call<List<CupoClient>> call, Response<List<CupoClient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cupoClientList = response.body();

                    if (!cupoClientList.isEmpty()) {
                        CupoClient cupoTrobat = cupoClientList.get(0);
                        cupoTrobat.setQuantitat(cupoTrobat.getQuantitat() + 1);
                        Log.e("CUPON", "Cupo Trobat id = " + cupoTrobat.getQuantitat());
                        Log.e("CUPON", "idClient = " + idClient);

                        Call<Void> updateCall = apiService.actualizarCupons(token, cupoTrobat.getId(), cupoTrobat);
                        updateCall.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                ActualitzarPunts nousPunts = new ActualitzarPunts(idClient, - cupo.getPunts());
                                Call<Void> callModificarClient = apiService.actualitzarPunts(token, nousPunts);
                                callModificarClient.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Log.e("CUPON", "Actualizadoo " + response.toString());
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Log.e("CUPON", "NOO Actualizadoo " + t.getMessage());

                                    }
                                });
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.e("CUPON", "NOO Actualizadoo " + t.getMessage());
                            }
                        });
                    } else {
                        CupoClient nouCupoClient = new CupoClient(1, idClient, cupo.getId());
                        ActualitzarPunts nousPunts = new ActualitzarPunts(idClient, - cupo.getPunts());
                        Call<Void> createCall = apiService.afegirCupo(token, nouCupoClient);
                        createCall.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Call<Void> callModificarClient = apiService.actualitzarPunts(token, nousPunts);
                                callModificarClient.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {


                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.e("CUPON", "NOO CREADO " + t.getMessage());
                            }
                        });
                    }
                } else {
                    // Manejar la respuesta no exitosa si es necesario
                }
            }

            @Override
            public void onFailure(Call<List<CupoClient>> call, Throwable t) {
                // Manejar el fallo de la llamada si es necesario
            }
        });
    }

    public void GenerarCupo(View view) {
        afegirCupo();
        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("selectedItemId", R.id.navigation_cupons);
        startActivity(intent);
    }

}