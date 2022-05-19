import java.io.Serializable;

public class Fatura implements Serializable {
    private String nomeProprietario;
    private String nomeFornecedor;
    private int NIF;
    private double precoTotal;

    public Fatura(){
        this.nomeProprietario = "";
        this.nomeFornecedor = "";
        this.NIF = 0;
        this.precoTotal = 0.0;
    }

    public Fatura(String nomeProprietario, String nomeFornecedor, int NIF, double precoTotal) {
        this.nomeProprietario = nomeProprietario;
        this.nomeFornecedor = nomeFornecedor;
        this.NIF = NIF;
        this.precoTotal = precoTotal;
    }

    public Fatura(Fatura f){
        this.nomeProprietario = f.getNomeProprietario();
        this.nomeFornecedor = f.getNomeFornecedor();
        this.NIF = f.getNIF();
        this.precoTotal = f.getPrecoTotal();
    }

    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public int getNIF() {
        return this.NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public double getPrecoTotal() {
        return this.precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass()!=this.getClass());
        Fatura ci = (Fatura) o;
        return (this.nomeProprietario.equals(ci.getNomeProprietario())
                && this.nomeFornecedor.equals(ci.getNomeFornecedor())
                && this.precoTotal==ci.getPrecoTotal()
                && this.NIF==ci.getNIF());
    }



    public String toString(){
        StringBuilder sb = new StringBuilder
                 ("\n-------------------FATURA----------------------\n\n");
        sb.append("-> Proprietario:").append(this.nomeProprietario).append("\n");
        sb.append("-> Fornecedor:").append(this.nomeFornecedor).append("\n");
        sb.append("-> NIF:").append(this.NIF).append("\n");
        sb.append("-> Preço Total:").append(this.precoTotal).append("$\n");
        sb.append("\n-----------------------------------------------\n");
        return sb.toString();
    }

    public Fatura clone(){
        return new Fatura(this);
    }


}
