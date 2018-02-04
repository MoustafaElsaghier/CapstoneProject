package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Adapters.RestaurantAdapter;
import elsaghier.developer.com.capstoneproject.ApiWork.ApiClientRestaurants;
import elsaghier.developer.com.capstoneproject.ApiWork.RestaurantInterface;
import elsaghier.developer.com.capstoneproject.Models.RestaurantResponse;
import elsaghier.developer.com.capstoneproject.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.hideProgressDialog;
import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.showProgressDialog;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantActivityFragment extends Fragment {

    @BindView(R.id.restaurants_recyc)
    RecyclerView hotelsRecycler;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;

    RestaurantInterface anInterface;
    retrofit2.Call<RestaurantResponse> call;


    public RestaurantActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        showProgressDialog(getContext(), getString(R.string.load_rest), getString(R.string.load_rest_detail));
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final boolean isTablet = getResources().getBoolean(R.bool.isTab);

        layoutManager = new LinearLayoutManager(getContext());
        hotelsRecycler.setLayoutManager(layoutManager);
        anInterface = ApiClientRestaurants.getClient(getString(R.string.zomata_URL))
                .create(RestaurantInterface.class);
        call = anInterface.getRestaurants(getString(R.string.app_type),
                getString(R.string.zomato_key));
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantResponse> call, @NonNull Response<RestaurantResponse> response) {
                hideProgressDialog();
                adapter = new RestaurantAdapter(response.body().getRestaurants(), getContext(),isTablet);
                hotelsRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantResponse> call, @NonNull Throwable t) {
                hideProgressDialog();
                Snackbar.make(view, getString(R.string.error_messages),Snackbar.LENGTH_LONG).show();
            }
        });

    }


}
