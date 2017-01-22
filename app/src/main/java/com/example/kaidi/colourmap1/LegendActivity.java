package com.example.kaidi.colourmap1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import static com.example.kaidi.colourmap1.R.id.my_toolbar;

public class LegendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legend);


        Toolbar secondtoolbar = (Toolbar) findViewById(R.id.second_toolbar);
        secondtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LegendActivity.this , R.string.app_name , Toast.LENGTH_SHORT).show();

                onClickStartNewActivity2();
            }
        });
    }

    public void onClickStartNewActivity2() {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
