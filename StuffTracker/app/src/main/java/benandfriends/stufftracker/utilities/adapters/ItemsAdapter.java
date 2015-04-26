package benandfriends.stufftracker.utilities.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.activities.CreateItemActivity;
import benandfriends.stufftracker.activities.FabListActivity;
import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {


    public static final String CONTAINER_PARENT_ID_KEY = "ParentContainerId";

    private Context context;
    private int parentContainerId;


    public ItemsAdapter(Context context, int position) {
        this.context = context;
        this.parentContainerId = position;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView purchaseDateTextView;
        public TextView expirationDateTextView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.title);
            purchaseDateTextView = (TextView) v.findViewById(R.id.purchased_on_date);
            expirationDateTextView = (TextView) v.findViewById(R.id.expiration_date);
            imageView = (ImageView) v.findViewById(R.id.icon);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        Container parent = Application.getApplicationInstance(context).getContainers().get(parentContainerId);
        final Item item = parent.getItems().get(position);
        final String name = item.getName();
        final Bitmap image = item.getImage();

        setExpirationText(item, holder);
        setPurchasedText(item, holder);

        holder.titleTextView.setText(name);
        if (null != image) {
            holder.imageView.setImageBitmap(image);
        }

        final int itemPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CreateItemActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(FabListActivity.POSITION_KEY, itemPosition);
                i.putExtra(CONTAINER_PARENT_ID_KEY, parentContainerId);
                ((Activity)context).startActivityForResult(i, 1);
            }
        });
    }


    @Override
    public int getItemCount() {
        Container parent = Application.getApplicationInstance(context).getContainers().get(parentContainerId);
        return parent.getItems().size();
    }


    private void setExpirationText(Item item, ViewHolder holder) {
        Date expiration = item.getDateExpires();
        final String expireString = context.getString(R.string.expires_on);
        if (null != expiration) {
            final String expirationDate = expireString + " " + Application.APP_DATE_FORMAT.format(expiration);
            holder.expirationDateTextView.setText(expirationDate);
        } else {
            holder.expirationDateTextView.setText(expireString + " ?");
        }
    }


    private void setPurchasedText(Item item, ViewHolder holder) {
        Date purchasedOn = item.getDatePurchased();
        final String purchaseString = context.getString(R.string.purchased_on);
        if (null != purchasedOn) {
            final String purchasedOnDate = purchaseString + " " + Application.APP_DATE_FORMAT.format(purchasedOn);
            holder.purchaseDateTextView.setText(purchasedOnDate);
        } else {
            holder.purchaseDateTextView.setText(purchaseString + " ?");
        }
    }


}
