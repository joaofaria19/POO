import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestFornecedor {
    /* Default constructor for test class CasaInteligenteTest
     */
    public TestFornecedor()
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

    @Test
    public void testConstructor() {
        CasaInteligente casa1 = new CasaInteligente("Gualtar");
        SmartBulb sb = new SmartBulb("sb",2.5,40.0,0.15);
        SmartSpeaker ssp = new SmartSpeaker("ssp",15,"SIC", Marca.Sony,0.25);
        String room = new String();
        Fornecedor forn = new Fornecedor("EDP");

        Map<String,SmartDevice> mydevices = new HashMap<>();
        Map<String, List<String>> mylocation = new HashMap<>();
        Map<String, CasaInteligente> casasAssociadas = new HashMap<>();

        casa1.addDevice(sb);
        casa1.addDevice(ssp);
        casa1.addRoom(room);

        sb.consumoEnergetico();
        ssp.consumoEnergetico();

        forn.addCasa(casa1);

        //forn.setImposto(0.5);
        //forn.setValorbase(1.5);
        forn.formula("Gualtar");

        //assertTrue(forn != null);
        assertTrue(forn.formula("Gualtar")!=0.0);

    }
}
