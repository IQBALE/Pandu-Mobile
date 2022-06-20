package com.example.pandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.pandu.R;
import com.example.pandu.entity.LabelEntity;
import com.example.pandu.entity.UsiaEntity;
import com.example.pandu.helper.StatusCallback;
import com.example.pandu.network.APIService;
import com.example.pandu.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsiaActivity extends AppCompatActivity implements StatusCallback {
    AnyChartView usia;
    APIService service;

    MyViewModel viewModel;

    List<Integer> NoUsia = new ArrayList<>();
    List<Integer> totalLaki = new ArrayList<>();
    List<Integer> totalPerempuan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_usia);
        getSupportActionBar().setTitle("Persebaran Usia Penerima");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        usia = findViewById(R.id.usia);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        viewModel.getDataUsia();

        viewModel.getUsia().observe(this, usia -> {
            if(usia != null) {
                for(UsiaEntity u:usia){
                    NoUsia.add(u.getUSIA());
                    totalLaki.add(u.getL());
                    totalPerempuan.add(u.getP());
                }
                usiaView();
            }
        });

    }

    private void usiaView() {
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);

        cartesian.padding(10d, 20d, 15d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.yAxis(0).title("Total Penerima");
        cartesian.xAxis(0).title("Usia Penerima");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d,5d);

        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0; i<NoUsia.size(); i++){
            dataEntries.add(new CustomDataEntry(NoUsia.get(i), totalLaki.get(i), totalPerempuan.get(i)));
        }

        Set set = Set.instantiate();
        set.data(dataEntries);
        Mapping series1Mapping = set.mapAs("{x : 'x', value: 'value'}");
        Mapping series2Mapping = set.mapAs("{x : 'x', value: 'value2'}");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Laki-Laki");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Perempuan");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d,0d,10d,0d);

        usia.setChart(cartesian);
    }

    @Override
    public void loading() {
        Toast.makeText(getApplicationContext(), "Sedang Loading", Toast.LENGTH_LONG).show();
    }

    @Override
    public void success() {
        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(String message) {
        Toast.makeText(getApplicationContext(), "Error:"+ message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void empty() {
        Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
    }


    private class CustomDataEntry extends ValueDataEntry{
        CustomDataEntry(Number x, Number value, Number value2){
            super(x, value);
            setValue("value2", value2);
        }
    }
}