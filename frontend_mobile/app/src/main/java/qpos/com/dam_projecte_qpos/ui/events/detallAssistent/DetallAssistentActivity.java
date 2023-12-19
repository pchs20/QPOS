package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.R;

public class DetallAssistentActivity extends AppCompatActivity {

    List<Assistencies> clientList;
    private RecyclerView recyclerView;
    private DetallAssistentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_assistent);

        Intent intent = getIntent();
        if (intent.hasExtra("clientList")) {
            clientList = (List<Assistencies>) intent.getSerializableExtra("clientList");
        }

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.assistentRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear y configurar el adapter
        adapter = new DetallAssistentAdapter(clientList, this);
        recyclerView.setAdapter(adapter);
    }

}