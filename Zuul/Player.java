import java.util.ArrayList;
/**
 * This class holds information about the Player, which is what 
 * represents the user. The Player can pick up items and go through rooms,
 * but has a limit on the amount of weight they can hold. 
 *
 * @author Amanda
 * @version 2021.05.12
 */
public class Player
{
    private double weight = 0;
    private double limit;
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private Room currentRoom;

    /**
     * Constructor for objects of class Player
     * @param limit the max weight the player can hold
     * @start the room the player starts in
     */
    public Player(double limit){
        this.limit = limit;
    }
    /**
     * Returns the total weight the player is carrying
     * @return the weight carried
     */
    public double getWeight(){
        return weight;
    }
    /**
     * Returns the total weight the player can carry
     * @return the max weight
     */
    public double getLimit(){
        return limit;
    }
    /**
     * Returns the weight being carried and max weight limit in String format:
     *    Weight: 25.0 out of 150.0 pounds
     */
    public String getWeightString(){
        return "Weight: " + weight + " out of " + limit + " pounds";
    }
   
    /**
     * Returns the index of where the desired item is located within the
     * list of items that the player is holding.
     * @param item the item that is being searched for
     * @return the index where the item is located
     */
    public int findItem(Item item){
        int index = -1;
        for(int i = 0; i<itemList.size(); i++){
            if (item.equals(itemList.get(i))){
                index = i;
            }
        }
        return index;
    }
    /**
     * Adds an item to the list of what the player is holding
     * @param item the item being added
     */
    public void addItem(Item item){
        itemList.add(item);
        weight += item.getWeight();
    }
    /**
     * Removes an item from the list of what the player is holding
     * @param i the index where the item being removed is located
     */
    public void removeItem(Item item){
        itemList.remove(item);
        weight -= item.getWeight();
    }
    /**
     * Checks to see if the player has enough available space to pick up
     * the new desired item
     * @param item the item being checked
     * @return boolean whether the item can be picked up or not
     */
    public boolean checkItemWeight(Item item){
        if (item.getWeight() > limit-weight){
            return false;
        } else return true;
    }
    /**
     * Returns the room that the player is currently in
     * @return the current room
     */
    public Room getRoom(){
        return currentRoom;
    }
    /**
     * Sets the room that the player is in
     * @param newRoom the room the player is entering
     */
    public void setRoom(Room newRoom){
        currentRoom = newRoom;
    }
    /** 
     * Prints the content of the player's inventory
     */
    public void showInventory(){
        if(itemList.size() == 0){
            System.out.println("Your inventory is empty!");
        } else{
            System.out.println("Inventory: ");
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
}
