package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Adapters.RestaurantAdapter;
import elsaghier.developer.com.capstoneproject.ApiWork.ApiClient;
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
        showProgressDialog(getContext(), "Loading List of Restaurants", "getting restaurants from server");
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getContext());
        hotelsRecycler.setLayoutManager(layoutManager);
        anInterface = ApiClient.getClient("https://developers.zomato.com/api/v2.1/")
                .create(RestaurantInterface.class);
        call = anInterface.getRestaurants("application/json",
                "55a1d18014dd0c0dac534c02598a3368");
        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantResponse> call, @NonNull Response<RestaurantResponse> response) {
                hideProgressDialog();
                adapter = new RestaurantAdapter(response.body().getRestaurants(), getContext());
                Toast.makeText(getContext(), "HHH" + response.body().getRestaurants().size(), Toast.LENGTH_SHORT).show();
                hotelsRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantResponse> call, @NonNull Throwable t) {
                hideProgressDialog();
                System.out.println("Error : " + t.getMessage());
                Toast.makeText(getContext(), "Error Please Try again", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
