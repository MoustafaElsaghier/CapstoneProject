package elsaghier.developer.com.capstoneproject.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ELSaghier on 1/25/2018.
 */

public class RestaurantResponse implements Serializable {

    @SerializedName("results_found")
    @Expose
    private Integer resultsFound;
    @SerializedName("results_start")
    @Expose
    private Integer resultsStart;
    @SerializedName("results_shown")
    @Expose
    private Integer resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<RestRes> restaurants = null;
    private final static long serialVersionUID = -6995728231124076920L;

    public Integer getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(Integer resultsFound) {
        this.resultsFound = resultsFound;
    }

    public Integer getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(Integer resultsStart) {
        this.resultsStart = resultsStart;
    }

    public Integer getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(Integer resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<RestRes> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestRes> restaurants) {
        this.restaurants = restaurants;
    }

    public class RestRes implements Serializable {
        @SerializedName("restaurant")
        private
        RestaurantModel list = null;

        public RestaurantModel getList() {
            return list;
        }

        public void setList(RestaurantModel list) {
            this.list = list;
        }
    }
}