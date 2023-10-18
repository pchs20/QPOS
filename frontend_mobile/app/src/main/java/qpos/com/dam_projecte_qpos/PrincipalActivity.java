package qpos.com.dam_projecte_qpos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import qpos.com.dam_projecte_qpos.databinding.ActivityPrincipalBinding;
import qpos.com.dam_projecte_qpos.ui.compte.CompteFragment;
import qpos.com.dam_projecte_qpos.ui.cupons.CuponsFragment;
import qpos.com.dam_projecte_qpos.ui.factures.FacturesFragment;
import qpos.com.dam_projecte_qpos.ui.events.EventsFragment;

public class PrincipalActivity extends AppCompatActivity {

    ActivityPrincipalBinding binding;
    private Fragment selectedFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);


        // Configurar un oyente para manejar las selecciones de elementos de menú
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if (item.getItemId() == R.id.navigation_productes) {
                    replaceFragment(new EventsFragment());
                    return true;
                } else if (item.getItemId() == R.id.navigation_cupons) {
                    replaceFragment(new CuponsFragment());
                    return true;
                } else if (item.getItemId() == R.id.navigation_factures) {
                    replaceFragment(new FacturesFragment());

                    return true;
                }else if (item.getItemId() == R.id.navigation_compte) {
                    replaceFragment(new CompteFragment());
                    return true;
                }
                return false;
            }


        });

    }
    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        fragmentTransaction.commit();
    }
}