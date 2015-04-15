package benandfriends.stufftracker;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.Date;


/**
 * Created by gavinfarnsworth on 3/26/15.
 */
public class Item {
    private String name;
    private int id;
    private Bitmap image;
    private String upc;
    private Boolean opened;
    private Date dateBought;
    private Date dateExpires;
    private Boolean notifyWhenExpiring;

    public Item(String name, int id, String upc,  Boolean opened, Date dateBought) {
        this.name = name;
        this.id = id;
        this.upc = upc;
        this.opened = opened;
        this.dateBought = dateBought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }


    public Boolean isOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public Date getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public Boolean isNotifyWhenExpiring() {
        return notifyWhenExpiring;
    }

    public void setNotifyWhenExpiring(Boolean notifyWhenExpiring) {
        this.notifyWhenExpiring = notifyWhenExpiring;
    }
}
