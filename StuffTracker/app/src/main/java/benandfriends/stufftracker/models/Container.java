package benandfriends.stufftracker.models;


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

public final class Container extends Observable {

    private final UUID id;
    private String name;
    private Bitmap image;
    private List<Item> items = new ArrayList<>();


    public Container(String name) {
        this.id = UUID.randomUUID();
        this.setName(name);
    }


    public Container(String name, UUID id) {
        this.id = id;
        this.name = name;
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
        this.setChanged();
        this.notifyObservers();
    }


    public void setImage(Bitmap image) {
        this.image = image;
        this.setChanged();
        this.notifyObservers();
    }


    public List<Item> getItems() {
        return this.items;
    }


    public void addItem(Item item) {
        items.add(item);
        this.setChanged();
        this.notifyObservers();
    }


    public void removeItem(Item item) {
        items.remove(item);
        this.setChanged();
        this.notifyObservers();
    }


    public UUID getId() {
        return this.id;
    }


}
