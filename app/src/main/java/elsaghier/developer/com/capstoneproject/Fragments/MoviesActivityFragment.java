package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Adapters.MoviesAdapter;
import elsaghier.developer.com.capstoneproject.ApiWork.ApiClient;
import elsaghier.developer.com.capstoneproject.ApiWork.MovieInterFace;
import elsaghier.developer.com.capstoneproject.Models.Film;
import elsaghier.developer.com.capstoneproject.Models.FilmsResponse;
import elsaghier.developer.com.capstoneproject.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.hideProgressDialog;
import static elsaghier.developer.com.capstoneproject.Models.ProgressDialogClass.showProgressDialog;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesActivityFragment extends Fragment {

    @BindView(R.id.movies_recyc)
    RecyclerView moviesRecycler;
    RecyclerView.LayoutManager layoutManager;

    MovieInterFace anInterface;
    retrofit2.Call<FilmsResponse> call;

    MoviesAdapter moviesAdapter;
    boolean isTablet;

    public MoviesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        showProgressDialog(getContext(), "Loading List of Movies", "getting Movies from server");
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final boolean isTablet = getResources().getBoolean(R.bool.isTab);

        layoutManager = new GridLayoutManager(getContext(), 2);
        moviesRecycler.setLayoutManager(layoutManager);
        anInterface = ApiClient.getClient("http://api.themoviedb.org/3/")
                .create(MovieInterFace.class);
        call = anInterface.getPopularMovies("50afdb8a06eb4f5d7320f4f4729a993e");

        call.enqueue(new Callback<FilmsResponse>() {
            @Override
            public void onResponse(Call<FilmsResponse> call, Response<FilmsResponse> response) {
                hideProgressDialog();
                List<Film> filmModels = response.body().getFilms();
                moviesAdapter = new MoviesAdapter(filmModels, getContext(), isTablet);
                moviesRecycler.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<FilmsResponse> call, Throwable t) {
                hideProgressDialog();
                Snackbar.make(view, "Error Please Try again", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
