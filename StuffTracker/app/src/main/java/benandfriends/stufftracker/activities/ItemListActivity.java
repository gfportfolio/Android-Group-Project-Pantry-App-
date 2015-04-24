package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import benandfriends.stufftracker.utilities.ItemsAdapter;


public class ItemListActivity extends FabListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startActivityForResult(intent, 1);
            }
        };
    }


}
