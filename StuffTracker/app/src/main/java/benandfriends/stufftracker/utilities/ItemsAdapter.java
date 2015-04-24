package benandfriends.stufftracker.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.Container;


public class ItemsAdapter extends RecyclerView.Adapter<ContainersAdapter.ViewHolder> {


    private Context context;
    private int parentContainerId;


    public ItemsAdapter(Context context, int position) {
        this.context = context;
        this.parentContainerId = position;
    }


    @Override
    public ContainersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(ContainersAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        Container parent = Application.getApplicationInstance().getContainers().get(parentContainerId);
        return parent.getItems().size();
    }


}
