 

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
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmartBulb extends SmartDevice {
    public static final double WARM = 2.5;
    public static final double NEUTRAL = 1.5;
    public static final double COLD = 1;
    
    private double tone;
    private double dimensao;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        // initialise instance variables
        super();
        this.tone = NEUTRAL;
        this.dimensao = 0.0;
    }

    public SmartBulb(String id, double tone, double dimensao) {

        super(id);
        this.tone = tone;
        this.dimensao = dimensao;
    }

    public SmartBulb(String id) {

        super(id);
        this.tone = NEUTRAL;
        this.dimensao = 0.0;

    }

    public SmartBulb(SmartBulb b) {

        super(b);
        this.tone = b.getTone();
        this.dimensao = b.getDimensao();
    }

    public void setTone(double t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public double getTone() {
        return this.tone;
    }

    public void setDimensao(double dim){
        this.dimensao = dim;
    }

    public double getDimensao(){
        return this.dimensao;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartBulb sb = (SmartBulb) o;
        return (this.tone == sb.getTone());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartBulb:{ ")
                .append(super.toString())
                .append(" tone: ")
                .append(this.tone)
                .append(" dimensao(cm): ")
                .append(this.dimensao)
                .append(" }\n");
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartBulb(this);
    }

    public double consumoDispositivo() {
        return (2.4)*this.tone;
    }
}

