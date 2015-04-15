package benandfriends.stufftracker;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.ArrayList;

/**
 * Created by gavinfarnsworth on 3/26/15.
 */
public class Container {
    private String name;
    private Bitmap image;
    private Location location;
    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public int getItemsCount(){
        return items.size();
    }

    public Item getItem(int i){
        return items.get(i);
    }

    public Container(String name) {

        this.name = name;
        this.items = new ArrayList<Item>();
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
