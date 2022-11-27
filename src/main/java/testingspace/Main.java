package testingspace;


import bst.BinarySearchTree;
import org.xml.sax.SAXException;
import roomsanditems.Room;
import xml.XMLParser;

import characters.Player;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {

    @SuppressWarnings("DefaultCharset")
    public static void main(String[] args) {

        //String file;
        SAXParserFactory spf = SAXParserFactory.newInstance();
        Scanner scanner = new Scanner(System.in);

        //From Student XML in class example
        try {
            //InputStream input = new FileInputStream("/Users/williamstone/Library/Mobile Documents/com~apple~CloudDocs/SUNY Oswego/Fall 2022/Testttt/src/xml/file.xml");
            InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/xml/file.xml");
            //"/Users/williamstone/Library/Mobile Documents/com~apple~CloudDocs/SUNY Oswego/Fall 2022/Testttt/src/xml/" + file
            SAXParser saxParser = spf.newSAXParser();
            XMLParser rxp = new XMLParser();
            saxParser.parse(input, rxp);
            rxp.setRoomRef();

            BinarySearchTree<String, Room> bst = rxp.getRoomList();

            Player player = rxp.getPlayer();
            

            player.play(scanner, bst);




        } catch (ParserConfigurationException | SAXException |
                IOException e) {
            throw new RuntimeException(e);
        }

    }
}

