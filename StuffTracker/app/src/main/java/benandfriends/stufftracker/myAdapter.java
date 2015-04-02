package benandfriends.stufftracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gavinfarnsworth on 3/31/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    public MyAdapter(String[] myDataset){

        mDataset = myDataset;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        ViewHolder vh = new ViewHolder((TextView) v);
        
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
