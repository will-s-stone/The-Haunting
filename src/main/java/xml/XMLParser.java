package xml;

import bst.BinarySearchTree;
import characters.Adult;
import characters.Character;
import characters.Child;
import characters.Player;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import roomsanditems.Item;
import roomsanditems.Room;

import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
    private Room room;
    private Item item;
    //r = room, i = item, p = player, a = adult, c = character
    private String north, east, south, west, rName, rDescription, iName, iDescription,
            cName, cDescription, actions;
    private Player player;
    private Adult adult;
    private Child child;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private BinarySearchTree<String, Room> roomList = new BinarySearchTree<String, Room>();



    @SuppressWarnings("ArgumentSelectionDefectChecker")
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        //System.out.println("Start Tag: " + qName);
        if (qName.equals("room")) {
            //Get name and description, do this for all objects
            rName = atts.getValue("name");
            rDescription = atts.getValue("description");
            //Get directional values
            north = atts.getValue("north");
            east = atts.getValue("east");
            south = atts.getValue("south");
            west = atts.getValue("west");


            //Create new room
            room = new Room(rName, rDescription, north, east, south, west);
            roomList.add(rName, room);

            }
        if(qName.equals("adult")){
            cName = atts.getValue("name");
            cDescription = atts.getValue("description");
            adult = new Adult(cName, cDescription, room);
            room.addCharacter(adult);

            adult.addRoom(room);

            room.addNPC(adult);
        }
        if (qName.equals("child")){
            cName = atts.getValue("name");
            cDescription = atts.getValue("description");
            child = new Child(cName, cDescription, room);
            room.addCharacter(child);

            child.addRoom(room);

            room.addNPC(child);
        }
        if (qName.equals("player")){
            cName = atts.getValue("name");
            cDescription = atts.getValue("description");
            player = new Player(cName, cDescription, room);
            room.addCharacter(player);

            player.addRoom(room);


        }

       if (qName.equals("item")) {
           iName = atts.getValue("name");
           iDescription = atts.getValue("description");
           actions = atts.getValue("actions");
           item = new Item(iName, iDescription);
           //Segment actions and stores them in an array if they contain the deliminator ","
           String arr[] = actions.split(",");
           for (int i = 0; i < arr.length; i++) {
               if(arr[i].equalsIgnoreCase("shake")){
                   item.addAction(Item.ItemActions.Shake);
               }
               if(arr[i].equalsIgnoreCase("possess")){
                   item.addAction(Item.ItemActions.Possess);
               }
               if(arr[i].equalsIgnoreCase("Throw")){
                   item.addAction(Item.ItemActions.Throw);
               }
           }
           room.addItem(item);
           item.putItemInRoom(room);

           items.add(item);
           actions = "";
       }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) {
        if (qName.equals("room")) {
            //After all this is done, it adds the room and all the information for that room.
            rooms.add(room);
            //Reset all room info, so we can store another room.
            rName = "";
            rDescription = "";
        }
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public ArrayList<Item> getItems(){
        return items;
    }


    public void setRoomRef() {
        //Now when we set our room references, it will fill in all parameters needed for a room,
        // and from there we just can print it out.
        for (Room currentRoom : rooms) {
            if (currentRoom.getNorthRoomName() != null) {
                for (Room currentRoom2 : rooms) {
                    if (currentRoom2.getName().equalsIgnoreCase(currentRoom.getNorthRoomName())) {
                        //Sets the current room's north room
                        currentRoom.setNorthRoom(currentRoom2);
                        //If we know what room is south of the current room,
                        // then we know what's north of the second room
                        //currentRoom2.setSouthRoom(currentRoom);
                    }
                }
            }
            System.out.println(rooms.get(0).getRoom(Room.Direction.NORTH).getName());


            if (currentRoom.getEastRoomName()!= null) {
                for (Room currentRoom2:rooms) {
                    if(currentRoom2.getName().equalsIgnoreCase(currentRoom.getEastRoomName())){
                        currentRoom.setEastRoom(currentRoom2);
                        //currentRoom2.setWestRoom(currentRoom);
                    }
                }
            }
            if (currentRoom.getSouthRoomName()!= null) {
                for (Room currentRoom2:rooms) {
                    if(currentRoom2.getName().equalsIgnoreCase(currentRoom.getSouthRoomName())){
                        currentRoom.setSouthRoom(currentRoom2);
                        //currentRoom2.setNorthRoom(currentRoom);
                    }
                }
            }


            if (currentRoom.getWestRoomName()!= null) {
                for (Room currentRoom2:rooms) {
                    if(currentRoom2.getName().equalsIgnoreCase(currentRoom.getWestRoomName())){
                        currentRoom.setWestRoom(currentRoom2);
                        //currentRoom2.setEastRoom(currentRoom);
                    }
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
    public BinarySearchTree<String, Room> getRoomList(){
        return roomList;
    }
}

