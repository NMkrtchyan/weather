package pw.backtolife.weather_pixomatic.Adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pw.backtolife.weather_pixomatic.CityInfo.CityModel;
import pw.backtolife.weather_pixomatic.R;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityModel> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city,
                null);
        return new CityViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CityViewHolder) holder).city.setText(itemList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void setData(List<CityModel> cityList) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        this.itemList.addAll(cityList);
        notifyDataSetChanged();
    }

    public List<CityModel> getItemList() {
        return itemList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View item, int position);
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView city;

        CityViewHolder(View itemView) {
            super(itemView);

            city = (AppCompatTextView) itemView.findViewById(R.id.txtCity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, getAdapterPosition());
                }
            });

        }
    }
}

