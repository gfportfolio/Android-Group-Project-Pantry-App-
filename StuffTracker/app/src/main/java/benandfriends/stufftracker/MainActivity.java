package benandfriends.stufftracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller.load();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.Adapter mAdapter = new MainAdapter(makeArrayListOfItems(), this);
        mRecyclerView.setAdapter(mAdapter);
    }


    private ArrayList<ItemMain> makeArrayListOfItems(){
        ArrayList<ItemMain> list = new ArrayList<>();
        ItemMain items = new ItemMain();
        items.Count=Controller.getItemCount();
        items.Title=getString(R.string.items);
        items.Image = R.drawable.item;

        ItemMain containers = new ItemMain();
        containers.Title=getString(R.string.containers);
        containers.Count=Controller.getLocationsCount();
        containers.Image=R.drawable.location;

        list.add(items);
        list.add(containers);
        return list;
    }


}
