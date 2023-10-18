package qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import qpos.com.dam_projecte_qpos.R;
import qpos.com.dam_projecte_qpos.ui.cupons.CuponsListAdapter;
import qpos.com.dam_projecte_qpos.ui.cupons.Item;
public class ActiusFragment extends Fragment {

    private ActiusViewModel mViewModel;

    public static ActiusFragment newInstance() {
        return new ActiusFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actius, container, false);

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("567654443", "3€", "24/11/2023"));
        items.add(new Item("984478839", "5€", "12/10/2023"));
        items.add(new Item("984478839", "5€", "12/10/2023"));


        CuponsListAdapter adapter = new CuponsListAdapter(getContext(), items);

        ListView listView = view.findViewById(R.id.listView_actius);
        listView.setAdapter(adapter);

        return view;
    }

}
