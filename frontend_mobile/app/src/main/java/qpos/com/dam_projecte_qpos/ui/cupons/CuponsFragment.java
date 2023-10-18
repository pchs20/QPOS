package qpos.com.dam_projecte_qpos.ui.cupons;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import qpos.com.dam_projecte_qpos.R;

public class CuponsFragment extends Fragment {

    private CuponsViewModel mViewModel;
    ViewPager2 viewPager;
    TabLayout tabLayout;

    public static CuponsFragment newInstance() {
        return new CuponsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cupons, container, false); // Asigna el layout de tu fragmento principal

        viewPager = rootView.findViewById(R.id.viewPager); // Asigna el ViewPager2
        tabLayout = rootView.findViewById(R.id.tabLayout); // Asigna el TabLayout
        Spinner spinner = rootView.findViewById(R.id.spinner);

// Vincula el Spinner con las opciones del menú desplegable
        ArrayAdapter<CharSequence> Arrayadapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_options, android.R.layout.simple_spinner_item);
        Arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(Arrayadapter);

// Configura el adaptador del ViewPager
        TabsPagerAdapter adapter = new TabsPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

// Conecta el TabLayout y el ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(obtenerTituloPestana(position))
        ).attach();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CuponsViewModel.class);
        // TODO: Use the ViewModel
    }
    private String obtenerTituloPestana(int position) {
        switch (position) {
            case 0:
                return "Actius";
            case 1:
                return "Utilitzats";
            case 2:
                return "Caducats";
            default:
                return "";
        }
    }


}