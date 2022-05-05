import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

public class Controller {

    public static void run() throws FileNotFoundException {
        //int opcao = Menu.MenuInicial();
        Sys s = new Sys();
        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 7) {
                opcao = Menu.MenuInicial();
            }

            switch (opcao) {
                case 0:
                    Menu.MensagemFinal();
                    System.exit(0);
                    break;
                case 1:
                    //display casas


                    List<CasaInteligente> casas = new ArrayList<>();
                    //List<Fornecedor> listaf = new ArrayList<>();
                    casas = s.getCasas().values().stream().map(CasaInteligente::clone).collect(Collectors.toList());

                    //System.out.println(listaF);
                    /*
                    for (Fornecedor f : listaf) {
                        for(CasaInteligente casa : f.getCasasAssociadas().values().stream().map(CasaInteligente::clone).toList()) {
                            casas.add(casa.clone());
                        }//System.out.println(f.toString());

                    }*/
                    Menu.MenuShowCasa(casas);

                    Scanner scanner = new Scanner(System.in);
                    int next = scanner.nextInt();
                    if (next == 0) //Menu.MenuInicial();
                    break;
                case 2:
                    //System.out.println("op 2");
                    //display fornecedores
                    List<Fornecedor> l = new ArrayList<>();
                    l = s.getFornecedores().values().stream().map(Fornecedor::clone).collect(Collectors.toList());

                    List<Fornecedor> lf = new ArrayList<>();
                    for(Fornecedor f:l){
                        lf.add(f.clone());
                    }

                    Menu.MenuShowFornecedor(lf);

                    Scanner scanner2 = new Scanner(System.in);
                    int next2 = scanner2.nextInt();
                    if (next2 == 0) //Menu.MenuInicial();
                    break;
                case 3:
                    //Menu.parsing();
                    System.out.println("Escreva o nome do fornecedor\n\n(0)Voltar");
                    Scanner scanner3 = new Scanner(System.in);
                    int next3 = scanner3.nextInt();
                    if (next3 == 0) Menu.MenuInicial();
                    else ;
                    break;
                case 4:
                    Menu.MenuOpcaoCarregamento();

                    Scanner scanner4 = new Scanner(System.in);
                    int next4 = scanner4.nextInt();
                    if (next4 == 0) Menu.MenuInicial();
                    else Parse.parsing(next4,s);
                    //System.out.println(s.toString());
                    //Menu.MenuInicial();
                    break;
                case 5:
                    LocalDate today = now();
                    System.out.println("Data de hoje: ");
                    String formattedDate = today.format(DateTimeFormatter
                            .ofLocalizedDate(FormatStyle.LONG));
                    System.out.println(formattedDate);
                    System.out.println("\nInsira uma data no formato d/MM/yyyy");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    Scanner sc = new Scanner(System.in); //System.in is a standard input stream
                    String date = sc.nextLine();
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
}
