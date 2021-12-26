/**
 *  This class is the main class of the "Enchanted Forest" application. 
 *  "Enchanted Forest" is a simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Amanda Lu
 * @version 2021.05.20
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help","look", "examine", "inventory", "use", "drop", "take", "inspect", "hint"
    };
    private static final String[] commandDescriptions = {
        "use this command with the exits to move between rooms", 
        "exits the game", 
        "prints information about the game and all the commands",
        "look around the room generally", 
        "use this command with items in your inventory to find out more about them", 
        "displays your inventory", 
        "use this command with items in your inventory to use them", 
        "use this command with items in your inventory to drop them", 
        "use this command with items in the room to pick them up",
        "use this command with specific areas in the room to find out more information", 
        "reveals the interactable objects within the room"
    };
    

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(int i = 0; i < validCommands.length; i++) {
            System.out.print(validCommands[i] + ": " + commandDescriptions[i]);
            System.out.println();
        }
    }
}