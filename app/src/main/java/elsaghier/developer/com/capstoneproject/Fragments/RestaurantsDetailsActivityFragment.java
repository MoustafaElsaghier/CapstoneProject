package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Models.RestaurantModel;
import elsaghier.developer.com.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantsDetailsActivityFragment extends Fragment implements OnMapReadyCallback {

    RestaurantModel restaurantModel;
    @BindView(R.id.restaurant_detail_name)
    TextView mName;
    @BindView(R.id.rest_detail_location)
    TextView mAddress;
    @BindView(R.id.item_price)
    TextView mItemPrice;
    @BindView(R.id.rest_detail_img)
    ImageView restaurantImg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants_details, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        restaurantModel = (RestaurantModel) getActivity().getIntent().getSerializableExtra("rest_item");
        restaurantModel = (RestaurantModel) getArguments().getSerializable("rest_item");


//        SupportMapFragment fragment = (SupportMapFragment) getActivity()
//                .getSupportFragmentManager().findFragmentById(R.id.map);
//
//        fragment.getMapAsync(this);


        mName.setText(restaurantModel.getName());

        mAddress.setText(restaurantModel.getLocation().getAddress());

        String itemPrice = String.format("%s %s", restaurantModel.getPriceRange(), restaurantModel.getCurrency());
        mItemPrice.setText(itemPrice);

        if (restaurantModel.getFeaturedImage().isEmpty())
            restaurantImg.setImageResource(R.drawable.restauranticon);
        else
            Glide.with(this).load(restaurantModel.getFeaturedImage()).into(restaurantImg);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = Double.parseDouble(restaurantModel.getLocation().getLatitude());
        double lng = Double.parseDouble(restaurantModel.getLocation().getLongitude());

        LatLng restLocation = new LatLng(lat, lng);
        Toast.makeText(getContext(), "Map", Toast.LENGTH_SHORT).show();

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(restLocation, 18.0f));
        googleMap.addMarker(new MarkerOptions()
                .title(restaurantModel.getName())
                .snippet("The most populous Restaurant.")
                .position(restLocation));

    }

}
