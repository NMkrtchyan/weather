package pw.backtolife.weather_pixomatic.Network;

import android.content.Context;

import java.util.Collections;
import java.util.Map;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import pw.backtolife.weather_pixomatic.WeatherInfo.WeatherModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Acer on 2/6/2018.
 */

public interface RequestManager {

    @GET("v1/public/yql")
    Call<WeatherModel> getWeather(@QueryMap(encoded = true) Map<String, String> options);

    class Factory {
        private static RequestManager service;

        public static RequestManager getInstance(Context context) {
            if (service == null) {

                OkHttpClient client;

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().addInterceptor(interceptor)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://query.yahooapis.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
                service = retrofit.create(RequestManager.class);
                return service;
            } else {
                return service;
            }

        }

    }

}
