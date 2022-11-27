package characters;

import roomsanditems.Room;

import java.util.ArrayList;

@SuppressWarnings("JavaLangClash")
public abstract class Character {
    private String name;
    private String description;
    private Room room;
    private ArrayList<Room> rooms = new ArrayList<>();

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public Room getPreviousRoom(){
        return rooms.get(rooms.size()-1);
    }


    @Override
    public String toString(){
        return "\n" + name +", "+ description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //Getters and setters to put our player where we want and to lso access this information.
    public abstract void move(Room r);

    public Room getRoom(){
        return room;
    }
}
