import java.io.Serial;
import java.io.Serializable;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos e identificar um SmartDevice pelo seu id.
 */
public abstract class SmartDevice implements Serializable {

    private Integer id; // código identificador do SmartDevice
    private Modo mode; // identificador se o device se encontra desligado(false) ou ligado(true)

    /**
     * Constructor vazio
     */
    public SmartDevice() {
        this.id = 0;
        this.mode = Modo.OFF;
    }

    /**
     * Constructor adicional
     */
    public SmartDevice(Integer d){
        this.id = d;
        this.mode = Modo.OFF;
    }

    /**
     * Constructor parametrizavel
     */
    public SmartDevice(Integer d, Modo estado) {
        this.id = d;
        this.mode = estado;
    }

    /**
     * Constructor de cópia
     */
    public SmartDevice(SmartDevice o) {
        this.id = o.getID();
        this.mode = o.getMode();
    }


    public Modo getMode() {return this.mode;}
    
    public void setMode(Modo b) { this.mode = b;}
    
    public Integer getID() {return this.id;}

    public void setID(Integer b){ this.id = b;}


    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || this.getClass() != o.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return(this.id.equals(sd.getID()) && this.mode == sd.getMode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[SmartDeviceId: ")
                .append(this.id)
                .append(" | Estado: ")
                .append(this.mode)
                .append(" ] ");
        return sb.toString();
    }
    public abstract SmartDevice clone();

    public void turnOn() {
        this.mode = Modo.ON;
    }

    public void turnOff() {
        this.mode = Modo.OFF;
    }

    public abstract double consumoEnergetico();
}
