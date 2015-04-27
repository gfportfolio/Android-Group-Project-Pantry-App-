package benandfriends.stufftracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.adapters.ContainersAdapter;


public class ContainerListActivity extends FabListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.fab.setImageResource(R.drawable.locationadd);
    }


    @Override
    protected RecyclerView.Adapter instantiateRecyclerViewAdapter() {
        return new ContainersAdapter(this);
    }


    @Override
    protected View.OnClickListener getFabClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContainerListActivity.this, CreateContainerActivity.class);
                startActivityForResult(intent, 1);
            }
        };
    }


}
