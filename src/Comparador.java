import java.util.Comparator;

public class Comparador implements Comparator<Fatura> {
    public int compare(Fatura o1, Fatura o2) {
        if(o1.getNIF()==o2.getNIF()) return 0;
        else if(o1.getNIF()>o2.getNIF())return 1;
        else return -1;
    }
}
