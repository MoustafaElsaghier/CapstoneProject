package elsaghier.developer.com.capstoneproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.ButterKnife;
import butterknife.OnClick;
import elsaghier.developer.com.capstoneproject.R;

public class HomeActivity extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        MobileAds.initialize(this, getString(R.string.ad_key));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mAdView.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.to_do_item)
    void openToDo() {
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
