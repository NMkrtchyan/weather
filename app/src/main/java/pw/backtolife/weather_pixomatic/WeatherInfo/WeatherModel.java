package pw.backtolife.weather_pixomatic.WeatherInfo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pw.backtolife.weather_pixomatic.WeatherInfo.Query;

public class WeatherModel implements Serializable
{

    @SerializedName("query")
    @Expose
    private Query query;
    private final static long serialVersionUID = -8796148167171622615L;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }


}
