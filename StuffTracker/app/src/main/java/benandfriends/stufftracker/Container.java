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

    public boolean addItem(Item item){
        item.setLocation(this);
        return items.add(item);
    }

    public boolean removeItem(Item item){
        item.setLocation(null);
        return items.remove(item);
    }

    public Item getItemById(int id){
        for(Item itm: items){
            if(itm.getId()==id){
                return itm;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public ArrayList<Item> search(String s){
        //search this container for an item by name with string s
        return Search.searchListFor(items,s);
    }
}
