import java.io.File;
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

    public static void run() throws IOException, ObjectNullException, ClassNotFoundException, ObjectEmpty {
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

                    try {
                        Parse.parsing(next4, s);
                    } catch (FileNotFoundException e) {
                        Menu.errors(1);
                    }

                    break;
                case 2:
                    try {
                        List<Fornecedor> l = new ArrayList<>();
                        l = s.getListFornecedor();
                        Menu.MenuShowFornecedor(l);
                        Menu.voltarPress();
                    } catch (ObjectEmpty oe1) {
                        Menu.errors(5);
                    }
                    break;
                case 3:

                    try {
                        String fornecedor = Menu.MenuShowFornecedorCasas();
                        List<CasaInteligente> casas1 = s.getCasasAssociadas(fornecedor);
                        Menu.MenuShowCasasAssociadas(fornecedor, casas1);
                        Menu.voltarPress();
                    } catch (ObjectNullException e1) {
                        Menu.errors(2);
                    }


                    break;
                case 4:

                    try {
                        List<CasaInteligente> casas5 = new ArrayList<>();
                        casas5 = s.getListCasas();
                        Menu.MenuShowCasa(casas5);
                        Menu.voltarPress();
                    } catch (ObjectEmpty oe2) {
                        Menu.errors(4);
                    }
                    break;
                case 5:
                    int next1 = Menu.MenuEstadoDevices();
                    int estado = Menu.MenuEstado();
                    switch (next1) {
                        case 1:
                            String string = Menu.MenuEstadoCasa();
                            System.out.println(string);
                            s.alteraEstadoCasa(string, estado);
                            break;
                        case 2:
                            String string1 = Menu.MenuEstadoCasa();
                            String string2 = Menu.MenuEstadoRoom();
                            s.alteraEstadoRoom(string1, string2, estado);
                            break;
                        case 3:
                            String string3 = Menu.MenuEstadoCasa();
                            int id = Menu.MenuEstadoID();
                            s.alteraEstadoDevice(string3, id, estado);
                            break;
                        default:
                            break;

                    }


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
                    Menu.Mensagem(4);
                    break;

                case 8:

                    String[] novoFornecedor = Menu.MenuNovoFornecedor();
                    s.novoFornecedor(novoFornecedor);
                    Menu.Mensagem(5);
                    break;

                case 9:

                    try {
                        s.guardaEstado();
                        Menu.Mensagem(3);
                    } catch (FileNotFoundException e) {
                        Menu.errors(1);
                    }
                    break;

                case 10:
                    try {
                        CasaInteligente casa = new CasaInteligente();
                        String cr1 = Menu.MenuAddDeviceC();
                        s.existsProprietario(cr1);
                        String cr2 = Menu.MenuAddDeviceR();
                        s.existsRoom(cr1, cr2);

                        int next = Menu.MenuVerificarDevice();
                        switch (next) {
                            case 1:
                                String[] aux1 = Menu.MenuSmartBulb();
                                s.addSmartToCasaToRoom(s.createSmartBulb(aux1), cr1, cr2);
                                break;
                            case 2:
                                String[] aux2 = Menu.MenuSmartCamera();
                                s.addSmartToCasaToRoom(s.createSmartCamera(aux2), cr1, cr2);
                                break;
                            case 3:
                                String[] aux3 = Menu.MenuSmartSpeaker();
                                s.addSmartToCasaToRoom(s.createSmartSpeaker(aux3), cr1, cr2);
                                break;
                                default:
                                    break;
                        }
                    }
                    catch(ObjectEmpty oe3){Menu.errors(6);}
                    catch(ObjectNullException one3){Menu.errors(7);}
                    break;
                default:
                    Menu.MenuInicial();
            }

        }
    }
}
