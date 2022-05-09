 

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
    private Marca marca;
    private double consumoDiario;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        // initialise instance variables
        super();
        this.volume = 0;
        this.channel = "";
        this.marca = Marca.NULL;
        this.consumoDiario = 0.0;
    }

    public SmartSpeaker(int canal) {
        // initialise instance variables
        super(canal);
        this.volume = 0;
        this.channel = "";
        this.marca = Marca.NULL;
        this.consumoDiario = 0.0;
    }

    public SmartSpeaker(int cod, int volume, String channel, Marca marca,double consumoDiario) {
        // initialise instance variables
        super(cod);
        // verificar se o volume fornecido é positivo
        this.volume = Math.max(volume, 0);
        this.channel = channel;
        this.marca = marca;
        this.consumoDiario = consumoDiario;
    }

    public SmartSpeaker(SmartSpeaker ssp){
        super(ssp);
        this.volume = ssp.getVolume();
        this.channel = ssp.getChannel();
        this.marca = ssp.getMarca();
        this.consumoDiario= ssp.getConsumoDiario();
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

    public Marca getMarca(){return this.marca;}

    public void setMarca(Marca marca) {this.marca = marca;}

    public double getConsumoDiario(){return this.consumoDiario;}

    public void setConsumoDiario(double consumoDiario) {this.consumoDiario = consumoDiario;}

    public boolean equals(Object o){
        if(o==this)return true;
        if(o==null || o.getClass()!= this.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartSpeaker sb = (SmartSpeaker) o;
        return (this.channel.equals(sb.getChannel()) && this.volume == sb.getVolume() && this.marca.equals(sb.getMarca()) && this.consumoDiario==sb.getConsumoDiario());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n       » SmartSpeaker:{ ")
                .append(super.toString())
                .append(" | Volume: ")
                .append(this.volume)
                .append(" | Channel: ")
                .append(this.channel)
                .append(" | Marca: ")
                .append(this.marca)
                .append(" | Consumo Diario: ")
                .append(this.consumoDiario)
                .append(" }");
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartSpeaker(this);
    }

    public double consumoEnergetico() {
        double custo = 1;
        switch (this.marca) {
            case Sony:
                custo=1.6;
                break;
            case JBL:
                custo=2.3;
                break;
            case LG:
                custo=1.5;
                break;
            case Philips:
                custo=1.9;
                break;
            case Sennheiser:
                custo=1.3;
                break;
            case Goodis:
                custo=1.8;
                break;
            case BOSE:
                custo=2.5;
                break;
            case BangOlufsen:
                custo=2.7;
                break;
            case BowersWilkins:
                custo=2.9;
                break;
            case Marshall:
                custo=2.1;
                break;
            default:
                custo =1;
                break;
        }
        return custo*0.1*this.volume*this.consumoDiario;
    }
}
