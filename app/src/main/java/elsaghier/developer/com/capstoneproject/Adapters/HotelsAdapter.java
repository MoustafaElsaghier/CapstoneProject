package elsaghier.developer.com.capstoneproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import elsaghier.developer.com.capstoneproject.Activities.HotelDetailsActivity;
import elsaghier.developer.com.capstoneproject.Models.HotelModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 1/25/2018.
 */

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelHolder> {
    private List<HotelModel> mData;
    private Context mContext;

    public HotelsAdapter(List<HotelModel> data, Context mContext) {
        this.mData = data;
        this.mContext = mContext;
    }

    @Override
    public HotelsAdapter.HotelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_recyc_item, parent, false);
        return new HotelsAdapter.HotelHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelsAdapter.HotelHolder holder, int position) {

        final HotelModel restaurant = mData.get(position);
        //TODO:: implement Hotel items and pass them to holder
       /*
        holder.setName(restaurant.getName());
        holder.setAddress(restaurant.getLocation().getAddress());
        holder.setRating(restaurant.getAverageCostForTwo() + " " + restaurant.getCurrency());

        if (restaurant.getFeaturedImage().isEmpty())
            holder.restaurantImg.setImageResource(R.mipmap.ic_launcher);
        else
            Glide.with(mContext).load(restaurant.getFeaturedImage()).into(holder.restaurantImg);
*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, HotelDetailsActivity.class);
                i.putExtra("rest_item", restaurant);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }


    class HotelHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_img)
        ImageView restaurantImg;
        @BindView(R.id.item_name)
        TextView restaurantName;
        @BindView(R.id.iteem_address)
        TextView restaurantAddress;
        @BindView(R.id.item_price)
        TextView restaurantPriceRange;

        HotelHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        void setName(String name) {
            restaurantName.setText(name);
        }

        void setAddress(String address) {
            restaurantAddress.setText(address);
        }

        void setRating(String rating) {
            restaurantPriceRange.setText(rating);
        }

    }
}
