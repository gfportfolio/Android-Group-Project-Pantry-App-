package benandfriends.stufftracker.utilities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.activities.ContainerListActivity;
import benandfriends.stufftracker.utilities.ItemMain;

/**
 * Created by gavinfarnsworth on 3/31/15.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder> {

    private ArrayList<ItemMain> myDataSet;
    private static Context context;


    public MainAdapter(ArrayList<ItemMain> myDataSet, Context context){
       this.myDataSet = myDataSet;
       MainAdapter.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ItemViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemMain i = myDataSet.get(position);
        holder.vName.setText(i.getTitle());
        holder.vLocation.setText(i.getCount() + "");
        holder.vImg.setImageResource(i.getImage());
        holder.itemView.setId(position);
    }


    @Override
    public int getItemCount() {
        return myDataSet.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView vName;
        protected TextView vLocation;
        protected ImageView vImg;

        public ItemViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vName = (TextView) v.findViewById(R.id.itemName);
            vLocation = (TextView) v.findViewById(R.id.itemCount);
            vImg = (ImageView) v.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            TextView nameField = (TextView)v.findViewById(R.id.itemName);
            if (null != nameField && null != context) {
                String name = nameField.getText().toString();
                if (name.equals(context.getString(R.string.containers))) {
                    Intent intent = new Intent(context, ContainerListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (name.equals(context.getString(R.string.items))) {
                    // Do nothing for now.
                }
            }
        }
    }

}
