package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.ContainersAdapter;


public class ContainerListActivity extends Activity {

    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_list);

        this.adapter = new ContainersAdapter(this.getMockDataSet());
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.containers_recycler_view);

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
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
                Intent intent = new Intent(ContainerListActivity.this, CreateContainerActivity.class);
                startActivity(intent);
            }
        });
    }


}
