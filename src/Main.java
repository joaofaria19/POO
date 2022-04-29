import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu m = new Menu();
        int resultado = Menu.MenuInicial();
        Menu.options(resultado);
    }
}
