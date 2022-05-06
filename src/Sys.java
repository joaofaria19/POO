import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Sys {

    private Map<String,Fornecedor> fornecedores;
    private Map<String,CasaInteligente> casas;
    private int id;

    public Sys(){

        this.fornecedores = new HashMap<>();
        this.casas = new HashMap<>();
        this.id = 0;
    }

    public Sys(Map<String,Fornecedor> listaF,Map<String,CasaInteligente> listaC){
        this.fornecedores = new HashMap<>();
        for(Fornecedor f : listaF.values()){
            this.fornecedores.put(f.getId(),f.clone());
        }
        this.casas = new HashMap<>();
        for(CasaInteligente casa: listaC.values()){
            this.casas.put(casa.getProprietario(),casa.clone());
        }

    }


    public Sys(Sys lf){

        this.fornecedores = lf.getFornecedores();
        this.casas = lf.getCasas();

    }


    public Map<String,CasaInteligente> getCasas(){
        Map<String,CasaInteligente> res = new HashMap<>();
        for(Map.Entry<String,CasaInteligente> lf : this.casas.entrySet()){
            res.put(lf.getKey(),lf.getValue().clone());
        }
        return res;
    }

    public void setCasas(Map<String,CasaInteligente> res){
        this.casas = new HashMap<>();
        for(Map.Entry<String,CasaInteligente> sd : res.entrySet()){
            this.casas.put(sd.getKey(),sd.getValue().clone());
        }
    }


    public Map<String,Fornecedor> getFornecedores(){
        Map<String,Fornecedor> res = new HashMap<>();
        for(Map.Entry<String,Fornecedor> lf : this.fornecedores.entrySet()){
            res.put(lf.getKey(),lf.getValue().clone());
        }
        return res;
    }

    public void setFornecedores(Map<String,Fornecedor> res){
        this.fornecedores = new HashMap<>();
        for(Map.Entry<String,Fornecedor> sd : res.entrySet()){
            this.fornecedores.put(sd.getKey(),sd.getValue().clone());
        }
    }

    public void addFornecedor(String s,Fornecedor f){
        this.fornecedores.put(s,f.clone());
    }

    public void addCasa(String s,CasaInteligente f){
        this.casas.put(s,f.clone());
    }

    public void addCasaToFornecedor(CasaInteligente casa , String f){
        List<Fornecedor> sl = new ArrayList<>();
        sl = this.fornecedores.values().stream().toList();
        for(Fornecedor forn:sl) {
            if (f.equals(forn.getId())) {
                forn.addCasa(casa.clone());
            }

        }
    }




    public void addDeviceToCasa(String casa, SmartDevice sd) throws ObjectNullException{
        if(this.casas.containsKey(casa)) {
            this.casas.get(casa).addDevice(sd.clone());
        }else{
            throw new ObjectNullException("Casa a qual pretende adicionar o device não existe");
        }
    }

    public String toString(){
        StringBuilder sb  =new StringBuilder();
        sb.append(this.fornecedores);
        return sb.toString();

    }



    public List<CasaInteligente> getCasasAssociadas(String f) throws ObjectNullException{
        if(this.fornecedores.containsKey(f)) {
            List<CasaInteligente> res = new ArrayList<>();


            for (CasaInteligente c : this.casas.values()) {
                if (f.equals(c.getNomeF())) {
                    res.add(c.clone());
                }
            }
            return res;
        }else{
            List<CasaInteligente> res = new ArrayList<>();
            return res;
            //throw new ObjectNullException("Fornecedor não existente");
        }


    }

    public int getID(){
        return this.id;
    }
    public void idInc(){
         this.id++;
    }
}
