/**
 *  This class is the main class of the "Enchanted Forest" application. 
 *  "Enchanted Forest" is a simple, text based adventure game.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Amanda Lu
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room clearing, forest, bigTree, cliff, bridge, ruins, well, cottageOutside, kitchen, bedroom;
    private Item key, shovel, cup, redGem, blueGem, greenGem, yellowGem, knife, vines, waterCup, paper, seedPacket, journal, smallRock, largeRock, branch, plate, fork;
    private boolean seedPlanted = false;
    private boolean plantGrown = false;
    private boolean cottageOpen = false;
    private boolean chestOpen = false;
    private boolean moundDug = false;
    private int gemNum = 0;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player(50);
        createRooms();
        createItems();
        parser = new Parser();
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        clearing = new Room("in a small clearing surrounded by trees. The ground here is covered with flowers and other shrubs, and the dirt seems to be extra fertile.");
        forest = new Room("in the middle of the forest. There is a suspicious mound of dirt to your right.");
        bigTree = new Room("in front of a huge towering tree. The tree is covered in long, hanging vines and has deep scratch marks running across the trunk.");
        cliff = new Room("on the edge of a cliff. There is a stone tablet sticking out of the ground near the edge.");
        bridge = new Room("next to a running river with a bridge stretching over it.");
        ruins = new Room("in the ruins of an old town. There is a dried-out well at the center of the ruined town.");
        cottageOutside = new Room("in front of a small cottage that seems abandoned. A bunch of rusty, old gardening supplies lay by the door.");
        kitchen = new Room("inside the kitchen of the cottage. It doesn't seem like anyone's been here in a long time and the counters are cluttered with dishes.");
        bedroom = new Room("inside the bedroom of the cottage. There is a small bed in the corner and a desk with books next to it. There is also a large chest at the foot of the bed.");
        well = new Room("at the bottom of the well. ");
        
        // initialise room exits
        clearing.setExit("east", forest);
        clearing.setExit("south", bridge);

        forest.setExit("north", bigTree); 
        forest.setExit("east", cliff);
        forest.setExit("west", clearing);
        
        bigTree.setExit("south", forest);
        
        cliff.setExit("west", forest);

        bridge.setExit("west", ruins);
        bridge.setExit("south", cottageOutside);
        bridge.setExit("north", clearing);

        ruins.setExit("east", bridge);
        
        well.setExit("out", ruins);
        
        cottageOutside.setExit("inside", kitchen);
        cottageOutside.setExit("north", bridge);

        kitchen.setExit("south", bedroom);
        kitchen.setExit("outside", cottageOutside);
        
        bedroom.setExit("north", kitchen);
        
        // adds interactions to each room
        clearing.addInteract("dirt");
        forest.addInteract("mound");
        cliff.addInteract("tablet");
        bigTree.addInteract("vines");
        bridge.addInteract("river");
        ruins.addInteract("well");
        cottageOutside.addInteract("door");
        bedroom.addInteract("chest");
        
        //Sets the room the player begins in
        player.setRoom(clearing);
        
    }
    /**
     * Creates all the items and assigns them to their rooms
     */
    private void createItems(){
        //creates the items
        key = new Item("key", 1.0, "A normal looking key. I wonder where it goes?");
        shovel = new Item("shovel", 10.0, "A rusty old shovel");
        cup = new Item("cup", 1.0, "A tall empty glass");
        waterCup = new Item("waterCup", 3.0, "A tall cup filled with water");
        redGem = new Item("redGem", 0.5, "A shiny red gemstone");
        blueGem = new Item("blueGem", 0.5, "A shiny blue gemstone");
        greenGem = new Item("greenGem", 0.5, "A shiny green gemstone");
        yellowGem = new Item("yellowGem", 0.5, "A shiny yellow gemstone");
        knife = new Item("knife", 2.0, "A sharp knife. Maybe I could use it to cut something");
        vines = new Item("vines", 4.0, "Some sturdy vines. It could probably support my weight");
        seedPacket = new Item("seedPacket", 1.0, "A small packet of seeds. There's a note that says 'Grow Me' on the front.");
        journal = new Item("journal", 2.0, "A worn journal. Maybe reading (using) it can tell me something about this forest.");
        smallRock = new Item("smallRock", 1.0, "A small rock");
        largeRock = new Item("largeRock", 6.5, "A large rock");
        branch = new Item("branch", 1.5, "A broken tree branch");
        plate = new Item("plate", 2.0, "A large white plate");
        fork = new Item("fork", 1.0, "A metal fork");
        
        //Adds items to the rooms
        bedroom.addItem(journal);
        kitchen.addItem(cup);
        kitchen.addItem(knife);
        cottageOutside.addItem(shovel);
        well.addItem(blueGem);
        forest.addItem(branch);
        forest.addItem(smallRock);
        bigTree.addItem(largeRock);
        bigTree.addItem(branch);
        ruins.addItem(largeRock);
        ruins.addItem(smallRock);
        kitchen.addItem(plate);
        kitchen.addItem(fork);
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Enchanted Forest!");
        System.out.println("You wake up to a cool breeze blowing at your face.");
        System.out.println("Opening your eyes, you realize that you are no longer in your room where you went to sleep, but in the middle of a forest!");
        System.out.println("You should probably figure out what is going on and find a way back home");
        System.out.println("Tip: Don't forget to pick up items after discovering them");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
            if (wantToQuit){
                System.out.println("You are lost, and keep wandering around the forest aimlessly for the rest of your days.");
                System.out.println();
            }
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("examine")) {
            examine(command);
        }
        else if (commandWord.equals("inventory")) {
            printInventory();
        }
        else if (commandWord.equals("use")) {
            use(command);
            if(gemNum==3){
                    System.out.println("As you put in the final gem, the entire tablet lights up and begins to rumble.");
                    System.out.println("It slowly rises out of the ground, revealing a swirling portal just big enough for you to fit through.");
                    System.out.println("You step through the portal and find yourself falling through the air before landing on your bed with a thud.");
                    System.out.println("What just happened? You try to recall the events of the day, but a wave of tiredness washes over you.");
                    System.out.println("Before long, you find yourself drifting asleep. Oh well, you can worry about what happened when you wake up...");
                    System.out.println();
                    wantToQuit = true;
                }
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("inspect")) {
            inspect(command);
        }
        else if (commandWord.equals("hint")){
            printHint();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are alone in the mysterious forest.");
        System.out.println("You should figure out how to get home.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("You probably shouldn't go that way...");
        }
        else if(nextRoom.equals(kitchen) && cottageOpen==false){
            System.out.println("The door is locked.");
        }
        else {
            player.setRoom(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
        }
    }  
    /**
     * Look around the room. Prints the items located in that room.
     * If second word, then looks closer at the desired thing. 
     */
    private void look(Command command){ 
        if(!command.hasSecondWord()){
            System.out.println(player.getRoom().getLongDescription());
            player.getRoom().showItems(); 
        }
        return; 
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    /**
     * Examines an item in the player's inventory and prints its description
     * @param command the commandm
     */
    private void examine(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Examine what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getItem(itemName);
        if(item.getWeight() == -1){  // item not found
            System.out.println("You do not have this item.");
        } else {
            item.printInfo();
        }
    }
    
    /**
     * Prints out the contents of the player's inventory.
     * Also displays the weight being held.
     */
    private void printInventory(){
        player.showInventory();
        System.out.println(player.getWeightString());
    }
    
    /**
     * Picks up item from the current room and puts it in the player's inventory
     * @param command the command
     */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Pick up what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getRoom().getItem(itemName);
        if(item.getWeight() == -1){  // item not found
            System.out.println("This item is not in the area.");
        } 
        else if(item.equals(vines)){
            System.out.println("You're not strong enough to rip the vines off the tree. Perhaps if you had a sharp object...");
        }
        else {
            player.addItem(item);
            player.getRoom().removeItem(item);
            System.out.println("You picked up a " + item.getName());
        }
    }
    /**
     * Drops the item from the player's inventory and places it in the current room.
     * @param command the command
     */
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getItem(itemName);
        if(item.getWeight() == -1){  // item not found
            System.out.println("You do not have this item.");
        } else {
            player.removeItem(item);
            player.getRoom().addItem(item);
            System.out.println("You dropped a " + item.getName());
        }
    }
    /**
     * Allows the player to use items from their inventory on interactables in each room
     * Contains the different combinations of requirements needed to produce each result.
     */
    private void use(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Use what?");
            return;
        }
        String itemName = command.getSecondWord();
        Item item = player.getItem(itemName);
        if(item.getWeight() == -1){  // item not found
            System.out.println("You do not have this item.");
            return;
        } 
        
        if(item.equals(journal)){
            System.out.println("You open the journal and begin to read: ");
            System.out.println();
            System.out.println("December 17, 1893,");
            System.out.println("A lot of weird things have been happening lately, and people are beginning to get sick.");
            System.out.println("It might have something to do with the experiments that I've been doing lately, but I can't stop now.");
            System.out.println("I'm so close, I can feel it.");
            System.out.println();
            System.out.println("December 21, 1893, ");
            System.out.println("Things have been getting a lot worse, and I think the townspeople are beginning to catch on to me.");
            System.out.println("I got kicked out of town, but luckily I found this small cottage where I can continue working for now.");
            System.out.println();
            System.out.println("December 31, 1893");
            System.out.println("Everything was going so well, I don't know what went wrong. I have to get out of here fast!");
            System.out.println("The magic of the new year must have messed with my spell and created some sort of monster.");
            System.out.println("I have all the magical gems I made so I should be okay but the townspeople...No, its too late for them.");
            System.out.println("I don't know what will happen to me, but if you are reading this, please, come and find me.");
            System.out.println();
            System.out.println("That's the last entry in the journal...");
            
        } else{
            System.out.println("Where would you like to use the " + itemName+"?");
            String word = parser.getObject();
            //Different item usage combinations
            if(item.equals(knife) && word.equals("vines") && player.getRoom().equals(bigTree)){
                bigTree.addItem(vines);
                System.out.println("You carefully cut some vines off of the tree, and they fall to the floor");
            } else if(item.equals(cup)&& word.equals("river") && player.getRoom().equals(bridge)){
                player.removeItem(cup);
                player.addItem(waterCup);
                System.out.println("You fill up the cup with water from the river");
            } else if(item.equals(shovel) && word.equals("mound") && player.getRoom().equals(forest)){
                if(moundDug){ //if the player has already dug up the mound
                    System.out.println("You've already dug this up!");
                    return;
                }
                forest.addItem(key);
                moundDug = true;
                System.out.println("You use the shovel to dig up the suspicious mound and uncover a key on the floor!");
            } else if(item.equals(seedPacket) && word.equals("dirt") && player.getRoom().equals(clearing)){
                player.removeItem(seedPacket);
                seedPlanted = true;
                System.out.println("You plant the seeds into the ground. Now all you have to do is water them");
            } else if(item.equals(waterCup) && word.equals("dirt") && player.getRoom().equals(clearing)){
                if(seedPlanted){
                    player.removeItem(waterCup);
                    player.addItem(cup);
                    clearing.addItem(greenGem);
                    System.out.println("You water the seeds with your water cup and green leaves start to slowly sprout out of the ground");
                    System.out.println("After a few minutes, the plant is fully grown and displays a sparkling green gem from within its leaves");
                } else{
                    System.out.println("You pour the water onto the ground, but nothing happens.");
                } 
            } else if (item.equals(vines) && word.equals("well") && player.getRoom().equals(ruins)){
                player.removeItem(vines);
                System.out.println("You tie the vines to the top of the well. It seems sturdy enough to hold your weight");
                ruins.setExit("inside", well);
            } else if ((item.equals(redGem)||item.equals(blueGem)||item.equals(greenGem)) && word.equals("tablet") && player.getRoom().equals(cliff)){
                player.removeItem(item);
                System.out.println("You place the gem into the stone tablet");
                gemNum++;
            }else if (item.equals(key) && word.equals("door") && player.getRoom().equals(cottageOutside)){
                cottageOpen=true;
                System.out.println("You use the key to unlock the cottage door"); 
            } else{
                System.out.println("That doesn't quite work");
            }
        }  
    }
    /**
     * use to inspect things in the area
     */
    private void inspect(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Inspect what?");
            return;
        }
        String thing = command.getSecondWord();
        if(thing.equals("well") && player.getRoom().equals(ruins)){
            System.out.println("Looking closer at the dry well, you notice something sparkling at the very bottom.");
        } else if(thing.equals("tablet") && player.getRoom().equals(cliff)){
            System.out.println("After further inspection, you notice that the tablet has four different colored holes where something seems to be missing"); 
            System.out.println("One of the holes is already filled up with a sparkling yellow gem");
        } else if(thing.equals("chest") && player.getRoom().equals(bedroom)){
            if (chestOpen){ //if the player has already opened the chest before
                System.out.println("The chest is empty");
                return;
            }
            System.out.println("You open the chest and take outeverything that is inside, placing each item on the floor");
            bedroom.addItem(seedPacket);
            bedroom.addItem(redGem);
        } else if(thing.equals("door") && player.getRoom().equals(cottageOutside)){
            System.out.println("You try to open the door, but it seems to be locked");
        } else if(thing.equals("mound") && player.getRoom().equals(forest)){
            System.out.println("This mound of dirt seems pretty suspicious. Maybe there's something hidden underneath it");
        } else {
            System.out.println("There is nothing interesting there");
        }
        
    }
    /**
     * Gives a hint to help the player out
     */
    private void printHint(){
        player.getRoom().printInteractString();
    }
}