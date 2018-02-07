package pw.backtolife.weather_pixomatic.WeatherInfo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guid implements Serializable
{

    @SerializedName("isPermaLink")
    @Expose
    private String isPermaLink;
    private final static long serialVersionUID = 2445124754968655260L;

    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

}
