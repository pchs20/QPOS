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

import qpos.com.dam_projecte_qpos.ui.compte.CompteFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    EditText usuarioEditText, passwordEditText;
    Button inicioSessionButton;
    private APiService apiService;
    private SharedPreferences sharedPreferences;

    private Context context;
    AuthResponse authResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText = findViewById(R.id.editText_user);
        passwordEditText = findViewById(R.id.editText_pass);
        inicioSessionButton = findViewById(R.id.button_inicioSession);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedToken = sharedPreferences.getString("token", null);

        if (savedToken != null) {
            // El usuario ya ha iniciado sesión, puedes redirigir a la actividad principal
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish(); // Para cerrar la actividad actual y evitar que el usuario regrese al inicio de sesión
        } else {

            context = this;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://qpos.onrender.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(APiService.class);

            inicioSessionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = usuarioEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    login(username, password);

                }

            });
        }
    }

    private void login(String username, String password) {
        // Realiza la solicitud de inicio de sesión a la API Django
        Call<AuthResponse> call = apiService.login(username, password);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    authResponse = response.body();
                    // Guarda el token en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Log.d("Token", authResponse.getToken());
                    Log.d("UserID", authResponse.getId()+"");
                    editor.putString("token", authResponse.getToken());
                    editor.putInt("id", authResponse.getId());
                    editor.apply();
                    Intent intent = new Intent(context, PrincipalActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar errores de autenticación, como credenciales incorrectas
                    String username = usuarioEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    loginAdmin(username,password);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Manejar errores de conexión
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("ERROOOOR", "Error de connexió");
            }
        });
    }
        public void RegistrarUsuari (View view){
            Intent intent = new Intent(this, RegistreActivity.class);
            startActivity(intent);
        }

    private void loginAdmin(String username, String password) {

        Call<AuthResponse> call = apiService.loginAdmin(username, password);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    authResponse = response.body();
                    // Guarda el token en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Log.d("Token", authResponse.getToken());
                    editor.putString("token", authResponse.getToken());
                    editor.apply();
                    Intent intent = new Intent(context, EntradaAdminActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, "Inicio de sesión exitoso en el segundo enlace", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar errores de autenticación en el segundo enlace
                    Toast.makeText(context, "Usuari o contrassenya incorrectes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Manejar errores de conexión en el segundo enlace
                Toast.makeText(context, "Error de conexión en el segundo enlace", Toast.LENGTH_SHORT).show();
                Log.e("ERROOOOR", "Error de conexión en el segundo enlace");
            }
        });
    }

}