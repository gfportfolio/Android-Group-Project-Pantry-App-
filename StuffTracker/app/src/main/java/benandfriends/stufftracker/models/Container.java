package benandfriends.stufftracker.models;


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Container {

    private String name;
    private Bitmap image;
    private List<Item> items = new ArrayList<>();


    public Container(String name) {
        this.setName(name);
    }


    public String getName() {
        return name;
    }


    public Bitmap getImage() {
        return image;
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


    public void addItem(Item item) {
        items.add(item);
    }


    public void removeItem(Item item) {
        items.remove(item);
    }


    public List<Item> getItemList() {
        List<Item> copy = new ArrayList<>();
        Collections.copy(copy, items);
        return copy;
    }

}
