public class Edp extends Fornecedor{


    @Override
    public double formula(String morada, String id) {
        return (super.getValorbase() * super.getCasaByMorada(morada).getDevice(id).consumoDispositivo() * (1+super.getImposto())) * 0.9;
    }


}
