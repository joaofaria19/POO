 

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
    private double consumoDiario;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        // initialise instance variables
        super();
        this.tone = NEUTRAL;
        this.dimensao = 0.0;
        this.consumoDiario = 0.0;
    }

    public SmartBulb(int id, double tone, double dimensao, double consumoDiario) {

        super(id);
        this.tone = tone;
        this.dimensao = dimensao;
        this.consumoDiario = consumoDiario;
    }

    public SmartBulb(int id) {

        super(id);
        this.tone = NEUTRAL;
        this.dimensao = 0.0;
        this.consumoDiario = 0.0;

    }

    public SmartBulb(SmartBulb b) {

        super(b);
        this.tone = b.getTone();
        this.dimensao = b.getDimensao();
        this.consumoDiario = b.getConsumoDiario();
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

    public double getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;
        if(!super.equals(o)) return false;
        SmartBulb sb = (SmartBulb) o;
        return (this.tone == sb.getTone() && this.dimensao==sb.getDimensao() && this.consumoDiario==sb.getConsumoDiario());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n       » SmartBulb:{ ")
                .append(super.toString())
                .append(" | Tom: ")
                .append(verificaTom(this.tone))
                .append(" | Dimensao(cm): ")
                .append(this.dimensao)
                .append(" | Cosnumo Diario: ")
                .append(this.consumoDiario)
                .append(" }");
        return sb.toString();
    }

    public SmartDevice clone(){
        return new SmartBulb(this);
    }

    public String verificaTom(double tom){
        if(tom == 1.5) return "Neutral";
        else if(tom == 2.5) return "Cold";
        else return "Warm";
    }

    public double consumoEnergetico() {
        return (2.4)*this.tone*this.consumoDiario;
    }
}

