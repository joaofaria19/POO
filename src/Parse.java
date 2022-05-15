import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parse {

    public static void parsing(int next,Sys s) throws IOException, ObjectNullException, ClassNotFoundException {
        //Map<String,Fornecedor> fornecedores = new HashMap<>();
        if(next ==1) {
            try (Scanner scanner = new Scanner(new File("Logs.txt"));) {
                scanner.useDelimiter("\n:");

                while (scanner.hasNext()) {
                    String token = scanner.next();

                    String[] parts = token.split("\n");
                    String divisao = null;
                    String casaName = null;
                    CasaInteligente casa = null;
                    SmartDevice sd;

                    for(int j = 0; j < parts.length; j++) {

                        String[] type = parts[j].split(":");
                        String[] argsSplited = type[1].split(",");

                        switch(type[0]){
                            case "Fornecedor":

                                Fornecedor f = new Fornecedor(argsSplited[0]);
                                s.addFornecedor(f.getId(),f.clone());
                                break;
                            case "Casa":

                                casa = new CasaInteligente(argsSplited[0]);
                                casaName = casa.getProprietario();
                                casa.setNif(Integer.parseInt(argsSplited[1]));
                                casa.setNomeF(argsSplited[2]);

                                s.addCasa(casaName,casa.clone());
                                try{
                                s.addCasaToFornecedor(casa.clone(),argsSplited[2]);}
                                catch(ObjectNullException e){Menu.errors(2);}

                                break;
                            case "Divisao":

                                divisao=(argsSplited[0]);

                                if(s.getCasas().size() == 0) System.out.println("Erro\n");
                                else s.getCasas().get(casaName).addRoom(divisao);
                                break;
                            case "SmartBulb":

                                int ID1 = s.getID();
                                double modo = 0;
                                double dim= Double.parseDouble(argsSplited[1]);
                                double custo1= Double.parseDouble(argsSplited[2]);

                                if("Warm".equals(argsSplited[0])) modo = 2.5;
                                else if ("Neutral".equals(argsSplited[0])) modo = 1.5;
                                else if("Cold".equals(argsSplited[0])) modo = 1;

                                sd = new SmartBulb(ID1,modo,dim,custo1);

                                try {
                                    s.addDeviceToCasa(casaName, sd.clone());
                                }
                                catch(ObjectNullException e){Menu.errors(3);}

                                s.getCasas().get(casaName).addToRoom(divisao,sd.getID());
                                s.idInc();
                                break;
                            case "SmartCamera":

                                int ID2 = s.getID();
                                // transformar resoluções do tipo (1920x80) num double
                                String[] resolucao = argsSplited[0].split("x");
                                double comp = Double.parseDouble(resolucao[0].substring(1));
                                double alt = Double.parseDouble(resolucao[1].substring(0,resolucao[1].length()-1));
                                double resolution = (comp * alt)/100;

                                int filesize= Integer.parseInt(argsSplited[1]);
                                double custo2= Double.parseDouble(argsSplited[2]);

                                sd = new SmartCamera(ID2,resolution,filesize,custo2);

                                try {
                                    s.addDeviceToCasa(casaName, sd.clone());
                                }
                                catch(ObjectNullException e){Menu.errors(3);}

                                s.getCasas().get(casaName).addToRoom(divisao,sd.getID());
                                s.idInc();
                                break;
                            case "SmartSpeaker":

                                int ID3 = s.getID();
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

                                sd = new SmartSpeaker(ID3,volume,argsSplited[1],marca,custo3);

                                try {
                                    s.addDeviceToCasa(casaName, sd.clone());
                                }
                                catch(ObjectNullException e){Menu.errors(3);}

                                s.getCasas().get(casaName).addToRoom(divisao,sd.getID());
                                s.idInc();
                                break;
                            default:
                                break;

                        }
                    }
                }

            }

        }
        else{
            //carregar de binário
            s.carregaEstado();
            Menu.Mensagem(2);
        }

    }

}
