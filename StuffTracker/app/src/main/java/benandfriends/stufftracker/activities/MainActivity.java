package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.LocationsAdapter;


public class MainActivity extends Activity {

    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.adapter = new LocationsAdapter(this.getMockDataSet());
        this.setUpViews();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }


    private ArrayList<String> getMockDataSet() {
        ArrayList<String> dataSet = new ArrayList<>();
        for (int i=0; i<10; i++) {
            dataSet.add("Test " + i);
        }
        return dataSet;
    }


    private void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.locations_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(this.adapter);

        this.instantiateFab(recyclerView);
    }


    private void instantiateFab(RecyclerView recyclerView) {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateContainerActivity.class);
                startActivity(intent);
            }
        });
    }


}
