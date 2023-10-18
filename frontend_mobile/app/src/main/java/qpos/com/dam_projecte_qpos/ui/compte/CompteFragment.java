package qpos.com.dam_projecte_qpos.ui.compte;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import qpos.com.dam_projecte_qpos.MainActivity;
import qpos.com.dam_projecte_qpos.R;

public class CompteFragment extends Fragment {
    private String[] buttonNames = {"Dades Personals", "Ajuda", "Tancar Sessió"};
    private int[] optionIcons = {R.drawable.icon_user, R.drawable.icon_help, R.drawable.icon_logout};


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

        // Agrega un oyente de clics a los elementos de la lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Maneja el clic en un botón específico
            String buttonText = buttonNames[position];
            Log.d("ButtonListFragment", "Hiciste clic en: " + buttonText); // Agrega un log

            Toast.makeText(view.getContext(), "Hiciste clic en: " + buttonText, Toast.LENGTH_SHORT).show();
            if (buttonText.equals("Tancar Sessió")){
                MainActivity main = new MainActivity();
                main.logout();
            }
        });

        return rootView;
    }

}