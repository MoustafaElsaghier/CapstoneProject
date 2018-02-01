package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Models.Film;
import elsaghier.developer.com.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    public static boolean isTab;
    Film filmModel;

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
            filmModel = (Film) getArguments().getSerializable("movie_item");
        else
            filmModel = (Film) getActivity().getIntent().getSerializableExtra("movie_item");

    }
}
