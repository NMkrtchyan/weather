package pw.backtolife.weather_pixomatic.WeatherInfo;


import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results implements Serializable
{

    @SerializedName("channel")
    @Expose
    private Channel channel;
    private final static long serialVersionUID = 2575570036258705744L;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
