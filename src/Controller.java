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

    public static void run() throws FileNotFoundException, ObjectNullException {
        //int opcao = Menu.MenuInicial();
        Sys s = new Sys();
        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 7) {
                Menu.MensagemOpcaoInvalida();
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
                    //CasaInteligente casa = new CasaInteligente();
                    //for(CasaInteligente casa : casas) {
                    //    System.out.println(casa.getDevices());
                    //}
                    if(casas.isEmpty()) {
                        Menu.MensagemNoCasas();
                        Menu.voltarPress();
                    }
                    else {
                        Menu.MenuShowCasa(casas);
                        Menu.voltarPress();
                    }


                    //Menu.MenuInicial();
                    break;
                case 2:

                    List<Fornecedor> l = new ArrayList<>();
                    l = s.getFornecedores().values().stream().map(Fornecedor::clone).collect(Collectors.toList());

                    List<Fornecedor> lf = new ArrayList<>();
                    for(Fornecedor f:l){
                        lf.add(f.clone());
                    }
                    if(lf.isEmpty()) {
                        Menu.MensagemNoFornecedores();
                        Menu.voltarPress();
                    }
                    else {
                        Menu.MenuShowFornecedor(lf);
                        Menu.voltarPress();
                    }


                    //Menu.MenuInicial();
                    break;
                case 3:
                    //Menu.parsing();


                    String fornecedor = Menu.MenuShowFornecedorCasas();

                    List<CasaInteligente> casa = s.getCasasAssociadas(fornecedor);

                    Menu.MenuShowCasasAssociadas(fornecedor,casa);
                    Menu.voltarPress();
                    break;
                case 4:
                    Menu.MenuOpcaoCarregamento();
                    int next4 = 1;
                    Parse.parsing(next4,s);

                    break;
                case 5:
                    Menu.MenuSimulacao();
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
                case 6:
                    String[] novaCasa = Menu.MenuNovaCasa();
                    Parse.novaCasa(novaCasa, s);
                    System.out.println(novaCasa[0]);

                    break;
                case 7:
                    String novoFornecedor = Menu.MenuNovoFornecedor();
                    //Fornecedor f = new Fornecedor(novoFornecedor);
                    //s.addFornecedor(f.getId(),f.clone());
                    Parse.novoFornecedor(novoFornecedor, s);
                    break;
                default:
                    System.out.println("Insira uma opção válida\n");
                    Menu.MenuInicial();
            }

        }
    }
}
