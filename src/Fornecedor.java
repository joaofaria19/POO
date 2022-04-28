import java.util.*;

public class Fornecedor {
    private String id;
    private double valorbase;
    private double imposto;
    private Map<String,CasaInteligente> casasAssociadas; // o identificador de cada casa será a sua morada

    public Fornecedor(String id, double valorbase, double imposto, Map<String, CasaInteligente> casasAssociadas) {
        this.id = id;
        this.valorbase = valorbase;
        this.imposto = imposto;
        this.casasAssociadas = new HashMap<>();
        for(CasaInteligente sd : casasAssociadas.values()){
            this.casasAssociadas.put(sd.getMorada(), sd.clone());
        }
    }

    public Fornecedor() {
        Random rand = new Random();
        this.id = "";
        this.valorbase = rand.nextDouble(1.0,5.0);
        this.imposto = rand.nextDouble(0.1,2.0);
        this.casasAssociadas = new HashMap<>();

    }

    public Fornecedor(Fornecedor f) {
        this.id = f.getId();
        this.valorbase = f.getValorbase();
        this.imposto = f.getImposto();
        this.casasAssociadas = f.getCasasAssociadas();

    }

    public Fornecedor(String id) {
        Random rand = new Random();
        this.id = id;
        this.valorbase = rand.nextDouble(1.0,5.0);
        this.imposto = rand.nextDouble(0.1,0.99);
        this.casasAssociadas = new HashMap<>();

    }


    public void setCasasAssociadas(Map<String, CasaInteligente> casasAssociadas) {
        this.casasAssociadas = new HashMap<>();
        for(Map.Entry<String,CasaInteligente> sd : casasAssociadas.entrySet()){
            this.casasAssociadas.put(sd.getKey(),sd.getValue().clone());
        }
    }

    public Map<String,CasaInteligente> getCasasAssociadas(){
        Map<String,CasaInteligente> dev = new HashMap<>();
        for(Map.Entry<String,CasaInteligente> sd : this.casasAssociadas.entrySet()){
            dev.put(sd.getKey(),sd.getValue().clone());
        }
        return dev;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValorbase() {
        return valorbase;
    }

    public void setValorbase(double valorbase) {
        this.valorbase = valorbase;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getConsumoCasa(String casa){
            return this.casasAssociadas.get(casa).getConsumo();
    }

    public void addCasa(CasaInteligente casa){
        this.casasAssociadas.put(casa.getMorada(),casa.clone());
    }

    public CasaInteligente getCasaByMorada(String morada){
        return this.casasAssociadas.get(morada).clone();
    }

    public double formula(String morada){
        if(this.casasAssociadas.get(morada).getDevices().size() > 10 ){
            return (this.valorbase * this.casasAssociadas.get(morada).getConsumo() * (1 + this.imposto)) * 0.9;
        }
        else return (this.valorbase * this.casasAssociadas.get(morada).getConsumo() * (1+this.imposto) * 0.75);
    }

}
