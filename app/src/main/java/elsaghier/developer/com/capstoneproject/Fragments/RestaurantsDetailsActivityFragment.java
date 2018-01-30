package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Models.RestaurantModel;
import elsaghier.developer.com.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantsDetailsActivityFragment extends Fragment {

    @BindView(R.id.restaurant_detail_name)
    TextView mName;
    @BindView(R.id.rest_detail_location)
    TextView mLocation;

    @BindView(R.id.item_price)
    TextView mItemPrice;
    GoogleMap map;

    @BindView(R.id.rest_detail_img)
    ImageView imgImageView;

    public RestaurantsDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RestaurantModel restaurantModel = (RestaurantModel) getActivity().getIntent().getSerializableExtra("rest_item");

        double lat = Double.parseDouble(restaurantModel.getLocation().getLatitude());
        double lng = Double.parseDouble(restaurantModel.getLocation().getLongitude());

        LatLng restLocation = new LatLng(lat, lng);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(restLocation, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title(restaurantModel.getName())
//                .snippet("The most populous Restaurant in city.")
//                .position(restLocation));

        mName.setText(restaurantModel.getName());

        String itemPrice = String.format("%s %s", restaurantModel.getPriceRange(), restaurantModel.getCurrency());
        mItemPrice.setText(itemPrice);

        mLocation.setText(restaurantModel.getLocation().getAddress());
        Glide.with(this).load(restaurantModel.getFeaturedImage()).into(imgImageView);
    }
}
