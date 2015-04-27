package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.utilities.ItemMain;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.adapters.ItemsAdapter;
import benandfriends.stufftracker.utilities.adapters.MainAdapter;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new MainAdapter(makeArrayListOfItems(), this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mainfab);
        fab.attachToRecyclerView(mRecyclerView);

        fab.setOnClickListener(getFabClickListener());
    }


    private ArrayList<ItemMain> makeArrayListOfItems(){
        ArrayList<ItemMain> list = new ArrayList<>();
        ItemMain items = new ItemMain();
        items.Count= Application.getApplicationInstance(this).getAllItems().size();
        items.Title=getString(R.string.items);
        items.Image = R.drawable.item;

        ItemMain containers = new ItemMain();
        containers.Title=getString(R.string.containers);
        containers.Count=Application.getApplicationInstance(this).getContainers().size();
        containers.Image=R.drawable.location;

        list.add(containers);

        list.add(items);
        return list;
    }

    protected View.OnClickListener getFabClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateItemActivity.class);
                startActivity(intent);
            }
        };
    }

}
