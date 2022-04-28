
/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos e identificar um SmartDevice pelo seu id.
 */
public abstract class SmartDevice {

    private String id; // código identificador do SmartDevice
    private Modo mode; // identificador se o device se encontra desligado(false) ou ligado(true)

    /* Para quando precisarmos de verificar o consumo energético de um smartDevice
        public void turnOn(){
        this.mode=true;
        this.instanteOn = System.currentTimeMillis();
    }

    public void turnOff(){
        long now = System.currentTimeMillis();
        long period = now-this.instanteOn;
        totalConsumo += period*this.consumo;
        periodoConsumo += period*this.consumo;
    }

    */
    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        this.id = "";
        this.mode = Modo.OFF;
    }
    public SmartDevice(String s){
        this.id = s;
        this.mode = Modo.OFF;
    }

    public SmartDevice(String s, Modo estado) {
        this.id = s;
        this.mode = estado;
    }

    public SmartDevice(SmartDevice o) {
        this.id = o.getID();
        this.mode = o.getMode();
    }


    public Modo getMode() {return this.mode;}
    
    public void setMode(Modo b) { this.mode = b;}
    
    public String getID() {return this.id;}

    public void setID(String b){ this.id = b;}


    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || this.getClass() != o.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return(this.id.equals(sd.getID()) && this.mode == sd.getMode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartDeviceId: ")
                .append(this.id)
                .append(" | On: ")
                .append(this.mode)
                .append(" ] \n");
        return sb.toString();
    }
    public abstract SmartDevice clone();

    public void turnOn() {
        this.mode = Modo.ON;
    }

    public void turnOff() {
        this.mode = Modo.OFF;
    }

    public abstract double consumoDispositivo();
}