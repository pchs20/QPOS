package qpos.com.dam_projecte_qpos.ui.factures;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qpos.com.dam_projecte_qpos.R;

public class FacturesFragment extends Fragment {

    private FacturesViewModel mViewModel;

    public static FacturesFragment newInstance() {
        return new FacturesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_factures, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FacturesViewModel.class);
        // TODO: Use the ViewModel
    }

}