import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Sys {

    Map<String,Fornecedor> fornecedores;
    Map<String,CasaInteligente> casas;
    int id;

    public Sys(){

        this.fornecedores = new HashMap<>();
        this.casas = new HashMap<>();
        this.id = 0;
    }

    public Sys(Map<String,Fornecedor> listaF){
        this.fornecedores = new HashMap<>();
        for(Fornecedor f : listaF.values()){
            this.fornecedores.put(f.getId(),f.clone());
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

    public void addCasaToFornecedor(CasaInteligente casa , String fs){
        List<Fornecedor> lf = this.fornecedores.values().stream().map(Fornecedor::clone).toList();
        for(Fornecedor f : lf){
            if(fs.equals(f.getId())){
                f.addCasa(casa);
            }
        }

    }

    public String toString(){
        StringBuilder sb  =new StringBuilder();
        sb.append(this.fornecedores);
        return sb.toString();

    }

    public int getID(){
        return this.id;
    }
    public int idInc(){
        return this.id++;

    }
}
