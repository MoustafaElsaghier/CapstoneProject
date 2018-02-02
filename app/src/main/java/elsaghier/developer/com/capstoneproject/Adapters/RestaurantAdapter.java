package elsaghier.developer.com.capstoneproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Activities.RestaurantsDetailsActivity;
import elsaghier.developer.com.capstoneproject.Fragments.RestaurantsDetailsActivityFragment;
import elsaghier.developer.com.capstoneproject.Models.RestaurantModel;
import elsaghier.developer.com.capstoneproject.Models.RestaurantResponse;
import elsaghier.developer.com.capstoneproject.Models.SharedPreferenceFiles;
import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 1/25/2018.
 */


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {

    private List<RestaurantResponse.RestRes> mData;
    private Context mContext;
    private boolean isTablet;

    public RestaurantAdapter(List<RestaurantResponse.RestRes> mData, Context mContext, boolean isTablet) {
        this.mData = mData;
        this.mContext = mContext;
        this.isTablet = isTablet;
    }

    @Override
    public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_recyc_item, parent, false);
        return new RestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantHolder holder, int position) {
        final RestaurantModel restaurant = mData.get(position).getList();
        holder.setName(restaurant.getName());
        holder.setAddress(restaurant.getLocation().getAddress());
        if (restaurant.getThumbImage().isEmpty())
            holder.restaurantImg.setImageResource(R.drawable.restauranticon);
        else
            Glide.with(mContext).load(restaurant.getThumbImage()).into(holder.restaurantImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferenceFiles.saveToSharedPreference(mContext, "rest_name", restaurant.getName());
                if (isTablet) {

                    RestaurantsDetailsActivityFragment fragment = new RestaurantsDetailsActivityFragment();

                    Bundle b = new Bundle();
                    b.putSerializable("rest_item", restaurant);
                    fragment.setArguments(b);
                    ((AppCompatActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.Rpane_2, fragment)
                            .commit();
                } else {
                    Intent i = new Intent(mContext, RestaurantsDetailsActivity.class);
                    i.putExtra("rest_item", restaurant);
                    mContext.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }


    class RestaurantHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_img)
        ImageView restaurantImg;
        @BindView(R.id.item_name)
        TextView restaurantName;
        @BindView(R.id.iteem_address)
        TextView restaurantAddress;

        RestaurantHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setName(String name) {
            restaurantName.setText(name);
        }

        void setAddress(String address) {
            restaurantAddress.setText(address);
        }

    }
}

