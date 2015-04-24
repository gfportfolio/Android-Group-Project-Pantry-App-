package benandfriends.stufftracker.utilities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.Container;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.activities.ItemListActivity;


public final class ContainersAdapter extends RecyclerView.Adapter<ContainersAdapter.ViewHolder> {


    private Context context;


    public ContainersAdapter(Context context) {
        this.context = context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtFooter;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtFooter = (TextView) v.findViewById(R.id.title);
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
        final Container c = Application.getApplicationInstance().getContainers().get(position);
        final String name = c.getName();
        final Bitmap image = c.getImage();
        holder.txtFooter.setText(name);
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


    @Override
    public int getItemCount() {
        return Application.getApplicationInstance().getContainers().size();
    }


}
