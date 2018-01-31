package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsaghier.developer.com.capstoneproject.Fragments.HotelsActivityFragment;
import elsaghier.developer.com.capstoneproject.R;

public class HotelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            HotelsActivityFragment detailFragment = new HotelsActivityFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.Hpane_1, detailFragment).commit();
        }
    }
}
