package roomsanditems;

import java.lang.reflect.Array;

public class Item {
    private String name, description;
    private ItemActions[] itemActions = new ItemActions[3];
    //Broken or not value, false means not broken, true means it is broken.
    private Boolean broken = false;
    private Room room;



    public void putItemInRoom(Room r){
        room = r;
    }

    public Room getRoom(){
       return room;
    }

    public boolean checkItemStatus() {
        return broken;
    }


    public enum ItemActions {
        Possess, Shake, Throw
    }
    public Item(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public void addAction(ItemActions IA) {
        //If the item has been thrown, it is seen as broken.

//        if(IA.equals(ItemActions.Throw)){
//            broken = true;
        //Guard clause to check if the item is broken.
        if(!getItemStatus()) {
            for (int i = 0; i < itemActions.length; i++) {
                if (itemActions[i] == IA) {
                    System.out.println("You can't do that action :(");
                    return;
                }
                if (itemActions[i] == null) {
                    itemActions[i] = IA;
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return  "\n" + getItemStatusPrint() + "\n" + name + ", " + description + getitemActions() ;
    }

    private String getItemStatusPrint() {
        if (broken) {
            return "This item is broken, try something else!";
        } else {
            return "This item is not yet broken";
        }
    }


    public String getitemActions() {
        String rep =  "\nThe " + name + " has had the following action(s) done to it: ";
        for (int i = 0; i < itemActions.length; i++) {
            if (itemActions[i] != null) {
                rep = rep + "\n" + itemActions[i];
            }
        }
        return rep;
    }
    public Boolean getItemStatus(){
        return broken;
    }

    public void breakItem(){
        broken = true;
        System.out.println(name + " is broken :(");
    }

    public String getName(){
        return name.trim();
    }


    public boolean checkAction(Item.ItemActions action){
        for (Item.ItemActions ia: itemActions) {
            if (action.equals(ia)){
                //If the action has been done, it returns true, otherwise false
                return true;
            }
        }
        return false;
    }
}
