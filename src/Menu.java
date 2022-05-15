import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Menu {
    public static int MenuInicial() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder
                 ("#########################################################\n");
        sb.append("#                                                       #\n");
        sb.append("# ------------------SMART HOME MANAGER------------------#\n");
        sb.append("#                                                       #\n");
        sb.append("# (1)  Carregar um Estado                               #\n");
        sb.append("# (2)  Verificar Fornecedores existentes                #\n");
        sb.append("# (3)  Verificar Casas Associadas a um Fornecedor       #\n");
        sb.append("# (4)  Verificar Casas existentes                       #\n");
        sb.append("# (5)  Ligar ou Desligar dispositivos de uma casa       #\n");
        sb.append("# (6)  Iniciar simulação                                #\n");
        sb.append("# (7)  Registar uma nova Casa                           #\n");
        sb.append("# (8)  Registar um novo Fornecedor                      #\n");
        sb.append("# (9)  Salvar Estado atual                              #\n");
        sb.append("# (10) Adicionar um Samart Device a uma Casa existente  #\n");
        sb.append("# (0)  Sair                                             #\n");
        sb.append("#                                                       #\n");
        sb.append("#########################################################\n\n");

        sb.append("Selecione uma das opções acima:");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /* recebe uma Lista de casasInteligentes*/
    public static void MenuShowCasa(List<CasaInteligente> casas){
        StringBuilder sb = new StringBuilder("---------CASAS EXISTENTES ----------\n");
        for(CasaInteligente casa : casas) {
            sb.append(casa.toString());
        }

        System.out.println(sb);
    }
    /* recebe uma lista de fornecedores*/
    public static void MenuShowFornecedor(List<Fornecedor> fornecedores){
        StringBuilder sb = new StringBuilder("---------FORNECEDORES EXISTENTES ----------\n");
        for(Fornecedor f : fornecedores) {
            sb.append(f.toString());
        }
        System.out.println(sb);
    }

    public static String MenuShowFornecedorCasas(){
        StringBuilder sb = new StringBuilder("\n=> Indique qual o fornecedor: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void MenuShowCasasAssociadas(String fornecedor,List<CasaInteligente> casas){
        StringBuilder sb = new StringBuilder("---------CASAS ASSOCIADAS----------");
        sb.append("\n[Fornecedor] : ").append(fornecedor);
        if (casas.isEmpty()){
            System.out.println("Este Fornecedor não existe ou ainda não tem casas associadas\n\n(0)Voltar");
        }

        else{
            for(CasaInteligente c:casas){
                sb.append(c.toString());
                System.out.println(sb);
            }
        }


    }


    public static void voltarPress(){
        System.out.println("\n(0)Voltar\n");
        Scanner scanner = new Scanner(System.in);
        int next = scanner.nextInt();
    }

    public static void MensagemOpcaoInvalida(){
        System.out.println("Insira uma opção válida\n");
    }
    public static void MensagemNoCasas(){
        System.out.println("Ainda não existem casas\n\n");
    }
    public static void MensagemNoFornecedores(){
        System.out.println("Ainda não existem Fornecedores\n\n");
    }

    public static int MenuOpcaoCarregamento(){
        StringBuilder sb = new StringBuilder("O que pretende?\n\n(1) Carregar ficheiro de texto\n(2) Carregar ficheiro binário\n(0)Voltar");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }

    public static void MenuSimulacao(){
        StringBuilder sb = new StringBuilder("\n----------------MENU SIMULACAO--------------\n\n");

    }

    public static String MenuAddDeviceC(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=> Indique o nome do Proprietario da casa onde pretende adicionar o Smart Device");
        return scanner.next();
    }

    public static String MenuAddDeviceR(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=> Indique a divisao da casa onde pretende adicionar o Smart Device");
        return scanner.next();
    }



    public static int MenuVerificarDevice() {
        StringBuilder sb = new StringBuilder("\n--------------SMART DEVICE---------------\n");
        sb.append("\nQue tipo de SmartDevice pretende adicionar?\n")
                .append("(1) Smart Bulb\n")
                .append("(2) Smart Camera\n")
                .append("(3) Smart Speaker\n");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String[] MenuSmartBulb(){
        StringBuilder sb = new StringBuilder("--------------SMART BULB---------------\n");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", ""};
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=> Indique o tom da lampada que pretende adicionar");
        args[0] = scanner.next();
        System.out.println("\n=> Indique a dimensao da lampada que pretende adicionar");
        args[1] = scanner.next();
        System.out.println("\n=> Indique o consumo da lampada que pretende adicionar");
        args[2] = scanner.next();
        return args;
    }

    public static String[] MenuSmartCamera(){
        StringBuilder sb = new StringBuilder("--------------SMART CAMERA---------------\n");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", ""};
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=> Indique o tamanho do ficheiro em bytes");
        args[0] = scanner.next();
        System.out.println("\n=> Indique a resolução da gravação(formato 000x00)");
        args[1] = scanner.next();
        System.out.println("\n=> Indique o consumo da lampada que pretende adicionar");
        args[2] = scanner.next();
        return args;
    }

    public static String[] MenuSmartSpeaker(){
        StringBuilder sb = new StringBuilder("--------------SMART SPEAKER---------------\n");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", "", ""};
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=> Indique o volume do smart speaker");
        args[0] = scanner.next();
        System.out.println("\n=> Indique o canal no qual esta sincronizado");
        args[1] = scanner.next();
        System.out.println("\n=> Indique a marca do Smart Speaker");
        args[2] = scanner.next();
        System.out.println("\n=> Indique o consumo da lampada que pretende adicionar");
        args[3] = scanner.next();
        return args;
    }


    public static String[] MenuNovoFornecedor(){
        StringBuilder sb = new StringBuilder("\n=> Insira o nome do Fornecedor a adiconar\n(Caso este nome já exista, o novo Fornecedor vai sobrescrever o antigo)");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", ""};
        Scanner scanner = new Scanner(System.in);
        args[0] = scanner.nextLine();
        System.out.println("=> Insira o valor base do custo do kwh de energia");
        args[1] = scanner.next();
        System.out.println("=> Insira o valor do imposto do Fornecedor(%)");
        args[2] = scanner.next();

        return args;
    }

    public static String[] MenuNovaCasa(){
        StringBuilder sb = new StringBuilder("\n=> Insira o nome do proprietario da Casa a adiconar\n(Caso este nome já exista, a nova Casa vai sobrescrever a antiga)");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", "",""};
        Scanner scanner = new Scanner(System.in);
        args[0] = scanner.nextLine();
        System.out.println("=> Insira o NIF");
        args[1] = scanner.next();
        System.out.println("=> Insira o nome de um Fornecedor Existente");
        args[2] = scanner.next();
        System.out.println("=> Pretende adicionar alguma divisão à casa?(S/N)");
        args[3] = scanner.next();
        return args;
    }

    public static void Carregamento(int next){
        StringBuilder sb = new StringBuilder();
        if(next==1) {
            sb.append("\n\nA carregar dados de ficheiro...\n\n");
        }else{
            sb.append("\n\nA carregar dados de binário...\n\n");
        }
        System.out.println(sb);
    }

    public static void Mensagem(int mensagem){
        switch (mensagem){
            case 1:
                System.out.println("\nfeitinho, adeus\n");
                break;
            case 2:
                System.out.println("\nEstado carregado\n");
                break;
                case 3:
                System.out.println("\nEstado salvo!!\n");
                break;
            case 4:
                System.out.println("\nCasa adicionada com sucesso\n");
                break;
            case 5:
                System.out.println("\nFornecedor adicionado com sucesso\n");
                break;


            default:
                break;
        }
    }


    public static void errors(int mensagem) {
        switch(mensagem){
            case 1:
                System.out.println("\n[ERRO] Ficheiro não encontrado\n");
                break;
            case 2:
                System.out.println("\n[ERRO] Fornecedor não encontrado\n");
                break;
            case 3:
                System.out.println("\n[ERRO] Casa não encontrada\n");
                break;
            case 4:
                System.out.println("\n[ERRO] Lista de casas vazia\n");
                break;
            case 5:
                System.out.println("\n[ERRO] Lista de fornecedores vazia\n");
                break;
            case 6:
                System.out.println("\n[ERRO] O proprietario referido nao existe\n");
                break;
            case 7:
                System.out.println("\n[ERRO] A divisao referida nao existe\n");
                break;

        }

    }
    public static String newRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=> Indique o nome da divisao que pretende adicionar");
        return scanner.next();
    }

    public static String MenuROOM(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=> Pretende adicionar mais divisoes?(S/N)");
        return scanner.next();
    }

    public static int MenuEstadoDevices(){
        StringBuilder sb = new StringBuilder("Escolha uma das opçõs seguintes:\n");
        sb.append("(1)Ligar/Desligar todos os dispositivos de uma casa\n");
        sb.append("(2)Ligar/Desligar todos os dispositivos de uma divisao de uma casa\n");
        sb.append("(3)Ligar/Desligar um determinado dispositivo\n");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }

    public static String MenuEstadoCasa(){
        System.out.println("\n=> Indique o nome do proprietario da casa\nonde pretende alterar o estado dos respetivos devices");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String MenuEstadoRoom(){
        System.out.println("\n=> Indique o nome da divisao da casa\nonde pretende alterar o estado dos respetivos devices");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public static int MenuEstadoID(){
        System.out.println("\n=> Indique o id do SmartDevice que quer alterar o estado");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int MenuEstado(){
        StringBuilder sb = new StringBuilder("O que petende fazer com os smart devices?\n");
        sb.append("(1) Ligar os Devices\n");
        sb.append("(2) Desligar os Devices\n");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}


