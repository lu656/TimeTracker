package com.example.luxe1.timetracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private static final String TAG = "LoggingFragment";

 //   private List<PieEntry> entries = new ArrayList<>();
//    private LoggingFragment v = new LoggingFragment();
    private PieChart pieChart;
/*    PieDataSet set = new PieDataSet(entries, "Time Spent");
    PieData data = new PieData(set);*/
    ArrayList<Integer>colors = new ArrayList<Integer>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.statistic_fragment, container, false);

        pieChart = view.findViewById(R.id.pie_chart);

        pieChart.setBackgroundColor(Color.LTGRAY);
        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setHoleRadius(25);
        pieChart.setRotation(0);
        pieChart.setRotationEnabled(true);

        for(int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);




       //getValue(1,2,3,4,5,pieChart);

        return view;
    }
    public void getValue(long sum, long sleep, long study, long exercise, long misc, PieChart pieCharto){
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();


        entries.clear();

            if(exercise != 0){
                entries.add(new PieEntry((exercise * 100.0f)/sum, "Exercising"));
            }
            if(study != 0){
                entries.add(new PieEntry((study * 100.0f)/sum, "Studying"));
            }
            if(sleep != 0){
                entries.add(new PieEntry((sleep * 100.0f)/sum, "Sleeping"));
            }
            if(misc != 0){
                entries.add(new PieEntry((misc * 100.0f)/sum, "Misc"));
            }
            PieDataSet set = new PieDataSet(entries, "time spent");
            PieData data = new PieData(set);
            set.setSliceSpace(3);
            set.setSelectionShift(5);
            set.setColors(colors);
            data.setValueTextSize(40);
            data.setValueTextColor(Color.BLACK);
            pieChart.setData(data);
            data.notifyDataChanged();
            pieChart.notifyDataSetChanged();
            pieChart.invalidate();

        }
}

