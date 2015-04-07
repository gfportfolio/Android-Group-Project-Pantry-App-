package benandfriends.stufftracker.models;

import android.graphics.Bitmap;

import java.util.Date;


public final class Item {

    private String name = "";
    private String upc;
    private Bitmap image;
    private Boolean isOpened;
    private Date datePurchased;
    private Date dateExpires;


    public Item(String name) {
        this.setName(name);
    }


    public String getName() {
        return name;
    }


    public String getUpc() {
        return upc;
    }


    public Bitmap getImage() {
        return image;
    }


    public Boolean getIsOpened() {
        return isOpened;
    }


    public Date getDatePurchased() {
        return datePurchased;
    }


    public Date getDateExpires() {
        return dateExpires;
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


    public void setIsOpened(Boolean isOpened) {
        this.isOpened = isOpened;
    }


    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }


    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }



}
