package qpos.com.dam_projecte_qpos;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText usuarioEditText, passwordEditText;
    Button inicioSessionButton;
    private APiService apiService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* usuarioEditText = findViewById(R.id.editText_user);
        passwordEditText = findViewById(R.id.editText_pass);
        inicioSessionButton = findViewById(R.id.button_inicioSession);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://miservidorelbonito.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APiService.class);

        inicioSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usuarioEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                login(username,password);

            }
        });
    }

    private void login(String username, String password) {
        // Realiza la solicitud de inicio de sesión a la API Django
        Call<AuthResponse> call = apiService.login(username, password);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    // Guarda el token en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", authResponse.getToken());
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar errores de autenticación, como credenciales incorrectas
                    Toast.makeText(MainActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Manejar errores de conexión
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("ERROOOOR", "Error de connexió");
            }
        });

    }*/
    }
        public void login(View view){
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        public void RegistrarUsuari (View view){
            Intent intent = new Intent(this, RegistreActivity.class);
            startActivity(intent);
        }


        public void logout () {
            // Elimina el token de autenticación y realiza otras tareas de cierre de sesión
            SharedPreferences sharedPreferences = getSharedPreferences("MiAppPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("token"); // Suponiendo que "token" es la clave para el token de autenticación
            editor.apply();

            // Redirige al usuario a la pantalla de inicio de sesión
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad actual
        }


}