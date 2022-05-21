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
            while (opcao < 0 || opcao > 15) {

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
                    // Guardar dados
                    try {
                        s.guardaEstado();
                        Menu.Mensagem(3);
                    } catch (FileNotFoundException e) {
                        Menu.errors(1);
                    }
                    break;

                case 3:
                    // Verificar Fornecedores existentes
                    try {
                        List<Fornecedor> l = new ArrayList<>();
                        l = s.getListFornecedor();
                        Menu.MenuShowFornecedor(l);
                        Menu.voltarPress();
                    } catch (ObjectEmpty oe1) {
                        Menu.errors(5);
                    }
                    break;

                case 4:
                    // Verificar Casas Inteligentes
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
                    // Verificar as Casas Associadas a um Fornecedor
                    try {
                        String fornecedor = Menu.MenuShowFornecedorCasas();
                        List<CasaInteligente> casas1 = s.getCasasAssociadas(fornecedor);
                        Menu.MenuShowCasasAssociadas(fornecedor, casas1);
                        Menu.voltarPress();
                    } catch (ObjectNullException e1) {
                        Menu.errors(2);
                    }

                    break;

                case 6:
                    // Criar um novo Fornecedor
                    String[] novoFornecedor = Menu.MenuNovoFornecedor();
                    s.novoFornecedor(novoFornecedor);
                    Menu.Mensagem(5);
                    break;

                case 7:
                    // Criar uma nova Casa
                    String[] novaCasa = Menu.MenuNovaCasa();
                    s.novaCasa(novaCasa);
                    System.out.println(novaCasa[0]);
                    Menu.Mensagem(4);
                    break;

                case 8:
                    // Adicionar um SmartDevice a uma casa
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

                case 9:
                    // Ligar/Desligar SmartDevices
                    int next1 = Menu.MenuEstadoDevices();
                    int estado = Menu.MenuEstado();
                    switch (next1) {
                        case 1:
                            try {
                                String string = Menu.MenuEstadoCasa();
                                System.out.println(string);
                                s.alteraEstadoCasa(string, estado);
                            } catch (ObjectNullException one6){Menu.errors(6);}
                            break;
                        case 2:
                            try {
                                String string1 = Menu.MenuEstadoCasa();
                                String string2 = Menu.MenuEstadoRoom();
                                s.alteraEstadoRoom(string1, string2, estado);
                            } catch (ObjectNullException one7){Menu.errors(8);}
                            break;
                        case 3:
                            try {
                                String string3 = Menu.MenuEstadoCasa();
                                int id = Menu.MenuEstadoID();
                                s.alteraEstadoDevice(string3, id, estado);
                            } catch (ObjectNullException one5){Menu.errors(9);}
                            break;
                        default:
                            break;

                    }
                    if(estado==1) Menu.Mensagem(6);
                    else Menu.Mensagem(7);
                    break;
                case 10:
                    // Iniciar Simulação
                    Menu.MenuSimulacao();
                    long days = Menu.Simulacao();
                    s.makeAllFaturas(days);
                    Menu.Mensagem(8);
                    break;
                case 11:
                    // Fatura da Casa
                    try{
                        String fatura = Menu.MenuFatura();
                        Menu.showFatura(s.showFaturaCasa(fatura));
                        Menu.voltarPress();
                    }catch(ObjectNullException one4){Menu.errors(6);}
                case 12:
                    // Mudar o Fornecedor de uma casa
                    try{
                        String[] args = Menu.MenuFornecedorCasa();
                        s.mudaFornecedor(args);
                        Menu.Mensagem(9);
                    }
                    catch(ObjectNullException one6){Menu.errors(6);}
                    catch(ObjectEmpty oe6){Menu.errors(2); }
                    break;
                case 13:
                    // Casa com maior gasto
                    List<Fatura> fl = s.getFaturas();
                    s.ordenaFaturas(fl);
                    Menu.CasaMaisCara(fl.get(0));
                    Menu.voltarPress();
                    break;
                case 14:
                    // Alterar dados de um Fornecedor
                    try {
                        String[] args = Menu.MenuAlteraDadosFornecedor();
                        s.alteraDadosFornecedor(args);
                        Menu.Mensagem(10);
                    }catch(ObjectNullException one7) {Menu.errors(2);}
                    break;
                case 15:
                    // Verificar Faturação de um Fornecedor
                    try{
                        String ff = Menu.FaturasFornecedor();
                        List<Fatura> lf2= s.faturasFornecedor(ff);
                        Menu.printFaturasList(lf2);
                        Menu.voltarPress();
                    }catch(ObjectNullException one8) {Menu.errors(2);}
                    break;
                    default:
                    Menu.MenuInicial();

            }

        }
    }
}
