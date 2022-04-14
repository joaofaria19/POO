 

/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */ 
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/


/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos.
 */
public class SmartDevice {

    private String id; // código identificador do SmartDevice
    private boolean on; // identificador se se encontra desligado ou ligado
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
        this.on = false;
    }
    public SmartDevice(String s){
        this.id = s;
        this.on = false;
    }

    public SmartDevice(String s, boolean estado) {
        this.id = s;
        this.on = estado;
    }

    public SmartDevice(SmartDevice o) {
        this.id = o.getID();
        this.on = o.getOn();
    }


    public boolean getOn() {return this.on;}
    
    public void setOn(boolean b) { this.on = b;}
    
    public String getID() {return this.id;}

    public void setID(String b){ this.id = b;}


    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || this.getClass() != o.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return(this.id.equals(sd.getID()) && this.on == sd.getOn());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartDeviceId: ")
                .append(this.id)
                .append(" | On/Off: ")
                .append(this.on)
                .append(" ] \n");
        return sb.toString();
    }
    public SmartDevice clone(){
        return new SmartDevice(this);
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }


}
