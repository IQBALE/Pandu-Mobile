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
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.pandu.R;
import com.example.pandu.entity.DomisiliEntity;
import com.example.pandu.network.APIService;
import com.example.pandu.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomisiliActivity extends AppCompatActivity {
    AnyChartView status;
    APIService service;

    MyViewModel viewModel;

    List<Integer> jmlStatus = new ArrayList<>();
    List<String> statDomisili= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_st);
        getSupportActionBar().setTitle("Status Domisili");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        status = findViewById(R.id.status);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        viewModel.getDataDomisili();

        viewModel.getDomisili().observe(this, domisili->{
            if(domisili != null){
                for (DomisiliEntity d: domisili){
                    statDomisili.add(d.getSTATUS());
                    jmlStatus.add(d.getStat());
                }
                statusView();
            }
        });
    }



    private void statusView() {
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0; i<statDomisili.size(); i++){
            dataEntries.add(new ValueDataEntry(statDomisili.get(i), jmlStatus.get(i)));
        }

        Column column = cartesian.column(dataEntries);
        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);

        cartesian.yScale().minimum(0d);
        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Status Domisili");
        cartesian.yAxis(0).title("Total Data");

        status.setChart(cartesian);
    }
}