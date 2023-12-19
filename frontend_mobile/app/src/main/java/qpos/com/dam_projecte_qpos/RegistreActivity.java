package qpos.com.dam_projecte_qpos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistreActivity extends AppCompatActivity {

    private EditText nomEditText, cognomsEditText, usuariEditText, passEditText, dniEditText, dataEditText, telefonEditText, bioEditText, mailEditText, pass2EditText;
    private ImageView imageView;

    private APiService apiService;

    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APiService.class);
        context = this;
    }

    public void RegistrarNouUsuari(View view) {

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

        Call<Void> call = apiService.registrarClient(client);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Usuario Registrado con exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                } else {
                    String errorBodyString = null;
                    try {
                        errorBodyString = response.errorBody().string();
                        Toast.makeText(context, "El usuario no se ha podido registrar", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar errores de conexión
            }
        });



    }
}