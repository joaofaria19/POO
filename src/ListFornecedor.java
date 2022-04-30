import java.util.HashMap;
import java.util.Map;

public class ListFornecedor {

    Map<String,Fornecedor> listaF;

    public ListFornecedor(){
        this.listaF = new HashMap<>();
    }

    public ListFornecedor(Map<String,Fornecedor> listaF){
        this.listaF = new HashMap<>();
        for(Fornecedor f : listaF.values()){
            this.listaF.put(f.getId(),f.clone());
        }

    }


    public ListFornecedor(ListFornecedor lf){
        this.listaF = lf.getListaF();
    }


    public Map<String,Fornecedor> getListaF(){
        Map<String,Fornecedor> res = new HashMap<>();
        for(Map.Entry<String,Fornecedor> lf : this.listaF.entrySet()){
            res.put(lf.getKey(),lf.getValue().clone());
        }
        return res;
    }

    public void setListaF(Map<String,Fornecedor> res){
        this.listaF = new HashMap<>();
        for(Map.Entry<String,Fornecedor> sd : res.entrySet()){
            this.listaF.put(sd.getKey(),sd.getValue().clone());
        }
    }


    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || this.getClass() != o.getClass()) return false;
        ListFornecedor lf = (ListFornecedor) o;
        return (this.listaF.equals(lf.getListaF()));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(" Lista Fornecedores: {")
                .append(this.listaF)
                .append("}\n");
        return sb.toString();
    }

    public ListFornecedor clone(){
        return new ListFornecedor(this);
    }
}
