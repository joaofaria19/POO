public class Endesa extends Fornecedor{
    @Override
    public double formula(String morada, String id) {

        if(super.getCasaByMorada(morada).getDevices().size() > 10 ){
            return (getValorbase() * super.getCasaByMorada(morada).getDevice(id).consumoDispositivo() * (1 + getImposto())) * 0.9;
        }
        else return (super.getValorbase() * super.getCasaByMorada(morada).getDevice(id).consumoDispositivo() * (1+super.getImposto())) * 0.75;
    }
}
