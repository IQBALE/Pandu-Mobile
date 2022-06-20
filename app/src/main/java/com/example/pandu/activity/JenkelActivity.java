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
import com.anychart.charts.Pie;
import com.example.pandu.R;
import com.example.pandu.entity.JenkelEntity;
import com.example.pandu.network.APIService;
import com.example.pandu.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JenkelActivity extends AppCompatActivity {
    AnyChartView jenkel;
    APIService service;

    MyViewModel viewModel;

    List<Integer> totalJK = new ArrayList<>();
    List<String> dataJK = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_jk);
        getSupportActionBar().setTitle("Jenis Kelamin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jenkel = findViewById(R.id.jenkel);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        viewModel.getDataGender();

        viewModel.getGender().observe(this, gender->{
            if (gender != null){
                for(JenkelEntity j: gender){
                    totalJK.add(j.getJK());
                    dataJK.add(j.getJENIS_KELAMIN());
                }
                jenkelView();
            }
        });
    }

    private void jenkelView() {
        Pie pie = AnyChart.pie();

        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0; i<dataJK.size(); i++){
            dataEntries.add(new ValueDataEntry(dataJK.get(i), totalJK.get(i)));
        }
        Log.d("test", dataJK.toString());
        Log.d("test", totalJK.toString());

        pie.data(dataEntries);
        jenkel.setChart(pie);

    }

}