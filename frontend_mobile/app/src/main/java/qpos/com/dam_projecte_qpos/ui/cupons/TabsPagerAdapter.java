package qpos.com.dam_projecte_qpos.ui.cupons;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius.ActiusFragment;

public class TabsPagerAdapter extends FragmentStateAdapter {

    public TabsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ActiusFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2; // Tres pestañas: "Actius," "Utilitzats," y "Caducats."
    }
}
