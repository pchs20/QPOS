package qpos.com.dam_projecte_qpos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EntradaAdminActivity extends AppCompatActivity implements LogoutHandler {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_admin);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public void tancarSessio(View view) {
        handleLogout();


    }
    @Override
    public void handleLogout() {
        // Implementa la lógica de logout aquí
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String tokenBeforeLogout = sharedPreferences.getString("token", null);
        Log.d("TokenBeforeLogout", tokenBeforeLogout);
        editor.remove("token");
        editor.apply();
        tokenBeforeLogout = sharedPreferences.getString("token", null);
        if (tokenBeforeLogout != null) {
            Log.d("TokenBeforeLogout", tokenBeforeLogout);
        } else {
            Log.d("TokenBeforeLogout", "Token is null");
        }

        // Redirige al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}