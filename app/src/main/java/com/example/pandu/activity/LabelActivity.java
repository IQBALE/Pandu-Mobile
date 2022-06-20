package com.example.pandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.pandu.R;
import com.example.pandu.entity.DomisiliEntity;
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

public class LabelActivity extends AppCompatActivity implements StatusCallback {
    AnyChartView lbl;
    APIService service;

    MyViewModel viewModel;

    List<String> dataLbl = new ArrayList<>();
    List<Integer> jmlhtotal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_lbl);
        getSupportActionBar().setTitle("Status Penerima");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lbl = findViewById(R.id.label);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        viewModel.getDataLabel();

        viewModel.getLabel().observe(this, label->{
            if(label != null){
                for (LabelEntity l: label){
                    dataLbl.add(l.getLABEL());
                    jmlhtotal.add(l.getLBL());
                }
                lvlView();
            }
        });
    }

    private void lvlView() {
        Pie pie = AnyChart.pie();

        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i=0; i<dataLbl.size(); i++){
            dataEntries.add(new ValueDataEntry(dataLbl.get(i), jmlhtotal.get(i)));
        }
        pie.data(dataEntries);
        lbl.setChart(pie);
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
}