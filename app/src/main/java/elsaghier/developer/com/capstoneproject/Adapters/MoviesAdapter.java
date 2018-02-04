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

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Activities.MoviesDetailsActivity;
import elsaghier.developer.com.capstoneproject.Fragments.MovieDetailsActivityFragment;
import elsaghier.developer.com.capstoneproject.Models.Film;
import elsaghier.developer.com.capstoneproject.Models.SharedPreferenceFiles;
import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 1/25/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.HotelHolder> {
    private List<Film> mData;
    private Context mContext;
    private boolean isTablet;

    public MoviesAdapter(List<Film> mData, Context mContext, boolean isTablet) {
        this.mData = mData;
        this.mContext = mContext;
        this.isTablet = isTablet;
    }

    @Override
    public MoviesAdapter.HotelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recyc_item, parent, false);
        return new MoviesAdapter.HotelHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.HotelHolder holder, int position) {

        final Film filmModel = mData.get(position);
        holder.setPoster(filmModel.getPosterPath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceFiles.saveToSharedPreference(mContext, mContext.getString(R.string.film_key_shared), filmModel.getTitle());

                if (isTablet) {

                    MovieDetailsActivityFragment fragment = new MovieDetailsActivityFragment();
                    Bundle b = new Bundle();
                    b.putSerializable(mContext.getString(R.string.film_key_pass), filmModel);
                    fragment.setArguments(b);
                    ((AppCompatActivity) mContext).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.Mpane_2, fragment)
                            .commit();
                } else {

                    Intent i = new Intent(mContext, MoviesDetailsActivity.class);
                    i.putExtra(mContext.getString(R.string.film_key_pass), filmModel);
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


    class HotelHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.poster_img)
        ImageView moivePoster;

        HotelHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setPoster(String path) {
            if (!path.isEmpty())
                Glide.with(mContext).load(mContext.getString(R.string.img_load_URL) + path)
                        .error(R.drawable.ic_movie)
                        .centerCrop()
                        .crossFade().placeholder(R.drawable.ic_movie)
                        .into(moivePoster);
        }
    }
}
