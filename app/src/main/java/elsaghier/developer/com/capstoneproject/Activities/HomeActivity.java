package elsaghier.developer.com.capstoneproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.R;
import elsaghier.developer.com.capstoneproject.ToDoActivity;

public class HomeActivity extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(HomeActivity.this, "Ad Loaded Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(HomeActivity.this, "Failed To Load Ad", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.to_do_item)
    void openToDo(){
        Intent intent = new Intent(HomeActivity.this, ToDoActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.restaurant_layout)
    void openRestaurants() {
        Intent intent = new Intent(HomeActivity.this, RestaurantActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.hotels_layout)
    void openHotels() {
        Intent intent = new Intent(HomeActivity.this, MoviesActivity.class);
        startActivity(intent);
    }

}
