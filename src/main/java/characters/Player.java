package characters;

import bst.BinarySearchTree;
import org.jetbrains.annotations.NotNull;
import roomsanditems.Item;
import roomsanditems.Room;
import xml.XMLParser;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Player extends Character {
    private Room room;
    private boolean running = false;
    private Timer timer;
    private AtomicInteger timeLeft;
    private ArrayList<Room> rooms = new ArrayList<>();

    private BinarySearchTree bst = new BinarySearchTree();

    private boolean cheatstatus = false; //If set to false, cheat mode is disabled


    public Player(String name, String description, Room room) {
        super(name, description);
        //Because the player and NPC's change rooms we want to use private instance variables in
        // the class themselves rather than getters from the parent class.
        this.room = room;
    }

    public void initTimer(int secs){
        timeLeft = new AtomicInteger(secs);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int tl = timeLeft.decrementAndGet();
                if(tl == 0) {
                    System.out.println("Better luck next time :(");
                    System.exit(0);
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000);
    }


    public void play(Scanner scanner, BinarySearchTree bst){
        System.out.println("\n\n\n" + getName() + getDescription());

        System.out.println("_____________________Welcome to The Haunting_____________________");
        System.out.println("Here you will be given quite the 'Ghostly' task you see..." +
                        "\nYou were involved in a grave accident and the man whom you" +
                        "\ndeem responsible got off scotch free, it is your turn to make " +
                        "\nhim and his family's lives HELL!!!! Type 'start' to well, start!");
        System.out.println("_________________________________________________________________");
        String start = scanner.nextLine();
        for (int i = 0; i < 1000; i++) {
            if(start.equalsIgnoreCase("start")){
                running = true;
                break;
            } else{
                System.out.println("Here's a hint, type 'start'...");
                start = scanner.nextLine();
            }
        }
        initTimer(60);
        //Utilize switch cases rather than a series of if statements.
        do {


            System.out.println("Enter command: ");
            String txt = scanner.nextLine();
            //Give the following a try...
            String[] input = txt.split(":");



            switch(input[0]){
                case "help":
                    String help = new String("Please choose from the following commands: " +
                            "\nlook = outputs the information pertaining to the room you, the player is in" +
                            "\nnorth = moves you, the player, north"+
                            "\neast = moves you, the player, east" +
                            "\nsouth = moves you, the player, south" +
                            "\nwest = moves you, the player, west" +
                            "\nshake:[itemName] = shakes an item of your choosing" +
                            "\npossess:[itemName] = possesses an item of your choosing" +
                            "\nthrow:[itemName] = throws an item of your choosing" +
                            "\ntime = displays the time you have left" +
                            "\nexit/quit = exits the program" );

                    System.out.println(help);
                    /*
                     * this command outputs a help message to the user,
                     * consisting of the full list of commands that can be used,
                     * together with descriptions of what they do
                     */
                    break;
                    //For the sake of simplicity, we merge these case statements.
                case "exit":
                case"quit":
                    System.out.println("Goodbye");
                    running = false;
                    break;

                case "cheatmode":
                    cheatstatus = true;
                    System.out.println("You have entered cheat mode, you now can look up rooms by name or even look at all the rooms :)");
                    break;
                case "nocheatmode":
                    cheatstatus = false;
                    System.out.println("Cheating is turned off:(");
                    break;

                case "look":
                    if(input.length>1) {
                        if(cheatstatus) {
                            String lookThing = input[1];
                            lookThing = lookThing.trim();
                            if (lookThing.equalsIgnoreCase("all")) {
                                bst.inOrder();
                                break;
                            } else {
                                bst.findAndPrint(lookThing);

                                break;
                        }
                    }
                    }else {
                        Room printR = getRoom();
                        System.out.println(printR);
                    }
                    break;

                case "print":
                    for (Object obj:bst) {
                        System.out.println(obj.toString());
                    }
                    break;
                case "north":

                        Room north = room.getRoom(Room.Direction.NORTH);

                        if (north != null) {
                            move(north);
                            System.out.println("You've moved north to...");
                            System.out.println(north);
                            break;
                        }
                        System.out.println("Oops, there's no north room to go to");
                    break;
                case "east":
                    Room east = room.getRoom(Room.Direction.EAST);
                    if (east != null) {
                        move(east);
                        System.out.println("You've moved east to...");
                        System.out.println(east);
                            break;
                    }
                    System.out.println("Oops, there's no east room to go to");



                    break;
                case "south":
                    Room south = room.getRoom(Room.Direction.SOUTH);
                    if (south != null) {
                        move(south);
                        System.out.println("You've moved south to...");
                        System.out.println(south);
                        break;
                    }
                    System.out.println("Oops, there's no south room to go to");



                        break;
                case "west":
                    //was Direction.WEST prior
                    Room west = room.getRoom(Room.Direction.WEST);
                    if (west != null) {
                        move(west);
                        System.out.println("You've moved west to...");
                        System.out.println(west);
                        break;
                    }
                    System.out.println("Oops, there's no west room to go to");

                        break;
                case "possess":
                    String possessThing = input[1];
                    possessThing = possessThing.trim();
                    //Let's try and find 'shakeThing' in the room we are in.


                    for (int i = 0; i < getRoom().getItems().size(); i++) {
                        //Let's get the name at an index.
                        String x = getRoom().getItems().get(i).getName();
                        if (x.equalsIgnoreCase(possessThing)) {
                            scare(Item.ItemActions.Possess, getRoom().getItems().get(i));


                            (getRoom().getItems().get(i)).addAction(Item.ItemActions.Possess);
                            //System.out.println(getRoom().getItems().get(i));




                            break;
                        }
                    }
                    break;
                case "throw":
                    String throwThing = input[1];
                    throwThing = throwThing.trim();
                    //Let's try and find 'throwThing' in the room we are in.


                    for (int i = 0; i < getRoom().getItems().size(); i++) {
                        //Let's get the name at an index.
                        String x = getRoom().getItems().get(i).getName();
                        if (x.equalsIgnoreCase(throwThing)){
                            scare(Item.ItemActions.Throw, getRoom().getItems().get(i));


                            (getRoom().getItems().get(i)).addAction(Item.ItemActions.Throw);
                            getRoom().getItems().get(i).breakItem();
                            //System.out.println(getRoom().getItems().get(i));



                            break;
                        }
                    }
                    break;
                case "shake":
                    String shakeThing = input[1];
                    shakeThing = shakeThing.trim();
                    //Let's try and find 'shakeThing' in the room we are in.


                    for (int i = 0; i < getRoom().getItems().size(); i++) {
                        //Let's get the name at the index.
                        String x = getRoom().getItems().get(i).getName();
                        if (x.equalsIgnoreCase(shakeThing)){
                            //Try putting this, "scare(Item.ItemActions.Shake, getRoom().getItems().get(i));" ~ highere
                            scare(Item.ItemActions.Shake, getRoom().getItems().get(i));


                            (getRoom().getItems().get(i)).addAction(Item.ItemActions.Shake);
                            //System.out.println(getRoom().getItems().get(i));


                            break;
                        }
                    }

                    break;
                case "time":
                    System.out.println("You have " + timeLeft + " seconds left!!!");

                    break;
                default:
                    System.out.println("Stop talking gibberish");
                    break;
            }
        } while(running);
        scanner.close();
    }
    //Getters and setters.
    @Override
    public void move(Room r){
        //Functionality to remove and add player from the room's ArrayList and add to another
        room.removeCharacter(this);
        r.addCharacter(this);
        room = r;

    }

    @Override
    public Room getRoom(){
        return room;
    }


    public void increaseTime(){

    }
    public void scare(Item.ItemActions ia, Item it) {
        //Get the NPC's in the room, and scare each one.
        //make a copy of the size first

        NPC[] temp = new NPC[room.getNPCs().size()];
        temp = room.getNPCs().toArray(temp);

        for (int i = 0; i < temp.length; i++) {
            //If the item is not broke and the item action is equal to possess.
            if(!(temp[i] ==null )) {

                if ((ia.equals(Item.ItemActions.Possess))) {
                    for (int j = 0; j < room.getNPCs().size(); j++) {
                        if (room.getNPCs().get(j).getName().equalsIgnoreCase(temp[i].getName())) {
                            if(!it.checkAction(ia)) {
                                room.getNPCs().get(j).increaseScaredness(possessNum());
                                System.out.println("What was that!?!?!?!?");
                                break;
                            }
                        }
                    }
                }

                if (ia.equals(Item.ItemActions.Throw)) {
                    for(int j = 0; j<room.getNPCs().size(); j++){
                        if(room.getNPCs().get(j).getName().equalsIgnoreCase(temp[i].getName())){
                            if(!it.checkAction(ia)) {
                                room.getNPCs().get(j).increaseScaredness(throwNum());
                                System.out.println("What was that!?!?!?!?");
                                break;
                            }
                        }
                    }
                }

                if (ia.equals(Item.ItemActions.Shake)) {
                    for (int j = 0; j < room.getNPCs().size(); j++) {
                        if (room.getNPCs().get(j).getName().equalsIgnoreCase(temp[i].getName())) {
                            if(!it.checkAction(ia)) {
                                room.getNPCs().get(j).increaseScaredness(shakeNum());
                                System.out.println("What was that!?!?!?!?");
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (room.getNPCs().size()<temp.length){
            timeLeft.addAndGet(30);
        }

    }

    public int possessNum(){
        Random random = new Random();
        return random.nextInt(15) + 10;

    }
    public int throwNum(){
        Random random = new Random();
        return random.nextInt(20) + 20;
    }
    public int shakeNum(){
        Random random = new Random();
        return random.nextInt(10) + 5;
    }

}
