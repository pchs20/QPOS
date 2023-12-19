package qpos.com.dam_projecte_qpos.ui.events;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.Client;
import qpos.com.dam_projecte_qpos.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsFragment extends Fragment {

    private EventsViewModel mViewModel;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    private APiService apiService;
    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();
    private ProgressBar progressBar;

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = rootView.findViewById(R.id.recycleEvent);
        progressBar = rootView.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Event> eventos = new ArrayList<Event>();
        eventAdapter = new EventAdapter(eventos);
        recyclerView.setAdapter(eventAdapter);

        // Llamar al método para obtener eventos
        fetchData();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Actualizar los eventos cada vez que el fragmento se vuelva visible
        fetchData();
    }

    private void fetchData() {
        // Retrofit y llamada a la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://qpos.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APiService.class);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = "Token " + sharedPreferences.getString("token", "");

        Call<List<Event>> call = apiService.obtenirEsdeveniments(token);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<Event> eventos = response.body();
                    Gson gson = new Gson();
                    String jsonResponse = gson.toJson(eventos);
                    Log.d("DEBUG", "Respuesta de la API (JSON): " + jsonResponse);

                    Type listType = new TypeToken<List<Event>>() {}.getType();
                    List<Event> events = gson.fromJson(jsonResponse, listType);

                    if (eventAdapter != null) {
                        Log.d("SIZE", eventos.size() + "");
                        eventAdapter.updateEventList(eventos);
                    }
                } else {
                    // Manejar la respuesta de error
                    Log.e("Error", "Error en la solicitud: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                // Manejar el fallo en la solicitud
                Log.e("Error", "Error en la solicitud: " + t.getMessage());
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}