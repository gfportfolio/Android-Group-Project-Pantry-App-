package benandfriends.stufftracker;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.respositories.ContainerRepository;

public final class Application implements Observer {


    public static final SimpleDateFormat APP_DATE_FORMAT = new SimpleDateFormat("MMMM d, yyyy", Locale.US);

    private static Application applicationInstance;

    private Context context;
    private ContainerRepository mContainerRepository;
    private List<Container> mContainers;


    private Application() { }


    public static Application getApplicationInstance(Context context) {
        if (null == applicationInstance) {
            applicationInstance = new Application();
        }
        applicationInstance.setContext(context);
        return applicationInstance;
    }


    public void addNewContainer(Container container) {
        if (null == mContainers) {
            this.loadContainers();
        }
        mContainers.add(container);
        mContainerRepository.persistContainerData(mContainers);
    }


    public void removeContainer(Container container) {
        if (null == mContainers) {
            this.loadContainers();
        }
        if (mContainers.remove(container)) {
            mContainerRepository.persistContainerData(mContainers);
        }
    }


    public List<Item> getAllItems() {
        if (null == mContainers) {
            this.loadContainers();
        }
        List<Item> items = new ArrayList<>();
        for (Container c : mContainers) {
            items.addAll(c.getItems());
        }
        return items;
    }


    public List<Container> getContainers() {
        if (null == mContainers) {
            this.loadContainers();
        }
        return mContainers;
    }


    private void loadContainers() {
        mContainerRepository = new ContainerRepository(context);
        if (null != mContainers) {
            for (Container container : mContainers) {
                container.deleteObservers();
            }
        }
        mContainers = mContainerRepository.loadContainerDataFromMemory();
        for (Container container : mContainers) {
            container.addObserver(this);
        }
    }


    private void setContext(Context context) {
        this.context = context;
    }


    @Override
    public void update(Observable observable, Object data) {
        this.mContainerRepository.persistContainerData(mContainers);
    }


}
