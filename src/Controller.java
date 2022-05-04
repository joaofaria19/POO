import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Controller {

    public static void run() throws FileNotFoundException {
        int opcao = Menu.MenuInicial();
        switch(opcao){
            case 0:
                Menu.MensagemFinal();
                System.exit(0);
                break;
            case 1:
                //display casas
                CasaInteligente casa1 = new CasaInteligente("Gualtar");
                CasaInteligente casa2 = new CasaInteligente("Gualtarir");
                Fornecedor f = new Fornecedor("EDP");
                f.addCasa(casa1);
                f.addCasa(casa2);
                Menu.MenuShowCasa(f.getCasasAssociadas());


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
                Menu.MenuOpcaoCarregamento();

                Scanner scanner4 = new Scanner(System.in);
                int next4 = scanner4.nextInt();
                if(next4 == 0) Menu.MenuInicial();
                else Parse.parsing(next4);
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
                System.out.println("Insira uma opção válida\n");
                Menu.MenuInicial();
        }
    }
}
