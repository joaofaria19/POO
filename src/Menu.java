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
        //sb.append("#                                        #\n");
        sb.append("# ---------------SMART HOME MANAGER---------------#\n");
        sb.append("#                                                 #\n");
        sb.append("# Selecione uma das seguintes opções:             #\n");
        sb.append("#                                                 #\n");
        //sb.append("# (1) Registar um novo SmartDevice       #\n");
        //sb.append("# (2) Registar uma nova Divisão          #\n");
        sb.append("# (1) Verificar Casas existentes                  #\n");
        //sb.append("# (4) Verificar Divisões existentes      #\n");
        sb.append("# (2) Verificar Fornecedores existentes           #\n");
        sb.append("# (3) Verificar Casas Associadas a um Fornecedor  #\n");
        sb.append("# (4) Carregar um estado                          #\n");
        sb.append("# (5) Iniciar simulação                           #\n");
        sb.append("# (6) Registar uma nova Casa                      #\n");
        sb.append("# (7) Registar um novo Fornecedor                 #\n");
        sb.append("# (0) Sair                                        #\n");
        sb.append("###################################################\n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /* recebe uma hashMap de casasInteligentes*/
    public static void MenuShowCasa(List<CasaInteligente> casas){
        StringBuilder sb = new StringBuilder("---------CASAS EXISTENTES ----------\n");
        for(CasaInteligente casa : casas) {
            sb.append(casa.toString());
        }

        sb.append("\n\n(0)Voltar");
        System.out.println(sb);
    }
    /*
    public static void MenuCasa(List<CasaInteligente> casas){
        StringBuilder sb = new StringBuilder("---------CASA INTELIGENTE----------\n");
        sb.append("(1) Visualizar Casas Existentes\n");
        sb.append("(2) Visualizar divisoes existentes numa casa\n");
        sb.append("(3) Visualizar os Smart Devices de uma determinada casa\n");
        for(CasaInteligente casa : casas) {
            sb.append(casa.toString());
        }

        sb.append("\n\n(0)Voltar");
        System.out.println(sb);
    }*/

    public static void MenuShowFornecedor(List<Fornecedor> fornecedores){
        StringBuilder sb = new StringBuilder("---------FORNECEDORES EXISTENTES ----------\n");
        for(Fornecedor f : fornecedores) {
            sb.append(f.toString());
        }
        sb.append("\n(0)Voltar");
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
        sb.append("[Fornecedor] : ").append(fornecedor);
        for(CasaInteligente c:casas){
            sb.append(c.toString());
        }
        sb.append("\n(0)Voltar");
        System.out.println(sb);

    }
    public static void voltarPress(){
        Scanner scanner = new Scanner(System.in);
        int next = scanner.nextInt();
    }
    public static void MensagemFinal(){
        System.out.println("feitinho, adeus");
    }

    public static void MenuOpcaoCarregamento(){
        StringBuilder sb = new StringBuilder("O que pretende?\n\n(1) Carregar ficheiro de texto\n(2) Carregar ficheiro binário\n(0)Voltar");
        System.out.println(sb);

    }

    public static void MenuSimulacao(){
        StringBuilder sb = new StringBuilder("\n----------------MENU SIMULACAO--------------\n\n");

    }

}

