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

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Models.Film;
import elsaghier.developer.com.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    public static boolean isTab;
    Film model;

    @BindView(R.id.imgBack)
    ImageView backDropPath;

    @BindView(R.id.filmName)
    TextView filmName;

    @BindView(R.id.filmDate)
    TextView filmDate;

    @BindView(R.id.film_rating)
    TextView filmRating;

    @BindView(R.id.film_over_view)
    TextView filmOverView;

    public MovieDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //movie_item
        if (isTab)
            model = (Film) getArguments().getSerializable("movie_item");
        else
            model = (Film) getActivity().getIntent().getSerializableExtra("movie_item");

        filmName.setText(model.getTitle());
        filmDate.setText(model.getReleaseDate());
        filmRating.setText(String.valueOf(model.getVoteAverage()));
        filmOverView.setText(model.getOverview());
        Glide.with(this).load("http://image.tmdb.org/t/p/w185" + model.getBackdropPath()).into(backDropPath);

    }
}
