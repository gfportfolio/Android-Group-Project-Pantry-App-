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
    private Location location;
    private Boolean opened;
    private Date dateBaught;
    private Date dateExpires;
    private Boolean notifyWhenExpiring;

    public Item(String name, int id, String upc, Location location, Boolean opened, Date dateBaught) {
        this.name = name;
        this.id = id;
        this.upc = upc;
        this.location = location;
        this.opened = opened;
        this.dateBaught = dateBaught;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean isOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Date getDateBaught() {
        return dateBaught;
    }

    public void setDateBaught(Date dateBaught) {
        this.dateBaught = dateBaught;
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
