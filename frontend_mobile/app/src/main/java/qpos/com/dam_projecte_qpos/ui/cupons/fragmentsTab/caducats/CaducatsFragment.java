package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.caducats;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.CuponsListAdapter;
import qpos.com.dam_projecte_qpos.ui.cupons.Item;

public class CaducatsFragment extends Fragment {

    private CaducatsViewModel mViewModel;



    public static CaducatsFragment newInstance() {
        return new CaducatsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caducats, container, false);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Menjar de Gat", "3€", "24/08/2023"));
        items.add(new Item("Menjar de Peix", "5€", "12/03/2023"));
        items.add(new Item("Llit de Gos", "5€", "12/05/2023"));
        items.add(new Item("Cupó Descompte", "5€", "12/05/2023"));


        CuponsListAdapter adapter = new CuponsListAdapter(getContext(), items);

        ListView listView = view.findViewById(R.id.listView_caducats);
        listView.setAdapter(adapter);

        return view;
    }

}