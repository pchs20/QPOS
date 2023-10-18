package qpos.com.dam_projecte_qpos.ui.cupons;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius.ActiusFragment;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.caducats.CaducatsFragment;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.utilitzats.UtilitzatsFragment;

public class TabsPagerAdapter extends FragmentStateAdapter {

    public TabsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return ActiusFragment.newInstance();
            case 1:
                return UtilitzatsFragment.newInstance();
            case 2:
                return CaducatsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Tres pestañas: "Actius," "Utilitzats," y "Caducats."
    }
}
