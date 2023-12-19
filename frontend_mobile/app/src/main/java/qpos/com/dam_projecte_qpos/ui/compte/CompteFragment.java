package qpos.com.dam_projecte_qpos.ui.compte;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import qpos.com.dam_projecte_qpos.APiService;
import qpos.com.dam_projecte_qpos.LogoutHandler;
import qpos.com.dam_projecte_qpos.MainActivity;
import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.compte.DadesClient.DadesPersonalsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompteFragment extends Fragment {
    private String[] buttonNames = {"Dades Personals", "Ajuda", "Tancar Sessió"};
    private int[] optionIcons = {R.drawable.icon_user, R.drawable.icon_help, R.drawable.icon_logout};
    private LogoutHandler logoutHandler;

    private APiService apiService;

    private String token;
    private int idClient;


    public void ButtonListFragment() {
        // Constructor vacío requerido.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_compte, container, false);

        ListView listView = rootView.findViewById(R.id.buttonListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(), R.layout.list_item_layout, R.id.textView, buttonNames) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                ImageView iconImageView = view.findViewById(R.id.iconImageView);
                iconImageView.setImageResource(optionIcons[position]);

                return view;
            }
        };
        // Establece el adaptador en el ListView
        listView.setAdapter(adapter);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        token = "Token " + sharedPreferences.getString("token", "");
        idClient = sharedPreferences.getInt("id", 0);

        // Agrega un oyente de clics a los elementos de la lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Maneja el clic en un botón específico
            String buttonText = buttonNames[position];
            Log.d("ButtonListFragment", "Hiciste clic en: " + buttonText); // Agrega un log

            Toast.makeText(view.getContext(), "Hiciste clic en: " + buttonText, Toast.LENGTH_SHORT).show();

            if (buttonText.equals("Tancar Sessió")){
                if(logoutHandler != null){
                    logoutHandler.handleLogout();
                }



            }else if(buttonText.equals("Dades Personals")){
                Context context = view.getContext();
                Intent intent = new Intent(context, DadesPersonalsActivity.class);
                intent.putExtra("client", "event");
                context.startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LogoutHandler) {
            logoutHandler = (LogoutHandler) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement LogoutListener");
        }
    }
}