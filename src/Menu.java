import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Menu {
    public static void MenuInicial() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder("##########################################\n");
        //sb.append("#                                        #\n");
        sb.append("# ----------SMART HOME MANAGER---------- #\n");
        sb.append("#                                        #\n");
        sb.append("# Selecione uma das seguintes opções:    #\n");
        sb.append("#                                        #\n");
        //sb.append("# (1) Registar um novo SmartDevice       #\n");
        //sb.append("# (2) Registar uma nova Divisão          #\n");
        sb.append("# (1) Verificar Casas existentes         #\n");
        //sb.append("# (4) Verificar Divisões existentes      #\n");
        sb.append("# (2) Verificar Fornecedores existentes  #\n");
        sb.append("# (3) Registar um novo Fornecedor        #\n");
        sb.append("# (4) Carregar um estado                 #\n");
        sb.append("# (5) Iniciar simulação                  #\n");
        sb.append("# (0) Sair                               #\n");
        sb.append("##########################################\n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        Menu.options(scanner.nextInt());
    }

    public static void options(int op) throws FileNotFoundException {
        switch(op){
            case 0:
                System.out.println("feitinho, adeus");
                break;
            case 1:
                System.out.println("op 1");
                //display casas
                System.out.println("Selecione uma casa\n\n(0)Voltar");

                Scanner scanner = new Scanner(System.in);
                int next = scanner.nextInt();
                if(next == 0) Menu.MenuInicial();
                break;
            case 2:
                System.out.println("op 2");
                //display fornecedores

                System.out.println("Selecione um fornecedor\n\n(0)Voltar");

                Scanner scanner2 = new Scanner(System.in);
                int next2 = scanner2.nextInt();
                if(next2 == 0) Menu.MenuInicial();
                break;
            case 3:
                //Menu.parsing();
                System.out.println("Escreva o nome do fornecedor\n\n(0)Voltar");
                Scanner scanner3 = new Scanner(System.in);
                int next3 = scanner3.nextInt();
                if(next3 == 0) Menu.MenuInicial();
                else;
                break;
            case 4:

                System.out.println("O que pretende?\n\n(1) Carregar ficheiro de texto\n(2) Carregar ficheiro binário\n(0)Voltar");
                Scanner scanner4 = new Scanner(System.in);
                int next4 = scanner4.nextInt();
                if(next4 == 0) Menu.MenuInicial();
                else Menu.parsing(next4);
                break;
            case 5:
                LocalDate today = now();
                System.out.println("Data de hoje: ");
                String formattedDate = today.format(DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.LONG));
                System.out.println(formattedDate);
                System.out.println("\nInsira uma data no formato d/MM/yyyy");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                Scanner sc= new Scanner(System.in); //System.in is a standard input stream
                String date= sc.nextLine();
                LocalDate localDate = LocalDate.parse(date, formatter);
                long elapsedDays = ChronoUnit.DAYS.between(today, localDate);
                System.out.println("Dias a simular: ");
                System.out.println(elapsedDays);
                //Scanner scanner = new Scanner(System.in);
                //int next = scanner.nextInt();
                //if(next == 0) Menu.MenuInicial();
                //else Menu.parsing(next);
                break;
            default:
                System.out.println("Insira uma opção válida");
                Menu.MenuInicial();
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
                        Menu.parseLine(type[0], type[1]);
                    }
                }
            }
        }
        else{
            //carregar de binário
        }


    }
    public static void parseLine(String type, String args){
        //String[] line = token.split(":");
        //System.out.printf("%s%n %s%n", type, args);
        String[] argsSplited = args.split(",");

        switch(type){
            case "Fornecedor":
                System.out.println("é fornecedor");
                //Fornecedor f1 = new Fornecedor(args[0]);
                break;
            case "Casa":
                System.out.println("é casa");
                CasaInteligente casa = new CasaInteligente(argsSplited[0]);
                casa.setNif(Integer.parseInt(argsSplited[1]));
                casa.setNomeF(argsSplited[2]);
                System.out.println(casa.toString());
                break;
            case "Divisao":
                System.out.println("é divisao");
                break;
            case "SmartBulb":
                System.out.println("é smartbulb");
                //SmartBulb lampada = new SmartBulb();
                break;
            case "SmartCamera":
                System.out.println("é camara");
                break;
            case "SmartSpeaker":
                System.out.println("é speaker");
                break;
            default:
                System.out.println("default");
                break;
        }

    }
}

