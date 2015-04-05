package benandfriends.stufftracker.models;

import android.graphics.Bitmap;

import java.util.Date;


public final class Item {

    private String name = "";
    private Bitmap image;
    private Boolean opened;
    private Date datePurchased;
    private Date dateExpires;
    private Boolean notifyWhenExpiring;


    public Item(String name) {
        this.setName(name);
    }


    public String getName() {
        return name;
    }


    public Bitmap getImage() {
        return image;
    }


    public Boolean getOpened() {
        return opened;
    }


    public Date getDatePurchased() {
        return datePurchased;
    }


    public Date getDateExpires() {
        return dateExpires;
    }


    public Boolean getNotifyWhenExpiring() {
        return notifyWhenExpiring;
    }


    public void setName(String name) {
        if (null == name || name.isEmpty()) {
            return;
        }
        this.name = name;
    }


    public void setImage(Bitmap image) {
        this.image = image;
    }


    public void setOpened(Boolean opened) {
        this.opened = opened;
    }


    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }


    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }


    public void setNotifyWhenExpiring(Boolean notifyWhenExpiring) {
        this.notifyWhenExpiring = notifyWhenExpiring;
    }


}
