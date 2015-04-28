package benandfriends.stufftracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.ItemMain;
import benandfriends.stufftracker.utilities.Search;
import benandfriends.stufftracker.utilities.adapters.ItemsAdapter;


public class ItemListActivity extends FabListActivity {


    private int parentContainerId;
    private EditText searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.fab.setImageResource(R.drawable.itemadd);
        Intent i = getIntent();
        if (null != i) {
            parentContainerId = i.getIntExtra(POSITION_KEY, -1);
        }
        searchbar = (EditText)findViewById(R.id.search_edit_text);
        searchbar.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ItemsAdapter adapt = (ItemsAdapter) adapter;
                String searchText = searchbar.getText().toString();
                if (searchText!=null && !searchText.equals("") && searchText.length() > 0) {
                    ArrayList<Item> search = Search.searchListFor(adapt.getItems(), searchbar.getText().toString());
                    adapt.setSearch(search);
                }
                else{
                    adapt.setSearch(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
