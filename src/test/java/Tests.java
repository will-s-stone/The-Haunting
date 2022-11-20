import characters.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    @Test
    public void playerChangesRoomsAndIsAddedAndRemoved() throws ParserConfigurationException, SAXException,
    IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        Scanner scanner = new Scanner(System.in);
        //InputStream input = new FileInputStream("/Users/williamstone/Library/Mobile Documents/com~apple~CloudDocs/SUNY Oswego/Fall 2022/Testttt/src/xml/file.xml");
        InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/xml/file.xml");
        //"/Users/williamstone/Library/Mobile Documents/com~apple~CloudDocs/SUNY Oswego/Fall 2022/Testttt/src/xml/" + file
        SAXParser saxParser = spf.newSAXParser();
        XMLParser rxp = new XMLParser();
        saxParser.parse(input, rxp);
        rxp.setRoomRef();
        Player player = rxp.getPlayer();
        //player.play(scanner);
        player.move(rxp.getRooms().get(0));
        assertEquals(player.getRoom(), rxp.getPlayer().getRoom());
    }

}
