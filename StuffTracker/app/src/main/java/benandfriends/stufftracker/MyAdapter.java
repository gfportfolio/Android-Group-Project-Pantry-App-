package benandfriends.stufftracker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gavinfarnsworth on 3/31/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements View.OnClickListener{

    private ArrayList<ItemMain> myDataSet;


    public MyAdapter(ArrayList<ItemMain> myDataSet){
       this.myDataSet = myDataSet;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        v.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch(v.getTag().toString()) {
            case "hi":
                break;
            default:
                break;
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vLocation;
        protected ImageView vImg;

        public ItemViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.itemName);
            vLocation = (TextView) v.findViewById(R.id.itemCount);
            vImg = (ImageView) v.findViewById(R.id.imageView);
        }
    }


}
