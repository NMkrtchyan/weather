package pw.backtolife.weather_pixomatic.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import pw.backtolife.weather_pixomatic.Adapter.RecyclerViewAdapter;
import pw.backtolife.weather_pixomatic.CityInfo.CityModel;
import pw.backtolife.weather_pixomatic.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_city);

        linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setData(setList());

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View item, int position) {
                Intent intent = new Intent(MainActivity.this, CityWeather.class);
                intent.putExtra("city",
                        recyclerViewAdapter.getItemList().get(position).getCity());
                intent.putExtra("city_short",
                        recyclerViewAdapter.getItemList().get(position).getShort_city());
                startActivity(intent);
            }
        });
    }

    public List<CityModel> setList() {
        ArrayList<CityModel> list = new ArrayList<>();

        list.add(0, new CityModel("Yerevan", "evn"));
        list.add(1, new CityModel("Paris", "prs"));
        list.add(2, new CityModel("Berlin", "brn"));
        list.add(3, new CityModel("Moscow", "msc"));
        list.add(4, new CityModel("Rome", "rm"));
        list.add(5, new CityModel("Amsterdam", "ams"));
        list.add(6, new CityModel("Gyumri", "gmr"));
        list.add(7, new CityModel("New-York", "ny"));
        list.add(8, new CityModel("Los Angeles", "la"));

        return list;
    }

}
