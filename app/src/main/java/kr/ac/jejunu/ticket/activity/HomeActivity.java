package kr.ac.jejunu.ticket.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kr.ac.jejunu.ticket.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private BottomNavigationView bottom;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragment();
    }

    private void setupFragment() {

        bottom = findViewById(R.id.bottomNavigationView);
        try {
            NavHostFragment host = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_controller);
            navController = host.getNavController();
            bottom.setOnNavigationItemSelectedListener(this::onNaviClick);
        }catch (Throwable ignored) {
            Log.d(TAG, String.valueOf(ignored));
        }
    }

    private boolean onNaviClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_menu :
                Toast.makeText(this,"you select home button",Toast.LENGTH_LONG).show();
                navController.navigate(R.id.homeFragment);
                return true;
            case R.id.shopping_menu:
                Toast.makeText(this, "you select shopping button", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.shoppingFragment);
                return true;
        }
        return false;
    }
}
