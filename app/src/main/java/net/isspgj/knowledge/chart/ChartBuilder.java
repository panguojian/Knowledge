package net.isspgj.knowledge.chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pgj on 2021/3/11
 **/
public class ChartBuilder {
    private LineChart lineChart;
    private LineData mLineData;

    private List<Entry> lineListX = new ArrayList<>();
    private List<Entry> lineListY = new ArrayList<>();
    private List<Entry> lineListZ = new ArrayList<>();

    private List<String> xData = new ArrayList<>();


    private void setAxis(){
        YAxis rightAxis = lineChart.getAxisRight();
        YAxis leftAxis = lineChart.getAxisLeft();

        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);

        rightAxis.setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#333333"));
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(0f);

        xAxis.setDrawAxisLine(true);

        xAxis.setDrawGridLines(true);

        xAxis.setDrawLabels(true);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(valueFormatter);

        xAxis.setAvoidFirstLastClipping(true);

        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
    }

    private void setDescription(){
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);
    }

    private void collection(){
        Collections.sort(lineListX, new EntryXComparator());
        Collections.sort(lineListY, new EntryXComparator());
        Collections.sort(lineListZ, new EntryXComparator());
    }

    public void initStateChart(LineChart chart) {

        this.lineChart = chart;
        collection();
        setDescription();

        lineChart.setBackgroundColor(Color.rgb(255, 255, 255));
        lineChart.setScaleEnabled(false);

        LineDataSet dataSetX = getXLineDataSet();
        LineDataSet dataSetY = getYLineDataSet();
        LineDataSet dataSetZ = getZLineDataSet();

        setAxis();

        mLineData = new LineData(dataSetX);
        mLineData.addDataSet(dataSetY);
        mLineData.addDataSet(dataSetZ);

        mLineData.setDrawValues(false);

        lineChart.setData(mLineData);
        lineChart.animateX(500);
        lineChart.setNoDataText("暂无数据");
        lineChart.invalidate();
    }

    private LineDataSet getXLineDataSet(){
        return getLineDataSet(lineListX, Color.RED,"ACC_X");
    }

    private LineDataSet getYLineDataSet(){
        return getLineDataSet(lineListY, Color.BLUE,"ACC_Y");
    }

    private LineDataSet getZLineDataSet(){
        return getLineDataSet(lineListZ, Color.GREEN,"ACC_Z");
    }

    /**
     *
     * @param list 数据集合
     * @param color 线条颜色
     * @param label 线条标签
     * @return
     */
    private LineDataSet getLineDataSet(List<Entry> list, int color, String label){
        LineDataSet dataSet = new LineDataSet(list, label);
        dataSet.setColor(color);
        dataSet.setCircleColor(Color.GREEN);
        dataSet.setCircleRadius(1.5f);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setFillColor(Color.GREEN);
        dataSet.setFillAlpha(50);

        dataSet.setLineWidth(1.7f);
        return dataSet;
    }

    private ValueFormatter valueFormatter = new ValueFormatter() {
        @Override
        public String getFormattedValue(float value) {
            int p = (int) value;
            if (p < xData.size() && p > -1) {
                return xData.get(p);
            } else {
                return "";
            }
        }
    };

    /**
     *
     * @param xValue x轴的值
     * @param accX x方向的加速度
     * @param accY y方向的加速度
     * @param accZ z方向的加速度
     */
    public void addEntry(String xValue, float accX, float accY, float accZ) {

        xData.add(xValue);

        Entry entryX = new Entry(xData.size(), accX);
        Entry entryY = new Entry(xData.size(), accY);
        Entry entryZ = new Entry(xData.size(), accZ);

        mLineData.addEntry(entryX, 0);
        mLineData.addEntry(entryY, 1);
        mLineData.addEntry(entryZ, 2);

        mLineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.moveViewToAnimated(xData.size(), 0, YAxis.AxisDependency.RIGHT, 500);
    }
}
