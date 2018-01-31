package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsaghier.developer.com.capstoneproject.Fragments.RestaurantActivityFragment;
import elsaghier.developer.com.capstoneproject.R;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            RestaurantActivityFragment detailFragment = new RestaurantActivityFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.Rpane_1, detailFragment).commit();
        }
    }
}
