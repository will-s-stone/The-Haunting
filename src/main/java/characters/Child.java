package characters;

import roomsanditems.Room;

import java.util.ArrayList;

public class Child extends NPC{
    private boolean flag = false;

    public Child(String description, String name, Room room) {
        super(description, name, room);
    }



    @Override
    public void increaseScaredness(int x) {
            //int preScare = this.scaredness;
            int y = (int) (x * 1.5);
            this.scaredness = (int) (this.scaredness + y);
            System.out.println(getName() +"'s scare level has increased to: " + this.scaredness);

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
}
