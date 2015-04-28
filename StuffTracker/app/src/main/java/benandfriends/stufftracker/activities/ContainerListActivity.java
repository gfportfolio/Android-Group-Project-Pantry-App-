package benandfriends.stufftracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.Search;
import benandfriends.stufftracker.utilities.adapters.ContainersAdapter;
import benandfriends.stufftracker.utilities.adapters.ItemsAdapter;


public class ContainerListActivity extends FabListActivity {

    private EditText searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.fab.setImageResource(R.drawable.locationadd);
        searchbar = (EditText)findViewById(R.id.search_edit_text);
        searchbar.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ContainersAdapter adapt = (ContainersAdapter) adapter;
                String searchText = searchbar.getText().toString();
                if (searchText!=null && !searchText.equals("") && searchText.length() > 0) {
                    ArrayList<Container> search = Search.searchListForContainer(adapt.getItems(), searchbar.getText().toString());
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
