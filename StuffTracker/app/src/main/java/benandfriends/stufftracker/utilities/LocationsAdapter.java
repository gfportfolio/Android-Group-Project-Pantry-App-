package benandfriends.stufftracker.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import benandfriends.stufftracker.R;


public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ViewHolder> {

    private ArrayList<String> dataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtFooter = (TextView) v.findViewById(R.id.title);
        }
    }


    public LocationsAdapter(ArrayList<String> dataSet) {
        this.dataSet = dataSet;
    }


    private void remove(String item) {
        int position = dataSet.indexOf(item);
        dataSet.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = dataSet.get(position);
        holder.txtFooter.setText("Title " + name);
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
