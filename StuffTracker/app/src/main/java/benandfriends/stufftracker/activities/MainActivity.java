package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.LocationsAdapter;


public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = (RecyclerView) findViewById(R.id.locations_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.recyclerView.setHasFixedSize(true);

        this.adapter = new LocationsAdapter(this.getMockDataSet());
        this.recyclerView.setAdapter(this.adapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.attachToRecyclerView(this.recyclerView);
    }


    private ArrayList<String> getMockDataSet() {
        ArrayList<String> dataSet = new ArrayList<>();
        for (int i=0; i<10; i++) {
            dataSet.add("Test " + i);
        }
        return dataSet;
    }


}
