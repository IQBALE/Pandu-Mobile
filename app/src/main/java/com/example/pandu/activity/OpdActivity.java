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
import com.example.pandu.entity.OpdEntity;
import com.example.pandu.network.APIService;
import com.example.pandu.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpdActivity extends AppCompatActivity {
    AnyChartView  opd;
    APIService service;

    MyViewModel viewModel;

    List<Integer> jmlhOpd = new ArrayList<>();
    List<String> Opd = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_opd);
        getSupportActionBar().setTitle("OPD Pengampu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        opd = findViewById(R.id.opd);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        viewModel.getDataOpd();

        viewModel.getOpd().observe(this, opd->{
            if(opd != null){
                for (OpdEntity o:opd){
                    Opd.add(o.getOPD_PENGAMPU());
                    jmlhOpd.add(o.getOpd());
                }
                opdView();
            }
        });

    }

    private void opdView() {
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i=0; i<Opd.size(); i++){
           dataEntries.add(new ValueDataEntry(Opd.get(i), jmlhOpd.get(i)));
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

        cartesian.xAxis(0).title("OPD Pengampu");
        cartesian.yAxis(0).title("Total Data");

        opd.setChart(cartesian);
    }
}