import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos 
 * espaços (as salas) que existem na casa.
 */
public class CasaInteligente {

    private String morada;
    private Map<String, SmartDevice> devices; // String: identificador do SmartDevice
    private Map<String, List<String>> locations; // String: Divisão | Lista: codigo dos devices

    /**
     * Constructor vazio
     */
    public CasaInteligente() {
        // initialise instance variables
        this.morada = "";
        this.devices = new HashMap();
        this.locations = new HashMap();
    }

    /*
     * Construtor Parametrizavel
     * */

    public CasaInteligente(String morada, HashMap<String,SmartDevice> mydevices, HashMap<String,List<String>> mylocations) {
        // initialise instance variables
        this.morada = morada;
        this.devices = new HashMap();
        for(SmartDevice sd : mydevices.values()){
            this.devices.put(sd.getID(), sd.clone());
        }
        this.locations = mylocations;
    }

    /*
     * Construtor de cópia
     * */
    public CasaInteligente(CasaInteligente ci){
        this.morada = ci.getMorada();
        this.locations = ci.getLocations();
        this.devices = ci.getDevices();
    }

    /*
     * Construtor adicional
     * */
    public CasaInteligente(String s){
        this.morada = s;
        this.locations = new HashMap<>();
        this.devices = new HashMap<>();
    }


    public String getMorada(){return this.morada;}

    public void setMorada(String morada){this.morada = morada;}

    public Map<String,SmartDevice> getDevices(){
        Map<String,SmartDevice> dev = new HashMap<>();
        for(Map.Entry<String,SmartDevice> sd : this.devices.entrySet()){
            dev.put(sd.getKey(),sd.getValue().clone());
        }
        return dev;
    }

    public void setDevices(Map<String,SmartDevice> dev){
        this.devices = new HashMap<>();
        for(Map.Entry<String,SmartDevice> sd : dev.entrySet()){
            this.devices.put(sd.getKey(),sd.getValue().clone());
        }
    }

    public Map<String,List<String>> getLocations(){return this.locations;}

    public void setLocations(Map<String,List<String>> list){this.locations = list;}

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass());
        CasaInteligente ci = (CasaInteligente) o;
        return (this.morada.equals(ci.getMorada())
                && this.devices.equals(ci.getDevices())
                && this.locations.equals(ci.getLocations()));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("Casa Inteligente\n")
                .append("Morada: ").append(this.morada).append("\n")
                .append("Devices: ").append(this.devices).append("\n")
                .append("Locations: ").append(this.locations).append("\n");
        return sb.toString();
    }

    public CasaInteligente clone(){
        return new CasaInteligente(this);
    }


    /*
     * Método para ligar um certo dispositivo
     * s- Identificador do SmartDevice
     * */
    public void setDeviceOn(String s) {
        this.devices.get(s).turnOn();
    }



    /*
     * Método para adicionar um novo SmartDevice
     * */
    public void addDevice(SmartDevice s) {
        this.devices.put(s.getID(),s.clone());
    }

    /*
     * Método que verifica se um determinado smart device existe
     *id- Identificador do SmartDevice
     */
    public boolean existsDevice(String id) {return this.devices.containsKey(id);}


    /*
     * Método para obter um determinado SmartDevice
     * Retorna null caso o SmartDevice nao esteja contido na Hash
     * */
    public SmartDevice getDevice(String s) {
        if(!this.devices.containsKey(s)) return null;
        else return this.devices.get(s).clone();
    }
    /*
     * Método para ligar um certo SmartDevice
     */
    public void setOn(String s) {
        this.devices.get(s).turnOn();
    }

    /*
     *Método que liga todos os SmartDevices
     */
    public void setAllOn() {
        for(SmartDevice sd : this.devices.values()){
            sd.turnOn();
        }
    }


    public void setMode(String s, boolean mode){
        this.devices.get(s).setMode(mode);
    }

    public void setAllMode(boolean mode) {
        for(SmartDevice sm : this.devices.values()){
            sm.setMode(mode);
        }
    }

    /*
     * Método para acrescentar uma divisao nova a CasaInteligente
     */
    public void addRoom(String room) {
        List<String> list = new ArrayList<>();
        this.locations.put(room,list);
    }

    /*
     * Método para verificar se uma determinada divisao existe
     * room- Identificador da divisao
     * */
    public boolean existRoom(String room) {
        return this.locations.containsKey(room);
    }

    /*
     * Método que permite adicionar um SmartDevice a uma divisao
     * Colocamos na lista de SmartDevices da divisao o smartDevice dado
     * */
    public void addToRoom (String room, String SmartDevice) {
        if(!existRoom(room)){
            addRoom(room);
        }
        this.locations.get(room).add(SmartDevice);
        //addDevice(.clone());
    }

    /*
     * Método que verifica se um determinado device encontra-se naquela divisao
     * */
    public boolean roomHasDevice (String room, String SmartDevice) {
        return this.locations.get(room).contains(SmartDevice);
    }



}
