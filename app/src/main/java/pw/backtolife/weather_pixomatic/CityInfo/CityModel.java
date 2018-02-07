package pw.backtolife.weather_pixomatic.CityInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Acer on 2/6/2018.
 */

public class CityModel implements Serializable{
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("short_city")
    @Expose
    private String short_city;

    public CityModel(String city, String short_city) {
        this.city = city;
        this.short_city = short_city;
    }

    public String getShort_city() {
        return short_city;
    }

    public void setShort_city(String short_city) {
        this.short_city = short_city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
