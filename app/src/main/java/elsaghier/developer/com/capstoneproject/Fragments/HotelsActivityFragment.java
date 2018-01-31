package elsaghier.developer.com.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import elsaghier.developer.com.capstoneproject.Adapters.HotelsAdapter;
import elsaghier.developer.com.capstoneproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotelsActivityFragment extends Fragment {

    @BindView(R.id.hotels_recyc)
    RecyclerView hotelRecycler;
    RecyclerView.LayoutManager layoutManager;
    HotelsAdapter hotelsAdapter;

    public HotelsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        ButterKnife.bind(this, view);
        final boolean isTablet = getResources().getBoolean(R.bool.isTab);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
