package elsaghier.developer.com.capstoneproject.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ELSaghier on 1/25/2018.
 */

public class HotelModel implements Serializable {
    @SerializedName("hotel_id")
    @Expose
    private Integer hotelId;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("ty_summary")
    @Expose
    private String tySummary;
    @SerializedName("property_type")
    @Expose
    private String propertyType;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTySummary() {
        return tySummary;
    }

    public void setTySummary(String tySummary) {
        this.tySummary = tySummary;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

}

