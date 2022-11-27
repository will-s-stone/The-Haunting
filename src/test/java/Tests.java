import bst.BinarySearchTree;
import characters.Adult;
import characters.Character;
import characters.Child;
import characters.Player;
import org.junit.jupiter.api.Test;
import roomsanditems.Item;
import roomsanditems.Room;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    Room room = new Room("mainRoom", "the main room", null, null, null,null);
    Room roomTwo = new Room("room two", "the second room", null, null, null, null);
    Room abcRoom = new Room("abcRoom", "the abcRoom", null, null, null, null);
    Room bcRoom = new Room("bcRoom", "the bcRoom", null, null, null, null);
    Room cRoom = new Room("cRoom", "the cRoom", null, null, null, null);
    Room defRoom = new Room("defRoom", "the defRoom", null, null, null, null);
    Room efRoom = new Room("efRoom", "the efRoom", null, null, null, null);
    Room fRoom = new Room("fRoom", "the fRoom", null, null, null, null);
    BinarySearchTree<String, Room> roomsList = new BinarySearchTree<>();



    Player player = new Player("player", "the main player", room);
    Adult tom = new Adult("tom", "I'm tom", room);
    Child timmy = new Child("timmy", "I'm timmy", roomTwo);
    Item bat = new Item("bat", "I'm a bat");


    @Test
    public void playerChangesRoomsAndIsAddedAndRemoved() {
        player.move(roomTwo);
        //After moving the player's previous room array size should be 0 and the new size should be 1.
        assertEquals( 1, room.getCharacters().size());
        assertEquals( 2, roomTwo.getCharacters().size());
        player.move(room);
        assertEquals( 2, room.getCharacters().size());
        assertEquals( 1,roomTwo.getCharacters().size());
    }
    @Test
    public void NPCChangesRoomAndIsAddedAndRemoved(){
        //tom.move(room);
        timmy.move(room);

        assertEquals(2, room.getNPCs().size());
        assertEquals(0, roomTwo.getNPCs().size());

    }
    @Test
    public void NPCCleansRoom(){
        room.addItem(bat);
        bat.addAction(Item.ItemActions.Throw);
        bat.breakItem();
        timmy.move(room);

        assertEquals(0, room.getItems().size());
    }
    @Test
    public void throwAndBreak(){
        //This is the sequence that takes place when the item is thrown by the player.
        bat.addAction(Item.ItemActions.Throw);
        bat.breakItem();
        assertEquals(true, bat.getItemStatus());
    }
    @Test
    public void cannotAddActionsToBrokenItem(){
        bat.addAction(Item.ItemActions.Throw);
        bat.breakItem();
        bat.addAction(Item.ItemActions.Shake);
        assertEquals(("\nThe bat has had the following action(s) done to it: " + "\n" + "Throw"), bat.getitemActions());
    }

    @Test
    public void BSTInsert(){
        roomsList.add(abcRoom.getName(), abcRoom);
        roomsList.add(bcRoom.getName(), bcRoom);
        roomsList.add(cRoom.getName(), cRoom);
        roomsList.add(defRoom.getName(), defRoom);
        roomsList.add(efRoom.getName(), efRoom);
        roomsList.add(fRoom.getName(), fRoom);

        assertEquals(6, roomsList.getSize());
    }

    @Test
    public void BSTfind(){
        roomsList.add(abcRoom.getName(), abcRoom);
        roomsList.add(bcRoom.getName(), bcRoom);
        roomsList.add(cRoom.getName(), cRoom);
        roomsList.add(defRoom.getName(), defRoom);
        roomsList.add(efRoom.getName(), efRoom);
        roomsList.add(fRoom.getName(), fRoom);


        assertEquals(bcRoom, roomsList.find("bcRoom"));
    }

    @Test
    public void BSTRemove(){
        roomsList.add(abcRoom.getName(), abcRoom);
        roomsList.add(bcRoom.getName(), bcRoom);
        roomsList.add(cRoom.getName(), cRoom);
        roomsList.add(defRoom.getName(), defRoom);
        roomsList.add(efRoom.getName(), efRoom);
        roomsList.add(fRoom.getName(), fRoom);

        assertEquals(6, roomsList.getSize());
        roomsList.remove("bcRoom");
        assertEquals(5, roomsList.getSize());
    }
}
