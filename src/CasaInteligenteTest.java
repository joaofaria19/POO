
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

    /*
     *
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
        SmartBulb sb = new SmartBulb();
        String room = new String();
        Map<String,SmartDevice> mydevices = new HashMap<>();
        Map<String, List<String>> mylocation = new HashMap<>();
        casa1.addDevice(sb);
        casa1.addDevice(sb);
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
        SmartBulb smartBul1 = new SmartBulb(1);
        SmartSpeaker smartSpe1 = new SmartSpeaker(2);
        SmartCamera smartCam1 = new SmartCamera(3);
        assertFalse(casaInte1.existsDevice(1));
        assertFalse(casaInte1.existsDevice(2));
        assertFalse(casaInte1.existsDevice(3));
        casaInte1.addDevice(smartBul1);
        assertTrue(casaInte1.existsDevice(2));
        casaInte1.addDevice(smartSpe1);
        assertTrue(casaInte1.existsDevice(1));
        assertTrue(casaInte1.existsDevice(2));
        casaInte1.addDevice(smartCam1);
        assertTrue(casaInte1.existsDevice(1));
        assertTrue(casaInte1.existsDevice(2));
        assertTrue(casaInte1.existsDevice(3));
    }

    /*
    * Função que testa o Método de getDevice();
    * */
    @Test
    public void testGetDevice() {
        CasaInteligente casa1 = new CasaInteligente("Gualtar");
        SmartBulb sb1 = new SmartBulb(1);
        SmartSpeaker sp1 = new SmartSpeaker(2);
        casa1.addDevice(sb1);
        casa1.addDevice(sp1);
        assertTrue(casa1.getDevice(1).equals(sb1));
        assertFalse(!casa1.getDevice(2).equals(sp1));
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
        SmartCamera sc = new SmartCamera(1);
        SmartBulb sb = new SmartBulb(2);
        SmartSpeaker sp = new SmartSpeaker(3);
        casa1.addDevice(sb);
        casa1.addDevice(sp);
        casa1.addDevice(sc);
        casa1.addRoom("sala");
        casa1.addRoom("quarto");
        casa1.addToRoom("sala", 1);
        casa1.addToRoom("sala", 2);
        casa1.addToRoom("quarto", 3);
        assertTrue(casa1.roomHasDevice("sala", 1));
        assertTrue(casa1.roomHasDevice("sala", 2));
        assertFalse(casa1.roomHasDevice("sala", 3));
        assertTrue(casa1.roomHasDevice("quarto", 3));
        assertFalse(casa1.roomHasDevice("quarto", 1));

    }

    @Test
    public void testConsumoRoom(){
        CasaInteligente casa1 = new CasaInteligente();
        SmartCamera sc1 = new SmartCamera(1,5.2,1024,0.25);
        SmartBulb sb1 = new SmartBulb(2,2.5,10,0.19);
        SmartSpeaker sp1 = new SmartSpeaker(3,15,"TVI",Marca.Sennheiser,0.35);
        SmartSpeaker sp2 = new SmartSpeaker(4,12,"SIC",Marca.Marshall,0.5);
        SmartCamera sc2 = new SmartCamera(5,4.3,1024,0.24);
        casa1.addDevice(sc2);
        casa1.addDevice(sp2);
        double sct = sc1.consumoEnergetico();
        double sbt = sb1.consumoEnergetico();
        double spt = sp1.consumoEnergetico();
        double res = sct+spt+sbt;
        assertFalse(sp1.getMode()==Modo.ON);
        sp1.turnOn();
        assertTrue(sp1.getMode() == Modo.ON);
        sc1.turnOn();
        sb1.turnOn();
        casa1.addDevice(sb1);
        casa1.addDevice(sp1);
        casa1.addDevice(sc1);
        casa1.addRoom("sala");
        casa1.addRoom("quarto");
        casa1.addToRoom("sala", 1);
        casa1.addToRoom("sala", 2);
        casa1.addToRoom("sala", 3);
        casa1.addToRoom("quarto", 4);
        casa1.addToRoom("quarto", 5);
        System.out.println(res);
        System.out.println(casa1.getConsumoRoom("sala"));
        assertTrue(casa1.getConsumoRoom("sala")==(sct+sbt+spt));
    }
}
