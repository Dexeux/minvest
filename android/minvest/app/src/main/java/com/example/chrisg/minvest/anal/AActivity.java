package com.example.chrisg.minvest.anal;

import android.os.Bundle;

import com.example.chrisg.minvest.R;
import com.example.chrisg.minvest.drawer.DrawerActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.chrisg.minvest.R.id.graph;

/**
 * Created by chrisg on 11/13/16.
 */

public class AActivity extends DrawerActivity {
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_anal);
        initDrawer();

        GraphView view = (GraphView)findViewById(graph);
        view.setTitle("Investment History");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        view.addSeries(series);
    }
}
