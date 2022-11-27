package characters;

import roomsanditems.Room;
import java.util.ArrayList;
import java.util.Random;

public class NPC extends Character {
    protected int scaredness = 0;
    private Room room;
    private boolean flag = false;

    //An ArrayList for the rooms that a NPC has been in goes to ~ Add to character, make abstract.
    //private ArrayList<Room> rooms = new ArrayList<>();

    public NPC(String name, String description, Room room) {
        super(name, description);
        this.room = room;
        room.addCharacter(this);
        room.addNPC(this);
        //this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "\n" + getName() + ", " + getDescription() + " ~ Current scare level: " + scaredness;
    }



    //Check below in case of trouble...
    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public void move(Room r) {
        //Let's add the room we move to the rooms ArrayList
        //We want to add and remove the NPC from the previous room' s Arraylist and add it to the new one.
        room.removeCharacter(this);
        room.removeNPC(this);
        room = r;
        room.addCharacter(this);
        room.addNPC(this);
        //Could be 'r', refer to if it does not work.
        for (int i = 0; i < room.getItems().size(); i++) {
            if (r.getItems().get(i).getItemStatus()){
                clean();
            }
        }
    }

    public void removeFromHouse(){
        room.getCharacters().remove(this);
        room.getNPCs().remove(this);
        room = null;
    }

    private void clean() {
        //Loops through items in the room to see if they are broken.
        //If so the NPC cleans them and removes them from the items list
        for (int i = 0; i < room.getItems().size(); i++) {
            if(room.getItems().get(i).getItemStatus()) {
                room.removeItem(room.getItems().get(i));
            }
        }
    }

    public Room setRandomRoom() {
        Random random = new Random();

        ArrayList<Room> tempRooms = new ArrayList<>();

        if (room.getNorthRoom() != null) {
            tempRooms.add(room.getNorthRoom());
            //return room.getNorthRoom();
        }
        if (room.getEastRoom() != null) {
            tempRooms.add(room.getEastRoom());
            //return room.getEastRoom();
        }
        if (room.getSouthRoom() != null) {
            tempRooms.add(room.getSouthRoom());
            //return room.getSouthRoom();

        }
        if (room.getWestRoom() != null) {
            tempRooms.add(room.getWestRoom());
            //return room.getWestRoom();
        }
        int randomInt = random.nextInt(tempRooms.size()-1);
        return tempRooms.get(randomInt);


    }




    public int getScaredness() {
        return scaredness;
    }


    public void increaseScaredness(int x) {
        //int preScare = this.scaredness;
        this.scaredness = scaredness + x;
        System.out.println(getName() +"'s scare level has increased to: " + scaredness);

        if(!flag) {
            if (scaredness > 50) {
                System.out.println(getName() + " has left the room!!!");
                move(setRandomRoom());
                flag = true;
            }
        }
        if (scaredness > 100) {
            this.getRoom().removeNPC(this);
            removeFromHouse();
            System.out.println("KEEP GOING!!!! " + getName() + " has left the house!!!!");
            }
    }

    public void moveNPC(){

        if(!flag) {
            if (scaredness > 50 && scaredness < 100) {
                System.out.println(getName() + " has left the room!!!");
                move(setRandomRoom());
                littleShriek();
                flag = true;
            }
        }
        if (scaredness > 100) {
            move(null);
            bigShriek();
            System.out.println("KEEP GOING!!!! " + getName() + " has left the house!!!!");
        }
    }

    public void littleShriek(){
        System.out.println("AHHHH");
    }
    public void bigShriek(){
        System.out.println("AAAAHHHHHHHHHHHHHHHHH");
    }

}


