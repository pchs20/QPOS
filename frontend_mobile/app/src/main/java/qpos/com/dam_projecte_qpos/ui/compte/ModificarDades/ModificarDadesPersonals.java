package qpos.com.dam_projecte_qpos.ui.compte.ModificarDades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.MainActivity;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.compte.DadesClient.DadesPersonalsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarDadesPersonals extends AppCompatActivity {

    private EditText nomEditText, cognomsEditText, usuariEditText, passEditText, dniEditText, dataEditText, telefonEditText, bioEditText, mailEditText, pass2EditText;

    private Client client;
    private APiService apiService;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_dades_personals);
        context = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APiService.class);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = "Token " + sharedPreferences.getString("token", "");


        nomEditText = findViewById(R.id.editTextNom);
        cognomsEditText = findViewById(R.id.editTextCognom);
        usuariEditText = findViewById(R.id.editTextUser);
        passEditText = findViewById(R.id.editTextPass);
        dniEditText = findViewById(R.id.editTextDNI);
        dataEditText = findViewById(R.id.editTextDate);
        telefonEditText = findViewById(R.id.editTextPhone);
        bioEditText = findViewById(R.id.editText_bio);
        mailEditText = findViewById(R.id.editText_email);
        pass2EditText = findViewById(R.id.editTextPass2);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra("client");
        nomEditText.setText(client.getNom());
        cognomsEditText.setText(client.getCognoms());
        usuariEditText.setText(client.getUsername());
        dniEditText.setText(client.getDni());
        dataEditText.setText(client.getAnyNaixement());
        telefonEditText.setText(client.getTelefon());
        bioEditText.setText(client.getBio());
        mailEditText.setText(client.getEmail());

    }

    public void ModificarDades(View view) {


        passEditText.setText(client.getPassword());
        pass2EditText.setText(client.getPassword());

        nomEditText = findViewById(R.id.editTextNom);
        cognomsEditText = findViewById(R.id.editTextCognom);
        usuariEditText = findViewById(R.id.editTextUser);
        passEditText = findViewById(R.id.editTextPass);
        dniEditText = findViewById(R.id.editTextDNI);
        dataEditText = findViewById(R.id.editTextDate);
        telefonEditText = findViewById(R.id.editTextPhone);
        bioEditText = findViewById(R.id.editText_bio);
        mailEditText = findViewById(R.id.editText_email);
        pass2EditText = findViewById(R.id.editTextPass2);

        Client client = new Client(
                usuariEditText.getText().toString(),
                nomEditText.getText().toString(),
                cognomsEditText.getText().toString(),
                mailEditText.getText().toString(),
                passEditText.getText().toString(),
                pass2EditText.getText().toString(),
                dniEditText.getText().toString(),
                dataEditText.getText().toString(),
                bioEditText.getText().toString(),
                telefonEditText.getText().toString());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = "Token " + sharedPreferences.getString("token", "");
        int idClient = sharedPreferences.getInt("id", 0);
        Log.e("USUARI ID", idClient + "");
        Log.e("USUARI Nom", client.getNom());

        Call<Void> call = apiService.modificarClient(token, idClient, client);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(context, "Todo OK", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DadesPersonalsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(context, "No s'ha pogut modificat les dades", Toast.LENGTH_SHORT).show();
            }
        });


    }
}