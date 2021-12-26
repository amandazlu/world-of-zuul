
/**
 * This class is the main class of the "Enchanted Forest" application. 
 * "Enchanted Forest" is a simple, text based adventure game.
 *  
 * Each "Item" in this class represents something that can be picked up 
 * by the Player and can be used. They can be stored in a Room or held by 
 * the Player, and each item has a specific weight.
 *
 * @author Amanda
 * @version 2021.05.12
 */
public class Item
{
   private String name;
   private String description;
   private double weight;
   
   /**
    * Creates an Item with a description, and weight
    * @param item The name of the item
    * @param num The item's weight
    */
   public Item (String name, double num, String desc){
       this.name = name;
       description = desc;
       weight = num;
    }
   public Item(double num){
       name = "";
       description = "";
       weight = num;
    }
   /**
    * Returns the name of the item
    * @return The item's name
    */
   public String getName(){
       return name;
    }
   /**
    * Returns the weight of the item
    * @return The item's weight
    */
   public double getWeight(){
       return weight;
    }
   /**
    * Returns the weight of the item in a string
    * @return The item's weight as a string
    */ 
   public String getWeightString(){
       return "Weight: " + weight + " pounds";
    }
   /**
    * Returns information about the item in this format:
    *    A rusty old shovel.
    *    Weight: 5.0 pounds
    * @return description of the item
    */
   public void printInfo(){
       System.out.println(description + ".");
       System.out.println(getWeightString());
    }
}
