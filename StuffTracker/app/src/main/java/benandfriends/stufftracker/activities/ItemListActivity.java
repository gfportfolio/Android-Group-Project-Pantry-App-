package benandfriends.stufftracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.ItemMain;
import benandfriends.stufftracker.utilities.adapters.ItemsAdapter;


public class ItemListActivity extends FabListActivity {


    private int parentContainerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.fab.setImageResource(R.drawable.itemadd);
        Intent i = getIntent();
        if (null != i) {
            parentContainerId = i.getIntExtra(POSITION_KEY, -1);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        this.adapter.notifyDataSetChanged();
    }


    @Override
    protected RecyclerView.Adapter instantiateRecyclerViewAdapter() {
        return new ItemsAdapter(this, this.position);
    }

    @Override
    protected View.OnClickListener getFabClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemListActivity.this, CreateItemActivity.class);
                intent.putExtra(ItemsAdapter.CONTAINER_PARENT_ID_KEY, parentContainerId);
                startActivityForResult(intent, 1);
            }
        };
    }


}
