package benandfriends.stufftracker.utilities;

import java.util.ArrayList;

import benandfriends.stufftracker.models.Item;

/**
 * Created by Benjamin on 3/31/2015.
 */
public class Search {
    private ArrayList<Item> ListOfEverything = new ArrayList<Item>();
    private ArrayList<Item> SearchList = new ArrayList<Item>();

    /*
    Constructor
    Parameters:
        ArrayList<Item> itms:   This is the collection of all items we are searching through.
     */
    public Search(ArrayList<Item> itms){
        ListOfEverything = itms;

    }


    public boolean removeItem(Item itm){
        return ListOfEverything.remove(itm);
    }


    public boolean addItem(Item itm){
        return ListOfEverything.add(itm);
    }


    /*
    Parameters
        String s:   the name or partial name of the item we are searching for.
     */
    public ArrayList<Item> searchFor(String s){
        SearchList.clear();
        s=s.toLowerCase();
        String[] word=s.split(" ");
        String search="(.*)";
        for(String ss:word){
            search+=ss+"(.*)";
        }
        for(Item i:ListOfEverything){
            String name=i.getName().toLowerCase();
            if(name.matches(search)){
                SearchList.add(i);

            }
        }
        return SearchList;
    }

    public ArrayList<Item> getRecentSearch(){
        return SearchList;
    }

    public static ArrayList<Item> searchListFor(ArrayList<Item> ListOThings,String s){
        ArrayList<Item> searching=new ArrayList<Item>();
        s=s.toLowerCase();
        String[] word=s.split(" ");
        String search="(.*)";
        for(String ss:word){
            search+=ss+"(.*)";
        }
        for(Item i:ListOThings){
            String name=i.getName().toLowerCase();
            if(name.matches(search)){
                searching.add(i);

            }
        }
        return searching;
    }

}