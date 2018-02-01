package elsaghier.developer.com.capstoneproject.ApiWork;

import elsaghier.developer.com.capstoneproject.Models.FilmsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterFace {
    // Movies
    @GET("movie/popular")
    Call<FilmsResponse> getPopularMovies(@Query("api_key") String apiKey);

}
