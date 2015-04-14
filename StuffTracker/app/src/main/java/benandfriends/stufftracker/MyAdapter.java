package benandfriends.stufftracker;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by gavinfarnsworth on 3/31/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements View.OnClickListener{

    private ArrayList<ItemMain> myDataset;

    

    /*public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }*/

    public MyAdapter(ArrayList<ItemMain> myDataset){

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
        ItemMain i = myDataset.get(position);
        holder.vName.setText(i.getTitle());
        holder.vLocation.setText(i.getCount()+"");
        holder.vImg.setImageResource(i.getImage());

    }


    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    @Override
    public void onClick(View v) {

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
