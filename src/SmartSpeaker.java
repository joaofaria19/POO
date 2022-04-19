 

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
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo
    
    private int volume;
    private String channel;
    private String marca;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        // initialise instance variables
        super();
        this.volume = 0;
        this.channel = "";
        this.marca = "";
    }

    public SmartSpeaker(String canal) {
        // initialise instance variables
        super(canal);
        this.volume = 0;
        this.channel = "";
        this.marca = "";
    }



    public SmartSpeaker(String cod, String channel, int i, String marca) {
        // initialise instance variables
        super(cod);
        // verificar se o volume fornecido é positivo
        this.volume = Math.max(i, 0);
        this.channel = channel;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ssp){
        super(ssp);
        this.volume = ssp.getVolume();
        this.channel = ssp.getChannel();
        this.marca = ssp.getMarca();
    }

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public void setVolume (int volume){ this.volume = volume;}

    public int getVolume() {return this.volume;}
    
    public String getChannel() {return this.channel;}

    public void setChannel(String c) {this.channel = c;}

    public String getMarca(){return this.marca;}

    public void setMarca(String marca) {this.marca = marca;}

    public boolean equals(Object o){
        if(o==this)return true;
        if(o==null || o.getClass()!= this.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartSpeaker sb = (SmartSpeaker) o;
        return (this.channel.equals(sb.getChannel()) && this.volume == sb.getVolume() && this.marca.equals(sb.getMarca()));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SmartSpeaker:{ ")
                .append(super.toString())
                .append(" Volume: ")
                .append(this.volume)
                .append(" Channel: ")
                .append(this.channel)
                .append(" Marca: ")
                .append(this.marca)
                .append(" }\n");
        return sb.toString();
    }

    public SmartSpeaker clone(){
        return new SmartSpeaker(this);
    }

}
