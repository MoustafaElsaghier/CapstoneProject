package elsaghier.developer.com.capstoneproject.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelResponse {
    @SerializedName("popularity")
    @Expose
    private List<HotelModel> popularity = null;

    public List<HotelModel> getPopularity() {
        return popularity;
    }

    public void setPopularity(List<HotelModel> popularity) {
        this.popularity = popularity;
    }

}