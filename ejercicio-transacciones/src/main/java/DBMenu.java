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
            System.out.println("[1] Insertar datos");
            System.out.println("[2] Actualizar datos");
            System.out.println("[3] Eliminar datos");
            System.out.println("[4] Mostrar datos");
            System.out.println("[5] Salir");

            option = sc().nextInt();

            switch (option) {
                case 1:
                    sql.insert();
                    break;
                case 2:
                    sql.update();
                    break;
                case 3:
                    sql.delete();
                    break;
                case 4:
                    sql.select();
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
