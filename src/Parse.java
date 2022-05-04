import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parse {
    public static void parseLine(String type, String args){
        //String[] line = token.split(":");
        //System.out.printf("%s%n %s%n", type, args);

        //List<String> linhas = lerFicheiro("Logs.txt");

        Map<String,Fornecedor> fornecedores =new HashMap<>();
        Map<String,CasaInteligente> casas =new HashMap<>();
        String[] argsSplited = args.split(",");
        String divisao = null;
        String casaS = null;
        CasaInteligente casa = null;
        SmartDevice sd;
        int id=0;
        switch(type){
            case "Fornecedor":
                System.out.println("é fornecedor");
                Fornecedor f = new Fornecedor(argsSplited[0]);
                fornecedores.put(f.getId(),f.clone());
                break;
            case "Casa":
                System.out.println("é casa");
                casa = new CasaInteligente(argsSplited[0]);
                casaS = casa.getProprietario();
                casa.setNif(Integer.parseInt(argsSplited[1]));
                casa.setNomeF(argsSplited[2]);

                //System.out.println(casa.toString());
                casas.put(casa.getProprietario(),casa.clone());
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
                String idb = String.valueOf(id);
                double modo= Double.parseDouble(argsSplited[0]);
                double dim= Double.parseDouble(argsSplited[1]);
                double custo1= Double.parseDouble(argsSplited[2]);

                sd = new SmartBulb(idb,modo,dim,custo1);
                casa.addDevice(sd);
                casa.addToRoom(divisao,sd.getID());
                id++;
                break;
            case "SmartCamera":
                System.out.println("é camara");
                double resolution = Double.parseDouble(argsSplited[9]);
                int filesize= Integer.parseInt(argsSplited[1]);
                double custo2= Double.parseDouble(argsSplited[2]);

                sd = new SmartCamera(idc,resolution,filesize,custo2);
                casa.addDevice(sd);
                casa.addToRoom(divisao,sd.getID());
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

                sd = new SmartSpeaker(ids,volume,argsSplited[1],marca,custo3);
                casa.addDevice(sd);
                casa.addToRoom(divisao,sd.getID());
                break;
            default:
                System.out.println("default");
                break;



        }
    }

    public static void parsing(int next) throws FileNotFoundException {
        if(next ==1) {
            try (Scanner scanner = new Scanner(new File("Logs.txt"));) {
                scanner.useDelimiter("\n:");
                //int ntoken = 0;
                while (scanner.hasNext()) {
                    String token = scanner.next();
                    //ntoken++;
                    String[] parts = token.split("\n");

                    for(int j = 0; j < parts.length; j++) {
                        //String part1 = parts[j];
                        // String[] line = token.split(":");
                        //String part2 = parts[1];
                        //System.out.printf("%s%n", token);

                        String[] type = parts[j].split(":");
                        //System.out.printf("%s%n", type[0]);
                        Parse.parseLine(type[0], type[1]);
                    }
                }
            }
        }
        else{
            //carregar de binário
        }
    }

}
