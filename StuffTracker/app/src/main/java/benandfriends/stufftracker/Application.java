package benandfriends.stufftracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public final class Application extends Observable {


    private static Application applicationInstance = new Application();
    private List<Container> mContainers = new ArrayList<>();


    private Application() { }

    public static Application getApplicationInstance() {
        return applicationInstance;
    }


    public void addNewContainer(Container container) {
        mContainers.add(container);
        this.notifyObservers();
    }


    public void removeContainer(Container container) {
        mContainers.remove(container);
        this.notifyObservers();
    }


    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for (Container c : mContainers) {
            items.addAll(c.getItems());
        }
        return items;
    }


    public List<Container> getContainers() {
        List<Container> copy = new ArrayList<>();
        Collections.copy(copy, mContainers);
        return copy;
    }


}
