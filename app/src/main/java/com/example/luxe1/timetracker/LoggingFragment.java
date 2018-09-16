package com.example.luxe1.timetracker;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.github.mikephil.charting.charts.PieChart;

public class LoggingFragment extends Fragment {

    private static final String TAG = "LoggingFragment";

    private Button sleeping;
    private Button studying;
    private Button exercising;
    private Button miscing;

    private Chronometer chronometer;

    private StatisticsFragment m;

    private boolean isStudying = false;
    private boolean isSleeping = false;
    private boolean isExercising = false;
    private boolean isMiscing = false;
    public static long timeStudying = 0;
    public static long timeSleeping = 0;
    public static long timeExercising = 0;
    public static long timeMiscing = 0;
    public static long sum = 0;

    public void setStats(StatisticsFragment stats) {
        m=stats;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.logging_fragment, container, false);
        View statView = inflater.inflate(R.layout.statistic_fragment,container,false);

        chronometer = view.findViewById(R.id.chronometer);
        final PieChart pieChart = (PieChart) statView.findViewById(R.id.pie_chart);



        sleeping = (Button)view.findViewById(R.id.sleep);
        exercising = (Button) view.findViewById(R.id.exercise);
        studying = (Button) view.findViewById(R.id.study);
        miscing = (Button) view.findViewById(R.id.misc);

        chronometer = (Chronometer) view.findViewById(R.id.chronometer);

        sleeping.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                if(isSleeping == false && (isMiscing == true || isExercising == true || isStudying == true)) {
                    isSleeping = false;
                }else if(isSleeping == false && isMiscing == false && isExercising == false && isStudying == false) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    isSleeping = true;
                } else if(isSleeping == true){
                    isSleeping = false;
                    chronometer.stop();
                    timeSleeping = timeSleeping + (SystemClock.elapsedRealtime() - chronometer.getBase());
                    sum = timeMiscing + timeSleeping + timeExercising + timeStudying;
                    m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                }
            }
        });

        exercising.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                if(isExercising == false && (isMiscing == true || isStudying == true || isSleeping == true)) {
                    isExercising = false;
                }else if(isExercising == false && isMiscing == false && isStudying == false && isSleeping == false) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    isExercising = true;
                } else if(isExercising == true){
                    isExercising = false;
                    chronometer.stop();
                    timeExercising = timeExercising + (SystemClock.elapsedRealtime() - chronometer.getBase());
                    sum = timeMiscing + timeSleeping + timeExercising + timeStudying;
                    m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                }
            }
        });

        studying.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                if(isStudying == false && (isMiscing == true || isExercising == true || isSleeping == true)) {
                    isStudying = false;
                }else if(isStudying == false && isMiscing == false && isExercising == false && isSleeping == false) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    isStudying = true;
                } else if(isStudying == true){
                    isStudying = false;
                    chronometer.stop();
                    timeStudying = timeStudying + (SystemClock.elapsedRealtime() - chronometer.getBase());
                    sum = timeMiscing + timeSleeping + timeExercising + timeStudying;
                    m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                }
            }
        });

        miscing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                if(isMiscing == false && (isStudying == true || isExercising == true || isSleeping == true)) {
                    isMiscing = false;
                }else if(isMiscing == false && isStudying == false && isExercising == false && isSleeping == false) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    isMiscing = true;
                } else if(isMiscing== true){
                    isMiscing = false;
                    chronometer.stop();
                    timeMiscing = timeMiscing + (SystemClock.elapsedRealtime() - chronometer.getBase());
                    sum = timeMiscing + timeSleeping + timeExercising + timeStudying;
                    m.getValue(sum, timeSleeping, timeStudying, timeExercising, timeMiscing, pieChart);
                }
            }
        });

        return view;


    }

   /* public long getTimeSleeping(){
        return timeSleeping;
    }

    public long getTimeStudying(){
        return timeStudying;
    }

    public long getTimeExercising(){
        return timeExercising;
    }

    public long getTimeMiscing(){
        return timeMiscing;
    }

    public long getSum(){
        return sum;
    }*/

    /*public void onClick(View v) {
        switch(v.getId()) {

            case R.id.study:
                isStudying = true;
                break;
            case R.id.sleep:
                isSleeping = true;
                break;
            case R.id.exercise:
                isExercising = true;
                break;
            case R.id.misc:
                isMiscing = true;
                break;

        }
    }

    public void startChronometer(View v) {

        if (!running) {

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

        }

    }

    public void pauseChronometer(View w) {

        if (running) {

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;

        }

    }

    public void resetChronometer(View v) {

        if (isStudying) {

            timeStudying = chronometer.getBase();
            isStudying = false;

        }

        else if (isSleeping) {

            timeSleeping = chronometer.getBase();
            isSleeping = false;

        }

        else if (isExercising) {

            timeExercising = chronometer.getBase();
            isExercising = false;

        }

        else if (isMiscing) {

            timeMiscing = chronometer.getBase();
            isMiscing = false;

        }

        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;

    }*/

}



