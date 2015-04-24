package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import benandfriends.stufftracker.R;


public abstract class FabListActivity extends Activity {

    public static final String POSITION_KEY = "PositionId";

    protected RecyclerView.Adapter adapter;
    protected int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_list);
        this.checkForPosition();
        this.adapter = instantiateRecyclerViewAdapter();
        setUpViews();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        this.adapter.notifyDataSetChanged();
    }


    private void checkForPosition() {
        Intent intent = getIntent();
        if (null != intent) {
            position = intent.getIntExtra(POSITION_KEY, -1);
        }
    }


    private void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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
        fab.setOnClickListener(getFabClickListener());
    }


    protected abstract RecyclerView.Adapter instantiateRecyclerViewAdapter();

    protected abstract View.OnClickListener getFabClickListener();
}
