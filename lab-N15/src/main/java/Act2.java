import java.sql.*;
import java.util.Scanner;

public class Act2 {
    // url del driver JDBC y la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Persona";
    // usuario y contraseña de la base de datos
    static final String USER = "root";
    static final String PASS = "Gerlagos5#";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //Paso 2: Cargar driver JDBC
            Class.forName("com.mysql.jdbc.Driver");

            //Paso 3: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Paso 4: Ejecutar insert
            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();

            //Los datos que vamos a insertar los tenemos en 3 ARREGLOS
            String nombres[] = {"Juan", "Pedro", "Antonio"};
            String apellidos[] = {"Gomez", "Lopez", "Alvarez"};
            String telefonos[] = {"987452154", "989654125", "985321478"};

            //se insertan datos en la tabla
            for (int i = 0; i < nombres.length; i++) {
                stmt.executeUpdate("INSERT INTO contacto (nombre, apellido, telefono) VALUES ('" + nombres[i] + "','" + apellidos[i] + "','" + telefonos[i] + "' )");
            }
            // Se realiza una consulta sobre la tabla contacto.
            ResultSet rs = stmt.executeQuery("select * from contacto");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                        rs.getInt(3));
            }


            //Paso 5: Limpiar
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Errores de jdbc
            se.printStackTrace();
        } catch (Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            //bloque usado para cerrar recursos
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nada que hacer
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Adios!");
    }

    public static Scanner sc() {
        return new Scanner(System.in);
    }

}
