import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Sys implements Serializable {

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

    public void addSmartToCasaToRoom(SmartDevice sd ,String arg1,String arg2){
        for (CasaInteligente c : this.casas.values()) {
            if (arg1.equals(c.getProprietario())) {
                c.addDevice(sd.clone());
                c.addToRoom(arg2,sd.clone().getID());
            }
        }

    }

    public int getID(){
        return this.id;
    }
    public void idInc(){
         this.id++;
    }



    public void novoFornecedor(String[] novoFornecedor){
        Fornecedor f = new Fornecedor(novoFornecedor[0]);
        f.setValorbase(Double.parseDouble(novoFornecedor[1]));
        f.setImposto(Double.parseDouble(novoFornecedor[2]));
        this.fornecedores.put(f.getId(),f.clone());


    }

    public void novaCasa(String[] novaCasa){
        String s;
        int aux =1;
        CasaInteligente casa = new CasaInteligente(novaCasa[0]);
        casa.setNif(Integer.parseInt(novaCasa[1]));
        casa.setNomeF(novaCasa[2]);
        if((novaCasa[3].toUpperCase(Locale.ROOT)).equals("S")){
            while(aux ==1){
                s = Menu.newRoom();
                casa.addRoom(s);
                String s2 = Menu.MenuROOM();
                if (s2.equals("N")) aux = 0;
            }
        }

        addCasa(casa.getProprietario(),casa.clone());
        addCasaToFornecedor(casa.clone(),novaCasa[2]);

    }


    public SmartBulb createSmartBulb(String[] args) {
        SmartBulb sd = new SmartBulb();
        sd.setID(this.id);
        sd.setMode(Modo.OFF);
        if ((args[0].toUpperCase(Locale.ROOT)).equals("WARM"))
            sd.setTone(2.5);
        else if ((args[0].toUpperCase(Locale.ROOT)).equals("NEUTRAL"))
        {
            sd.setTone(1.5);
        }else{
            sd.setTone(1);
        }
        sd.setDimensao(Double.parseDouble(args[1]));
        sd.setConsumoDiario(Double.parseDouble(args[2]));
        idInc();
        return sd;
    }


    public SmartCamera createSmartCamera(String[] args) {
        SmartCamera sd = new SmartCamera();
        sd.setID(this.id);
        sd.setMode(Modo.OFF);
        sd.setFileSize(Integer.parseInt(args[0]));
        String[] resolucao = args[1].split("x");
        double comp = Double.parseDouble(resolucao[0].substring(1));
        double alt = Double.parseDouble(resolucao[1].substring(0,resolucao[1].length()-1));
        double resolution = (comp * alt)/100;

        sd.setResolution(resolution);
        sd.setConsumoDiario(Double.parseDouble(args[2]));
        idInc();
        return sd;
    }



    public SmartSpeaker createSmartSpeaker(String[] args) {
        SmartSpeaker sd = new SmartSpeaker();
        sd.setID(this.id);
        sd.setMode(Modo.OFF);
        sd.setVolume(Integer.parseInt(args[0]));
        sd.setChannel(args[1]);

        if("LG".equals(args[2])) sd.setMarca(Marca.LG);
        else if("Sony".equals(args[2])) sd.setMarca(Marca.Sony);
        else if("Philips".equals(args[2])) sd.setMarca(Marca.Philips);
        else if("Marshall".equals(args[2])) sd.setMarca(Marca.Marshall);
        else if("BOSE".equals(args[2])) sd.setMarca(Marca.BOSE);
        else if("Bang&Olufsen".equals(args[2])) sd.setMarca(Marca.BangOlufsen);
        else if("Bowers&Wilkins".equals(args[2])) sd.setMarca(Marca.BowersWilkins);
        else if("Sennheiser".equals(args[2])) sd.setMarca(Marca.Sennheiser);
        else if("Goodis".equals(args[2])) sd.setMarca(Marca.Goodis);
        else sd.setMarca(Marca.NULL);

        sd.setConsumoDiario(Double.parseDouble(args[2]));
        idInc();
        return sd;
    }



    public SmartCamera addSmarCamera(String[] args,SmartCamera sd){
        sd.setFileSize(Integer.parseInt(args[0]));
        sd.setResolution(Double.parseDouble(args[1]));
        sd.setConsumoDiario(Double.parseDouble(args[2]));
        return sd;
    }

    public SmartSpeaker addSmarSpeaker(String[] args,SmartSpeaker sd){
        sd.setVolume(Integer.parseInt(args[0]));
        sd.setChannel(args[2]);

        Marca marca = null;
        if("LG".equals(args[3])) marca=Marca.LG;
        else if("Sony".equals(args[3])) marca=Marca.Sony;
        else if("Philips".equals(args[3])) marca=Marca.Philips;
        else if("Marshall".equals(args[3])) marca=Marca.Marshall;
        else if("BOSE".equals(args[3])) marca=Marca.BOSE;
        else if("Bang&Olufsen".equals(args[3])) marca=Marca.BangOlufsen;
        else if("Bowers&Wilkins".equals(args[3])) marca=Marca.BowersWilkins;
        else if("Sennheiser".equals(args[3])) marca=Marca.Sennheiser;
        else if("Goodis".equals(args[3])) marca=Marca.Goodis;
        else marca= Marca.NULL;

        sd.setMarca(marca);
        sd.setConsumoDiario(Double.parseDouble(args[4]));

        return sd;
    }

    public void guardaEstado() throws FileNotFoundException, IOException, FileNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database"));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public void carregaEstado() throws IOException, ClassNotFoundException {
        Sys sys = carregaEstadoAux();
        this.casas = sys.getCasas();
        this.fornecedores = sys.getFornecedores();
        this.id = sys.getID();
    }

    public Sys carregaEstadoAux() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("database"));
        Sys sys = (Sys) ois.readObject();
        ois.close();
        return sys;

    }

    public boolean existsProprietario(String proprietario){
        if(this.casas.containsKey(proprietario)) return true;
        else return false;
    }

    public boolean existsRoom(String proprietario,String Room){
        for (CasaInteligente c : this.casas.values()) {
            if(c.existRoom(Room)) return true;
        }
        return false;
    }

}
