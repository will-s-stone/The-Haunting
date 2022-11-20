package roomsanditems;

import characters.Character;
import characters.NPC;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Room {

    //Private instance variables
    private String name;
    private String description;
    private ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<NPC> npcs = new ArrayList<>();
    private Room rNORTH, rEAST, rSOUTH, rWEST;
    private String northRoom, eastRoom, southRoom, westRoom;


    public enum Direction {NORTH, EAST, SOUTH, WEST}


    public Room getRoom(Direction dir){
        if (dir.equals(Direction.NORTH)){
            return rNORTH;
        }
        if (dir.equals(Direction.SOUTH)){
            return rSOUTH;
        }
        if (dir.equals(Direction.EAST)){
            return rEAST;
        }
        if (dir.equals(Direction.WEST)){
            return rWEST;
        }
        return null;
    }

    //Defining of constructors
    public Room(String name, String description, String northRoom, String eastRoom, String southRoom, String westRoom) {
        this.name = name;
        this.description = description;
        this.northRoom = northRoom;
        this.eastRoom = eastRoom;
        this.southRoom = southRoom;
        this.westRoom = westRoom;
    }


    public void addCharacter(Character oneCharacter){
        characters.add(oneCharacter);
    }


    //Redefining toString method, making careful note to utilize the assignment1.Adult toString() method
    @Override
    public String toString() {
        String representation = "\n" + name + "\n" + description + "\n" +
                "Neighboring rooms: " + "North room name: " + getNorthRoomName() +", " + "East room name: " +
                getEastRoomName() +", "+ "\nSouth room name: " + getSouthRoomName() + ", " + "West room name: " + getWestRoomName() +
                "\n" + iterate(npcs) + "\n" + iterate(items);
        return representation;
    }

    public String iterate(ArrayList arr){
        String rep = "";
        for (Object e: arr) {
            rep = rep + "\n" + e;
        }
        return rep;
    }

    //Referencing of 'Getters'
    public String getName() {
        return name.toString();
    }
    public String getDescription() {
        return description.toString();
    }

    //Use this...
    public void addItem(Item item){
        items.add(item);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public String getNorthRoomName(){
        if(northRoom == null){
            return "No north room :(";
        } else{
            return northRoom;
        }
    }
    public String getEastRoomName() {
        if(eastRoom == null){
            return "No east room :(";
        } else{
            return eastRoom;
        }
    }
    public String getSouthRoomName() {
        if(southRoom == null){
            return "No south room :(";
        } else{
            return southRoom;
        }
    }
    public String getWestRoomName() {
        if(westRoom == null){
            return "No west room :(";
        } else{
            return westRoom;
        }
    }

    public void setNorthRoom(Room northRoom){
        rNORTH = northRoom;
    }
    public void setEastRoom(Room eastRoom) {
        rEAST = eastRoom;}
    public void setSouthRoom(Room southRoom){
        rSOUTH = southRoom;}
    public void setWestRoom(Room westRoom) {
        rWEST = westRoom;}

    public Room getNorthRoom(){return rNORTH;}
    public Room getEastRoom() {return rEAST;}
    public Room getSouthRoom() {return rSOUTH;}
    public Room getWestRoom() {return rWEST;}

    //For tests
    public Room getCharacter(Character character){
        for (int i = 0; i < characters.size(); i++) {
            if(characters.get(i) == character) {
                return character.getRoom();
            }
        }
        return null;
    }

    public void removeItem(Item i) {
        items.remove(i);
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public void removeCharacter(Character c){
            characters.remove(c);
    }

    public void removeNPC(NPC npc){
        npcs.remove(npc);
    }
    public void addNPC(NPC npc){
        npcs.add(npc);
    }
    public ArrayList<NPC> getNPCs(){
        return npcs;
    }
    public void setNPCs(ArrayList<NPC> tempList){
        npcs = tempList;
    }
}

