package qpos.com.dam_projecte_qpos.ui.compte.DadesClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.MainActivity;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.compte.ModificarDades.ModificarDadesPersonals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DadesPersonalsActivity extends AppCompatActivity {

    APiService apiService;
    private Client datos;
    private Context context;
    private String token;
    private int idClient;

    private Button eliminar;

    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dades_personals);

        TextView textViewNom = findViewById(R.id.textViewNom);
        TextView textViewMail = findViewById((R.id.textViewMail));
        TextView textViewTelf = findViewById(R.id.textViewTelefon);
        TextView textViewBio = findViewById(R.id.textViewBio);
        eliminar = findViewById(R.id.eliminarUsuari);
        eliminar.setBackgroundColor(getResources().getColor(R.color.red));
        progressBar = findViewById(R.id.progressBar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        context = this;

        apiService = retrofit.create(APiService.class);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);

        Call<Client> call = apiService.obtenerDatos(token,idClient);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    datos = response.body();
                    textViewNom.setText(datos.getNom() + " " + datos.getCognoms());
                    textViewMail.setText((datos.getEmail()));
                    textViewTelf.setText(datos.getTelefon());
                    textViewBio.setText(datos.getBio());
                    Log.e("ERROOOOOOR", "Error en la respuesta: " + token);
                } else {
                    try {
                        String errorBodyString = response.errorBody().string();
                        Log.e("ERROOOOOOR", "Error en la respuesta: " + errorBodyString);
                        Log.e("ERROOOOOOR", "Error en la respuesta: " + token);
                        // Puedes mostrar el error en la interfaz de usuario o hacer cualquier otra acción necesaria
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("ERROOOOOOR", "No es pot connectar a la bbdd de clients" );
            }
        });
    }

    public void obrirModificarDades(View view) {
        Intent intent = new Intent(this, ModificarDadesPersonals.class);
        intent.putExtra("client", datos);
        startActivity(intent);

    }

    public void EsborrarClient(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Eliminar compte")
                .setMessage("Esàs segur que vols eliminar el compte?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Call<Void> call = apiService.eliminarClient(token,idClient);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("token");
                                editor.apply();
                                Log.e("ID CLIENT", idClient + "");
                                Toast.makeText(context, "Compte Eliminat Correctament", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                            }
                        });
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }) .show();;


    }
}