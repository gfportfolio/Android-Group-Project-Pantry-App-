package benandfriends.stufftracker.models;


import android.graphics.Bitmap;

public final class Container {

    private String name;
    private Bitmap image;


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


}
