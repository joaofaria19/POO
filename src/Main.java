import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CasaInteligente casa1 = new CasaInteligente();
        SmartCamera sc1 = new SmartCamera("sc1",5.2,1024);
        SmartBulb sb1 = new SmartBulb("sb1",2.5,10);
        SmartSpeaker sp1 = new SmartSpeaker("sp1","TVI",15,Marca.Sonos);
        SmartSpeaker sp2 = new SmartSpeaker("sp2","SIC",12,Marca.Marshall);
        SmartCamera sc2 = new SmartCamera("sc2",4.3,1024);
        casa1.addDevice(sb1);
        casa1.addDevice(sp1);
        casa1.addDevice(sc1);
        casa1.addDevice(sc2);
        casa1.addDevice(sp2);
        double sct = sc1.consumoDispositivo();
        double sbt = sb1.consumoDispositivo();
        double spt = sp1.consumoDispositivo();
        casa1.addRoom("sala");
        casa1.addRoom("quarto");
        casa1.addToRoom("sala", "sp1");
        casa1.addToRoom("sala", "sb1");
        casa1.addToRoom("sala", "sc1");
        casa1.addToRoom("quarto", "sc2");
        casa1.addToRoom("quarto", "sp2");
        Menu m = new Menu();
        int resultado = Menu.MenuInicial();
        Menu.options(resultado);
    }
}
