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

    public MoviesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        showProgressDialog(getContext(), getString(R.string.loading_movies), getString(R.string.loading_movies_hint));
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final boolean isTablet = getResources().getBoolean(R.bool.isTab);

        layoutManager = new GridLayoutManager(getContext(), 2);
        moviesRecycler.setLayoutManager(layoutManager);
        anInterface = ApiClient.getClient(getString(R.string.api_URL))
                .create(MovieInterFace.class);
        call = anInterface.getPopularMovies(getString(R.string.api_key_movies));

        call.enqueue(new Callback<FilmsResponse>() {
            @Override
            public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response) {
                hideProgressDialog();
                List<Film> filmModels = response.body().getFilms();
                moviesAdapter = new MoviesAdapter(filmModels, getContext(), isTablet);
                moviesRecycler.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<FilmsResponse> call, @NonNull Throwable t) {
                hideProgressDialog();
                Snackbar.make(view, R.string.error_messages, Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
