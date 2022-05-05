import java.util.*;

/**
 * A CasaInteligente faz a gestão dos SmartDevices que existem e dos 
 * espaços (as salas) que existem na casa.
 */
public class CasaInteligente {

    private String proprietario;
    private Map<Integer, SmartDevice> devices; // String: identificador do SmartDevice
    private Map<String, List<Integer>> locations; // String: Divisão | Lista: codigo dos devices
    private int nif;
    private String nomeF;
    //private List<String> rooms; // lista com todas as divisões da casa
    /**
     * Constructor vazio
     */
    public CasaInteligente() {
        // initialise instance variables
        this.proprietario = "";
        this.devices = new HashMap();
        this.locations = new HashMap();
        this.nif = 0;
        this.nomeF = "";
    }

    /*
     * Construtor Parametrizavel
     * */

    public CasaInteligente(String proprietario, HashMap<String,SmartDevice> mydevices, HashMap<String,List<Integer>> mylocations, int nif, String nome) {
        // initialise instance variables
        this.proprietario = proprietario;
        this.devices = new HashMap();
        for(SmartDevice sd : mydevices.values()){
            this.devices.put(sd.getID(), sd.clone());
        }
        this.locations = mylocations;
        this.nif = nif;
        this.nomeF = nome;
    }

    /*
     * Construtor de cópia
     * */
    public CasaInteligente(CasaInteligente ci){
        this.proprietario = ci.getProprietario();
        this.locations = ci.getLocations();
        this.devices = ci.getDevices();
        this.nif = ci.getNif();
        this.nomeF = ci.getNomeF();
    }

    /*
     * Construtor adicional
     * */
    public CasaInteligente(String s){
        this.proprietario = s;
        this.locations = new HashMap<>();
        this.devices = new HashMap<>();
        this.nif = 0;
    }

    public Map<Integer,SmartDevice> getDevices(){
        Map<Integer,SmartDevice> dev = new HashMap<>();
        for(Map.Entry<Integer,SmartDevice> sd : this.devices.entrySet()){
            dev.put(sd.getKey(),sd.getValue().clone());
        }
        return dev;
    }

    public void setDevices(Map<Integer,SmartDevice> dev){
        this.devices = new HashMap<>();
        for(Map.Entry<Integer,SmartDevice> sd : dev.entrySet()){
            this.devices.put(sd.getKey(),sd.getValue().clone());
        }
    }

    public Map<String,List<Integer>> getLocations(){return this.locations;}

    public void setLocations(Map<String,List<Integer>> list){this.locations = list;}

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass());
        CasaInteligente ci = (CasaInteligente) o;
        return (this.proprietario.equals(ci.getProprietario())
                && this.devices.equals(ci.getDevices())
                && this.locations.equals(ci.getLocations()));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder("[Casa Inteligente]\n")
                .append("   Proprietario: ").append(this.proprietario).append("\n")
                .append("   Devices:{ ").append(this.devices).append("}\n")
                .append("   Locations:{ ").append(this.locations).append("}\n")
                .append("   NIF: ").append(this.nif).append("\n")
                .append("   Fornecedor: ").append(this.nomeF).append("\n\n");

        return sb.toString();
    }



    public CasaInteligente clone(){
        return new CasaInteligente(this);
    }


    /*
     * Método para ligar um certo dispositivo
     * s- Identificador do SmartDevice
     * */
    public void setDeviceOn(int s) {
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
    public boolean existsDevice(int id) {return this.devices.containsKey(id);}


    /*
     * Método para obter um determinado SmartDevice
     * Retorna null caso o SmartDevice nao esteja contido na Hash
     * */
    public SmartDevice getDevice(int s) {
        if(!this.devices.containsKey(s)) return null;
        else return this.devices.get(s).clone();
    }

    public String getProprietario() {
        return this.proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }


    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getNomeF(){
        return this.nomeF;
    }

    public void setNomeF(String nomeF){
        this.nomeF=nomeF;
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


    public void setMode(int s, Modo mode){
        this.devices.get(s).setMode(mode);
    }

    public void setAllMode(Modo mode) {
        for(SmartDevice sm : this.devices.values()){
            sm.setMode(mode);
        }
    }

    /*
     * Método para acrescentar uma divisao nova a CasaInteligente
     */
    public void addRoom(String room) {
        List<Integer> list = new ArrayList<>();
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
    public void addToRoom (String room, int SmartDevice) {
        if(!existRoom(room)){
            addRoom(room);
        }
        this.locations.get(room).add(SmartDevice);
        //addDevice(.clone());
    }

    /*
     * Método que verifica se um determinado device encontra-se naquela divisao
     * */
    public boolean roomHasDevice (String room, int SmartDevice) {
        return this.locations.get(room).contains(SmartDevice);
    }

    public Set<SmartDevice> getAllDevices(){
            return new HashSet<>(this.devices.values());
    }


    /*
     * Método para obter o consumo total de uma divisão
     *
     * ATENÇAO => Perguntar se o encapsulamento é violado neste caso*/
    public double getConsumoRoom(String room){
        double res=0;
        for(Integer s: this.locations.get(room)){
            if (this.devices.get(s).getMode() == Modo.ON) {
                res += this.devices.get(s).consumoEnergetico();
            }
        }
        return res;
    }
    /*
    * Método para obter o consumo total de uma casa
    * */
    public double getConsumo(){
        double res = 0.0;
        CasaInteligente casa = new CasaInteligente();
        List<Integer> lista = new ArrayList<>();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms = casa.getLocations().values().stream().toList();
        for(List<Integer> l : rooms){
            lista.addAll(l);
        }
        Iterator<Integer> it = lista.iterator();
        while(it.hasNext()){
            Integer r = it.next();
            res+= this.devices.get(r).consumoEnergetico();
        }
        return res;
    }



}
