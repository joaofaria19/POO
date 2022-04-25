import java.util.Scanner;

public class Menu {
    public static int MenuInicial(){
        StringBuilder sb = new StringBuilder("----------MENU INICIAL----------\n\n");
        sb.append("(1) Registar um novo SmartDevice\n");
        sb.append("(2) Registar uma mova Divisão\n");
        sb.append("(3) Verificar SmartDevices existentes\n");
        sb.append("(4) Verificar Divisões existentes\n");
        sb.append("(5) Verificar Fornecedores existentes\n");
        sb.append("(6) Registar um novo Fornecedor\n");
        sb.append("(0) Sair");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
