package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.utilitzats;

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

public class UtilitzatsFragment extends Fragment {

    private UtilitzatsViewModel mViewModel;

    public static UtilitzatsFragment newInstance() {
        return new UtilitzatsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_utilitzats, container, false);



        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("567654443", "3€", "24/08/2023"));
        items.add(new Item("984478839", "5€", "12/03/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));
        items.add(new Item("984478839", "5€", "12/05/2023"));



        CuponsListAdapter adapter = new CuponsListAdapter(getContext(), items);

        ListView listView = view.findViewById(R.id.listView_utilitzats);
        listView.setAdapter(adapter);

        return view;
    }

}