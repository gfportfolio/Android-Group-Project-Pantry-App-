package benandfriends.stufftracker;

import android.text.style.TtsSpan;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by gavinfarnsworth on 4/6/15.
 */
public class Controller {

    private static ArrayList<Container> Containers;
    private static ArrayList<Item> Items;

    public static void load() {
        Containers = new ArrayList<Container>();
        Items = new ArrayList<Item>();

        loadDataFromMemory();



    }

    private static void loadDataFromMemory() {
        //TODO load memory


        //Dummy Data
        Container a = new Container("Fridge");
        Container b = new Container("Cupboard Over Stove");
        Containers.add(a);
        Containers.add(b);
        //String name, int id, String upc, Container location, Boolean opened, Date dateBought
        Item c= new Item("Oreos", 0, "044000025267", a, true, new Date(115, 3,1));
        Item d= new Item("Milk", 1, "062639295950", a, true, new Date(115, 3,3));
        d.setDateExpires(new Date(115, 3, 15));
        Items.add(0, c);
        Items.add(1,d);
    }

    public static ArrayList<Container> getContainers() {
        return Containers;
    }

    public static ArrayList<Item> getItems() {
        return Items;
    }
}
