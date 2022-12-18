import java.util.Scanner;

public class DBMenu {
    private static SQL sql;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        int option = 0;

        while (option != 5) {
            System.out.println("========Menú========");
            System.out.println("[1] Mostrar datos");
            System.out.println("[2] Insertar datos");
            System.out.println("[3] Actualizar datos");
            System.out.println("[4] Eliminar datos");
            System.out.println("[5] Salir");

            option = sc().nextInt();

            switch (option) {
                case 1:
                    sql.select();
                    break;
                case 2:
                    sql.insert();
                    break;
                case 3:
                    sql.update();
                    break;
                case 4:
                    sql.delete();
                    break;
                case 5:
                    break;
            }
        }
    }
    public static Scanner sc() {
        return new Scanner(System.in);
    }
}
