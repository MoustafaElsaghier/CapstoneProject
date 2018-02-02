package elsaghier.developer.com.capstoneproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import elsaghier.developer.com.capstoneproject.Fragments.MoviesActivityFragment;
import elsaghier.developer.com.capstoneproject.R;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            MoviesActivityFragment detailFragment = new MoviesActivityFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.M_pane_1, detailFragment).commit();
        }
    }
}
