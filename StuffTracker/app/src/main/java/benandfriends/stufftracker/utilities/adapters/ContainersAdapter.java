package benandfriends.stufftracker.utilities.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.activities.ItemListActivity;
import benandfriends.stufftracker.models.Container;


public final class ContainersAdapter extends RecyclerView.Adapter<ContainersAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Container> searchList=null;

    public ContainersAdapter(Context context) {
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.title);
            imageView = (ImageView) v.findViewById(R.id.icon);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(searchList==null) {
            final Container c = Application.getApplicationInstance(context).getContainers().get(position);
            final String name = c.getName();
            final Bitmap image = c.getImage();
            holder.titleTextView.setText(name);
            if (null != image) {
                holder.imageView.setImageBitmap(image);
            }

            final int containerPosition = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemListActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra(ItemListActivity.POSITION_KEY, containerPosition);
                    context.startActivity(i);
                }
            });
        }
        else{
            final Container c = searchList.get(position);
            final String name = c.getName();
            final Bitmap image = c.getImage();
            holder.titleTextView.setText(name);
            if (null != image) {
                holder.imageView.setImageBitmap(image);
            }

            final int containerPosition = getSearchedContainerPosition(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemListActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra(ItemListActivity.POSITION_KEY, containerPosition);
                    context.startActivity(i);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if(searchList==null) {
            return Application.getApplicationInstance(context).getContainers().size();
        }
        return searchList.size();
    }

    public void setSearch(ArrayList<Container> containers){
        if(containers==null || containers.size()==0){
            searchList=null;
        }
        else{
            searchList=containers;
        }

        this.notifyDataSetChanged();
    }

    public int getSearchedContainerPosition(int position){
        for(int i = 0;i<Application.getApplicationInstance(context).getContainers().size();i++){
            Container c = Application.getApplicationInstance(context).getContainers().get(i);
            if(c==searchList.get(position)){
                return i;
            }
        }
        return 0;

    }


    public ArrayList<Container> getItems(){
        return new ArrayList(Application.getApplicationInstance(context).getContainers());
    }
}
