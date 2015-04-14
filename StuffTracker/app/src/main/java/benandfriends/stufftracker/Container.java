package benandfriends.stufftracker;

import android.graphics.Bitmap;
import android.location.Location;

/**
 * Created by gavinfarnsworth on 3/26/15.
 */
public class Container {
    private String name;
    private Bitmap image;
    private Location location;


    public Container(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
