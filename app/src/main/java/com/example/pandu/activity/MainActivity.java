package com.example.pandu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pandu.R;

public class MainActivity extends AppCompatActivity {
    private CardView card1, card2, card3, card4, card5, card6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.information);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   openInformation();
            }
        });

        card2 = (CardView) findViewById(R.id.visual_jk);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openV_jk();
            }
        });

        card3 = (CardView) findViewById(R.id.visual_opd);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openV_opd(); }
        });

        card4 = (CardView) findViewById(R.id.visual_lbl);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openV_lbl(); }
        });

        card5 = (CardView) findViewById(R.id.visual_status);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openV_status();
            }
        });

        card6 = (CardView) findViewById(R.id.visual_usia);
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openV_usia();
            }
        });
    }
    public void openInformation(){
        Intent intent= new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
    public void openV_jk(){
        Intent intent = new Intent(this, JenkelActivity.class);
        startActivity(intent);
    }
    public void openV_opd(){
        Intent intent = new Intent(this, OpdActivity.class);
        startActivity(intent);
    }
    public void openV_lbl(){
        Intent intent = new Intent(this, LabelActivity.class);
        startActivity(intent);
    }
    public void openV_status(){
        Intent intent = new Intent(this, DomisiliActivity.class);
        startActivity(intent);
    }
    public void openV_usia(){
        Intent intent = new Intent(this, UsiaActivity.class);
        startActivity(intent);
    }
}