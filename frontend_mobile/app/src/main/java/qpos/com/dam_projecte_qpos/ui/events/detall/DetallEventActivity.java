package qpos.com.dam_projecte_qpos.ui.events.detall;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.PrincipalActivity;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.events.Event;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.AssistanceRequestBody;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.Assistencies;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.DetallAssistentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallEventActivity extends AppCompatActivity {

    TextView titolEvent, duracioEvent, ubicacioEvent, dataEvent, descripcioEvent, horaEvent;
    ImageView imageEvent;
    private Event evento;
    private Button apuntarseBtn;
    private APiService apiService;
    private String token;
    private int idClient;
    private Client clientAssitent;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_event);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APiService.class);

        context = this;

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);

        titolEvent = findViewById(R.id.cardTitleEvent);
        imageEvent = findViewById(R.id.cardImageEvent);
        duracioEvent = findViewById(R.id.textDuracio);
        ubicacioEvent = findViewById(R.id.cardUbicacioEvent);
        dataEvent = findViewById(R.id.cardDateEvent);
        descripcioEvent = findViewById(R.id.textDescripcio);
        apuntarseBtn = findViewById(R.id.buttonApuntarse);

        Intent intent = getIntent();
        if (intent.hasExtra("evento")) {
            evento = (Event) intent.getSerializableExtra("evento");
            titolEvent.setText(evento.getNom());
            duracioEvent.setText(evento.getDurada() + "'");
            ubicacioEvent.setText(evento.getUbicacio());
            descripcioEvent.setText(evento.getDescripcio());
            imageEvent.setImageResource(R.drawable.imatge_event);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            String formattedDate = evento.getData();
            dataEvent.setText(formattedDate + "h");

        }
        List<Assistencies> clientList = evento.getAssistencies();
        List<Client> clients = new ArrayList<Client>();
        for(int i = 0; i < clientList.size(); i++){
            clients.add(clientList.get(i).getClient());
        }

        for (int i = 0; i < clients.size(); i++){
            Log.e("ID Assistent", clients.get(i).getId() + " " + idClient);
            if(clients.get(i).getId() == idClient){
                apuntarseBtn.setText("Esborrar Assistencia");
            }
        }

    }
        public void ApuntarEvent(View view) {

        Call<Client> client = apiService.obtenerDatos(token, idClient);

        client.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                clientAssitent = response.body();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });

        Assistencies assistencies = new Assistencies(clientAssitent, evento);

        if (apuntarseBtn.getText().equals("Esborrar Assistencia")){
            apuntarseBtn.setBackgroundColor(getResources().getColor(R.color.red));
            Call<Void> call = apiService.eliminarAssistencies(token, idClient, assistencies.getEsdeveniment().getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    Intent intent = new Intent(context, PrincipalActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }else{
            apuntarseBtn.setBackgroundColor(getResources().getColor(R.color.green));
            AssistanceRequestBody assistanceRequestBody = new AssistanceRequestBody(String.valueOf(idClient), assistencies.getEsdeveniment().getId());
            Call<Void> call =apiService.afegirAssitencia(token, assistanceRequestBody);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Intent intent = new Intent(context, PrincipalActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }
    }

    public void VeureAssistents(View view) {
        List<Assistencies> clientList = evento.getAssistencies();
        Intent intent = new Intent(this, DetallAssistentActivity.class);
        intent.putExtra("event", evento);
        intent.putParcelableArrayListExtra("clientList", new ArrayList(clientList));
        startActivity(intent);
    }

}