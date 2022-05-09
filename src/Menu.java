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
        StringBuilder sb = new StringBuilder("###################################################\n");
        sb.append("#                                                 #\n");
        sb.append("# ---------------SMART HOME MANAGER---------------#\n");
        sb.append("#                                                 #\n");
        sb.append("# (1) Carregar um Estado                          #\n");
        sb.append("# (2) Verificar Fornecedores existentes           #\n");
        sb.append("# (3) Verificar Casas Associadas a um Fornecedor  #\n");
        sb.append("# (4) Verificar Casas existentes                  #\n");
        sb.append("# (5) Verificar dispositivos de uma casa          #\n");
        sb.append("# (6) Iniciar simulação                           #\n");
        sb.append("# (7) Registar uma nova Casa                      #\n");
        sb.append("# (8) Registar um novo Fornecedor                 #\n");
        sb.append("# (9) Salvar Estado atual                         #\n");
        sb.append("# (0) Sair                                        #\n");
        sb.append("#                                                 #\n");
        sb.append("###################################################\n\n");

        sb.append("Selecione uma das opções acima:");
        System.out.println(sb.toString());
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


    public static String MenuShowDevicesCasas(){
        StringBuilder sb = new StringBuilder("\n=> Indique qual a Casa na qual quer consultar os SmarDevices: ");
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
    public static void MensagemFinal(){
        System.out.println("feitinho, adeus");
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
    public static String[] MenuNovoFornecedor(){
        StringBuilder sb = new StringBuilder("\n=> Insira o nome do Fornecedor a adiconar\nCaso este nome já exista, o novo Fornecedor vai sobrescrever o antigo");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", ""};
        Scanner scanner = new Scanner(System.in);
        args[0] = scanner.nextLine();
        System.out.println("=> Insira o valor base do custo do kwh de energia");
        args[1] = scanner.next();
        System.out.println("=> Insira o valor do imposto do Fornecedor(%)");
        args[2] = scanner.next();
        System.out.println(args[2]);
        return args;
    }

    public static String[] MenuNovaCasa(){
        StringBuilder sb = new StringBuilder("\n=> Insira o nome do proprietario da Casa a adiconar\n(Caso este nome já exista, a nova Casa vai sobrescrever a antiga)");
        System.out.println(sb.toString());
        String [] args = new String[]{"", "", ""};
        Scanner scanner = new Scanner(System.in);
        args[0] = scanner.nextLine();
        System.out.println("=> Insira o NIF");
        args[1] = scanner.next();
        System.out.println("=> Insira o nome de um Fornecedor Existente");
        args[2] = scanner.next();
        System.out.println(args[2]);
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

}

