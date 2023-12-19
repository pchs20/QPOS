package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.events.Event;

public class DadesAssistentActivity extends AppCompatActivity {

    private TextView nomAssistent, emailAssistent;
    private ImageView imatgeAssistent;
    private Assistencies client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dades_assistent);

        nomAssistent = findViewById(R.id.AssistentNomDetall);
        emailAssistent = findViewById(R.id.AssistentMailDetall);
        imatgeAssistent = findViewById(R.id.AssistentImatgeDetall);

        Intent intent = getIntent();
        if (intent.hasExtra("dadesClient")) {
            client = (Assistencies) intent.getSerializableExtra("dadesClient");
            nomAssistent.setText(client.getClient().getNom() + " " + client.getClient().getCognoms());
            emailAssistent.setText(client.getClient().getEmail());
            imatgeAssistent.setImageResource(R.drawable.imagenusuario);
        }
    }
}