import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static void run() throws IOException, ObjectNullException, ClassNotFoundException {
        Sys s = new Sys();
        while (true) {
            int opcao = -1;
            while (opcao < 0 || opcao > 10) {

                opcao = Menu.MenuInicial();
            }

            switch (opcao) {
                case 0:
                    Menu.Mensagem(1);
                    System.exit(0);
                    break;
                case 1:
                    // Carregar dados
                    int next4 = Menu.MenuOpcaoCarregamento();

                    Menu.Carregamento(next4);
                    Menu.Mensagem(2);
                    Parse.parsing(next4,s);

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
                    break;
                case 3:
                    String fornecedor = Menu.MenuShowFornecedorCasas();

                    List<CasaInteligente> casas1 = s.getCasasAssociadas(fornecedor);

                    Menu.MenuShowCasasAssociadas(fornecedor,casas1);

                    Menu.voltarPress();
                    break;
                case 4:

                    List<CasaInteligente> casas2 = new ArrayList<>();
                    casas2 = s.getCasas().values().stream().map(CasaInteligente::clone).collect(Collectors.toList());
                    if(casas2.isEmpty()) {
                        Menu.MensagemNoCasas();
                        Menu.voltarPress();
                    }
                    else {
                        Menu.MenuShowCasa(casas2);
                        Menu.voltarPress();
                    }
                    break;
                    case 5:
                        Menu.voltarPress();
                        break;
                case 6:
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
                case 7:
                    String[] novaCasa = Menu.MenuNovaCasa();
                    s.novaCasa(novaCasa);
                    System.out.println(novaCasa[0]);
                    Menu.Mensagem(6);
                    break;
                case 8:
                    String[] novoFornecedor = Menu.MenuNovoFornecedor();
                    s.novoFornecedor(novoFornecedor);
                    Menu.Mensagem(7);
                    break;
                case 9:
                    s.guardaEstado();
                    Menu.Mensagem(3);
                    break;
                case 10:
                    CasaInteligente casa = new CasaInteligente();
                    String cr1 = Menu.MenuAddDeviceC();
                    if (!s.existsProprietario(cr1))
                    {
                        Menu.Mensagem(4);
                        break;
                    }
                    String cr2 = Menu.MenuAddDeviceR();
                    if(!s.existsRoom(cr1,cr2)){
                        Menu.Mensagem(5);
                        break;
                    }

                    int next = Menu.MenuVerificarDevice();
                    switch (next){
                        case 1:
                            String[] aux1 = Menu.MenuSmartBulb();
                            s.addSmartToCasaToRoom(s.createSmartBulb(aux1),cr1,cr2);
                            break;
                        case 2:
                            String[] aux2 = Menu.MenuSmartCamera();
                            s.addSmartToCasaToRoom(s.createSmartCamera(aux2),cr1,cr2);
                            break;
                        case 3:
                            String[] aux3 = Menu.MenuSmartSpeaker();
                            s.addSmartToCasaToRoom(s.createSmartSpeaker(aux3),cr1,cr2);

                            break;
                            default:
                            break;
                    }
                    break;
                default:
                    Menu.MenuInicial();
            }

        }
    }
}
