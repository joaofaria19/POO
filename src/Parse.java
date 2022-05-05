import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parse {
    private static int id=0;
    public static void parseLine(String type, String args){
        //String[] line = token.split(":");
        //System.out.printf("%s%n %s%n", type, args);

        //List<String> linhas = lerFicheiro("Logs.txt");


    }

    public static int inc_id(int id){
        return id++;
    }
    public static void parsing(int next,Sys s) throws FileNotFoundException {
        //Map<String,Fornecedor> fornecedores = new HashMap<>();
        if(next ==1) {
            try (Scanner scanner = new Scanner(new File("Logs.txt"));) {
                scanner.useDelimiter("\n:");

                while (scanner.hasNext()) {
                    String token = scanner.next();

                    String[] parts = token.split("\n");
                    String divisao = null;
                    String casaS = null;
                    CasaInteligente casa = null;
                    SmartDevice sd;

                    Map<String,CasaInteligente> casas = new HashMap<>();

                    for(int j = 0; j < parts.length; j++) {


                        String[] type = parts[j].split(":");
                        //System.out.printf("%s%n", type[0]);
                        //4Parse.parseLine(type[0], type[1]);

                        String[] argsSplited = type[1].split(",");



                        switch(type[0]){
                            case "Fornecedor":
                                System.out.println("é fornecedor");
                                Fornecedor f = new Fornecedor(argsSplited[0]);
                                s.addFornecedor(f.getId(),f.clone());
                                break;
                            case "Casa":
                                System.out.println("é casa");
                                casa = new CasaInteligente(argsSplited[0]);
                                casaS = casa.getProprietario();
                                casa.setNif(Integer.parseInt(argsSplited[1]));
                                casa.setNomeF(argsSplited[2]);

                                //System.out.println(casa.toString());
                                s.addCasa(casa.getProprietario(),casa.clone());
                                s.addCasaToFornecedor(casa,argsSplited[2]);
                                break;
                            case "Divisao":
                                System.out.println("é divisao");
                                divisao=(argsSplited[0]);

                                if(casas.size() == 0) System.out.println("Erro\n");
                                else casas.get(casaS).addRoom(divisao);
                                break;
                            case "SmartBulb":
                                System.out.println("é smartbulb");
                                //SmartBulb lampada = new SmartBulb();
                                //String idb = String.valueOf(id);
                                double modo = 0;
                                double dim= Double.parseDouble(argsSplited[1]);
                                double custo1= Double.parseDouble(argsSplited[2]);
                                if("Warm".equals(argsSplited[0])) modo = 2.5;
                                else if ("Neutral".equals(argsSplited[0])) modo = 1.5;
                                else if("Warm".equals(argsSplited[0])) modo = 1;
                                sd = new SmartBulb(String.valueOf(id),modo,dim,custo1);
                                casa.addDevice(sd);
                                casa.addToRoom(divisao,sd.getID());
                                inc_id(id);
                                break;
                            case "SmartCamera":
                                System.out.println("é camara");
                                String[] resolucao = argsSplited[0].split("x");
                                double comp = Double.parseDouble(resolucao[0].substring(1));
                                //System.out.println(comp);

                                double alt = Double.parseDouble(resolucao[1].substring(0,resolucao[1].length()-1));
                                //System.out.println(alt);
                                double resolution = (comp * alt)/100;
                                //System.out.println(resolution);
                                int filesize= Integer.parseInt(argsSplited[1]);
                                double custo2= Double.parseDouble(argsSplited[2]);

                                sd = new SmartCamera(String.valueOf(id),resolution,filesize,custo2);
                                casa.addDevice(sd);
                                casa.addToRoom(divisao,sd.getID());
                                inc_id(id);
                                break;
                            case "SmartSpeaker":
                                System.out.println("é speaker");

                                int volume = Integer.parseInt(argsSplited[0]);
                                double custo3= Double.parseDouble(argsSplited[3]);

                                Marca marca = null;
                                if("LG".equals(argsSplited[3])) marca=Marca.LG;
                                else if("Sony".equals(argsSplited[3])) marca=Marca.Sony;
                                else if("Philips".equals(argsSplited[3])) marca=Marca.Philips;
                                else if("Marshall".equals(argsSplited[3])) marca=Marca.Marshall;
                                else if("BOSE".equals(argsSplited[3])) marca=Marca.BOSE;
                                else if("Bang&Olufsen".equals(argsSplited[3])) marca=Marca.BangOlufsen;
                                else if("Bowers&Wilkins".equals(argsSplited[3])) marca=Marca.BowersWilkins;
                                else if("Sennheiser".equals(argsSplited[3])) marca=Marca.Sennheiser;
                                else if("Goodis".equals(argsSplited[3])) marca=Marca.Goodis;
                                else marca= Marca.NULL;

                                sd = new SmartSpeaker(String.valueOf(id),volume,argsSplited[1],marca,custo3);
                                casa.addDevice(sd);
                                casa.addToRoom(divisao,sd.getID());
                                inc_id(id);
                                break;
                            default:
                                System.out.println("default");
                                break;



                        }
                    }

                }
            }

        }
        else{
            //carregar de binário
        }
        System.out.println(s);
    }

}
