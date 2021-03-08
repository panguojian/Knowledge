package net.isspgj.knowledge.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

import net.isspgj.knowledge.R;

public class ChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    private ChartUtils chartUtils;

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
    }

    private void initView() {
        lineChart = findViewById(R.id.lc_test);
    }
}