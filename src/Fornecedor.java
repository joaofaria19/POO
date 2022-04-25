import java.util.*;

public abstract class Fornecedor {
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
        this.id = "";
        this.valorbase = 0;
        this.imposto = 0;
        this.casasAssociadas = new HashMap<>();

    }

    public Fornecedor(Fornecedor f) {
        this.id = f.getId();
        this.valorbase = f.getValorbase();
        this.imposto = f.getImposto();
        this.casasAssociadas = f.getCasasAssociadas();

    }

    public Fornecedor(String id) {
        this.id = id;
        this.valorbase = 0;
        this.imposto = 0;
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

    public CasaInteligente getCasaByMorada(String morada){
        return this.casasAssociadas.get(morada).clone();
    }

    public abstract double formula(String morada, String id);
}
