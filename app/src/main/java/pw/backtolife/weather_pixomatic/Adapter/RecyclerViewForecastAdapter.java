package pw.backtolife.weather_pixomatic.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pw.backtolife.weather_pixomatic.R;
import pw.backtolife.weather_pixomatic.WeatherInfo.Forecast;

/**
 * Created by Acer on 2/6/2018.
 */

public class RecyclerViewForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Forecast> itemList;

    public RecyclerViewForecastAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast,
                null);
        return new ForecastViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ForecastViewHolder) holder).day.setText(itemList.get(position).getDay());
        ((ForecastViewHolder) holder).condition.setText(itemList.get(position).getText());
        ((ForecastViewHolder) holder).low.setText(itemList.get(position).getLow());
        ((ForecastViewHolder) holder).high.setText(itemList.get(position).getHigh());
    }

    public void setData(List<Forecast> forecasts) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        this.itemList.addAll(forecasts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public List<Forecast> getItemList() {
        return itemList;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView day;
        private AppCompatTextView condition;
        private AppCompatTextView low;
        private AppCompatTextView high;

        ForecastViewHolder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.txt_day);
            condition = itemView.findViewById(R.id.txt_text);
            low = itemView.findViewById(R.id.txt_low);
            high = itemView.findViewById(R.id.txt_high);
        }
    }
}
