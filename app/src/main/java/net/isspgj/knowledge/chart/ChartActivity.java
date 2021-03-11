package net.isspgj.knowledge.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

import net.isspgj.knowledge.R;

public class ChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    private LineChart lineChart2;
    private ChartUtils chartUtils;
    private ChartBuilder chartBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initView();
        chartUtils = new ChartUtils();
        chartUtils.initStateChart(lineChart);

        for (int i = 0; i < 10; i++) {
            chartUtils.addEntry(i + "", i);
        }

        chartBuilder = new ChartBuilder();
        chartBuilder.initStateChart(lineChart2);
        for (int x = 0,y = 5,z = 10;x<50;x++,y++,z++){
            chartBuilder.addEntry("",x,y,z);
        }
    }

    private void initView() {
        lineChart = findViewById(R.id.lc_test);
        lineChart2 = findViewById(R.id.lc_test2);
    }
}