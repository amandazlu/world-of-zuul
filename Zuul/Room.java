import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Enchanted Forest" application. 
 * "Enchanted Forest" is a simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author Amanda Lu
 * @version 2021.05.20
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> itemList = new ArrayList<Item>();       //stores items in the room
    private ArrayList<String> interactList = new ArrayList<String>();     //stores what can be interacted with in a room
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    /**
     * Adds an item to the room
     * @param the item added
     */
    public void addItem(Item item){
        itemList.add(item);
    }
    /**
     * Removes an item from the room
     * @param the item removed
     */
    public void removeItem(Item item){
        itemList.remove(item);
    }
    /** 
     * Prints the items that are located within the room
     */
    public void showItems(){
        if(itemList.size() == 0){
            System.out.println("There are no items in the area!");
        } else{
            System.out.println("Items in the area: ");
            for(Item item: itemList){
                System.out.print(item.getName() + "(" + item.getWeight() + " lbs) | ");
            }
            System.out.println();
        }
    }
    /**
     * Returns the desired item when given the name. If item is not found,
     * returns an item with a weight of -1 to indicate it was not found.
     * @param the name of the item
     * @return the corresponding item or item indicating not found
     */
    public Item getItem(String name){
        for(Item item: itemList){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return new Item(-1);
    }
    /**
     * Adds interactable object to the room's list
     * @param word the thing that can be interacted with
     */
    public void addInteract(String word){
        interactList.add(word);
    }
    /**
     * Prints the list of interactable objects in the room
     */
    public void printInteractString(){
        System.out.println("Things that you can interact with: ");
        for(String str : interactList){
            System.out.print(str + " | ");
        }
        System.out.println();
    }
}