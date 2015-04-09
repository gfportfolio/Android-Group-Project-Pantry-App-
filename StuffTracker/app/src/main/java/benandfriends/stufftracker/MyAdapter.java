package benandfriends.stufftracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by gavinfarnsworth on 3/31/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private ArrayList<Item> myDataset;

    

    /*public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }*/

    public MyAdapter(ArrayList<Item> myDataset){

       this.myDataset = myDataset;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        ItemViewHolder vh = new ItemViewHolder( v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item i = myDataset.get(position);
        holder.vTitle.setText(i.getName());
        holder.vName.setText(i.getName());
        holder.vLocation.setText(i.getLocation().getName());
        if(i.getDateExpires()!=null){
            String date =android.text.format.DateFormat.format("MM-dd-yyyy", i.getDateExpires()).toString();
            holder.vExpDate.setText(date);
        }
        else{
            holder.vExpDate.setText("");
        }

    }


    @Override
    public int getItemCount() {
        return myDataset.size();
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vLocation;
        protected TextView vExpDate;
        protected TextView vTitle;

        public ItemViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.itemName);
            vLocation = (TextView) v.findViewById(R.id.itemLocation);
            vExpDate = (TextView) v.findViewById(R.id.itemExpDate);
            vTitle = (TextView) v.findViewById(R.id.title);

        }
    }
}
