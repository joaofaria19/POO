
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasaInteligenteTest {
    /* Default constructor for test class CasaInteligenteTest
    */
    public CasaInteligenteTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    /*
    * Função que testa o Construtor
    * */
    @Test
    public void testConstructor() {
        CasaInteligente casa1 = new CasaInteligente();
        SmartDevice sd = new SmartDevice();
        String room = new String();
        Map<String,SmartDevice> mydevices = new HashMap<>();
        Map<String, List<String>> mylocation = new HashMap<>();
        casa1.addDevice(sd);
        casa1.addDevice(sd);
        casa1.addRoom(room);
        assertTrue(casa1 != null);
        casa1 = new CasaInteligente("Campus de Gualtar");
        assertTrue(casa1 != null);
        casa1 = new CasaInteligente("Campus de Gualtar");
    }

    /*
    * Função que testa a criação e adição de novos Devices
    * */
    @Test
    public void testAddFindDevice() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        SmartCamera smartCam1 = new SmartCamera("c1");
        assertFalse(casaInte1.existsDevice("b1"));
        assertFalse(casaInte1.existsDevice("s1"));
        assertFalse(casaInte1.existsDevice("c1"));
        casaInte1.addDevice(smartBul1);
        assertTrue(casaInte1.existsDevice("b1"));
        casaInte1.addDevice(smartSpe1);
        assertTrue(casaInte1.existsDevice("s1"));
        assertTrue(casaInte1.existsDevice("b1"));
        casaInte1.addDevice(smartCam1);
        assertTrue(casaInte1.existsDevice("s1"));
        assertTrue(casaInte1.existsDevice("b1"));
        assertTrue(casaInte1.existsDevice("c1"));
    }

    /*
    * Função que testa o Método de getDevice();
    * */
    @Test
    public void testGetDevice() {
        CasaInteligente casa1 = new CasaInteligente("Gualtar");
        SmartBulb sb1 = new SmartBulb("sb");
        SmartSpeaker sp1 = new SmartSpeaker("sp");
        casa1.addDevice(sb1);
        assertTrue(casa1.getDevice("sb").equals(sb1));
        assertFalse(casa1.getDevice("sp").equals(sp1));
    }

    @Test
    public void testAddExistRoom(){
        CasaInteligente casa1 = new CasaInteligente("Gualtar");
        String room1 = "Casa de banho";
        String room2 = "Cozinha";
        String room3 = "Sala";
        casa1.addRoom(room1);
        casa1.addRoom(room2);
        casa1.addRoom(room3);
        assertTrue(casa1.existRoom(room1));
        assertTrue(casa1.existRoom(room2));
        assertTrue(casa1.existRoom(room3));
        assertTrue(casa1.existRoom(room1));
        assertFalse(casa1.existRoom("Garagem"));
    }

    @Test
    public void testAddHasDeviceRoom(){
        CasaInteligente casa1 = new CasaInteligente("Famalicao");;
        SmartCamera sc = new SmartCamera("sc1");
        SmartBulb sb = new SmartBulb("sb1");
        SmartSpeaker sp = new SmartSpeaker("sp1");
        casa1.addDevice(sb);
        casa1.addDevice(sp);
        casa1.addDevice(sc);
        casa1.addRoom("sala");
        casa1.addRoom("quarto");
        casa1.addToRoom("sala", "sp1");
        casa1.addToRoom("sala", "sb1");
        casa1.addToRoom("quarto", "sc1");
        assertTrue(casa1.roomHasDevice("sala", "sb1"));
        assertTrue(casa1.roomHasDevice("sala", "sp1"));
        assertFalse(casa1.roomHasDevice("sala", "sc1"));
        assertTrue(casa1.roomHasDevice("quarto", "sc1"));
        assertFalse(casa1.roomHasDevice("quarto", "sb1"));

    }
}
