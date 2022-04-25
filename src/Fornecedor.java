import java.util.*;

public class Fornecedor {
    private double formula;
    private Map<String,CasaInteligente> casasAssociadas; // o identificador de cada casa será a sua morada



    public double getConsumoCasa(String casa){
            return this.casasAssociadas.get(casa).getConsumo();
    }


}
