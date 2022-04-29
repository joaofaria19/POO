import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
    public static int MenuInicial(){
        StringBuilder sb = new StringBuilder("##########################################\n");
        //sb.append("#                                        #\n");
        sb.append("# ----------SMART HOME MANAGER---------- #\n");
        sb.append("#                                        #\n");
        sb.append("# Selecione uma das seguintes opções:    #\n");
        sb.append("#                                        #\n");
        sb.append("# (1) Registar um novo SmartDevice       #\n");
        sb.append("# (2) Registar uma nova Divisão          #\n");
        sb.append("# (3) Verificar SmartDevices existentes  #\n");
        sb.append("# (4) Verificar Divisões existentes      #\n");
        sb.append("# (5) Verificar Fornecedores existentes  #\n");
        sb.append("# (6) Registar um novo Fornecedor        #\n");
        sb.append("# (6) Carregar um estado                 #\n");
        sb.append("# (0) Sair                               #\n");
        sb.append("##########################################\n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void options(int op) throws FileNotFoundException {
        switch(op){
            case 0:
                break;
            case 1:
                System.out.println("op 1");
                break;
            case 2:
                //Menu.parsing();
                System.out.println("op 2");
                break;
            case 3:

                System.out.println("O que pretende?\n\n(1) Carregar ficheiro de texto\n(2) Carregar ficheiro binário");
                Scanner scanner = new Scanner(System.in);
                int next = scanner.nextInt();
                Menu.parsing(next);
                break;
            default:
                System.out.println("Insira uma opção válida");
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
                    String[] parts = token.split(":");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    System.out.printf("%s%n", token);
                    System.out.printf("%s%n", parts[0]);
                }
            }
        }
        else{
            //carregar de binário
        }
    }
}

