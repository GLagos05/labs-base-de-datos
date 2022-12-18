//Paso 1. Importar los paquetes requeridos
import java.sql.*;
import java.util.Scanner;

public class Insert {
    // url del driver JDBC y la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Comida";
    // usuario y contraseña de la base de datos
    static final String USER = "root";
    static final String PASS = "Gerlagos5#";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //Paso 2: Cargar driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Paso 3: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Paso 4: Ejecutar insert
            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();

            //Insertar datos de la tabla
            System.out.println("Ingrese código: ");
            int codigoPizza = sc().nextInt();
            System.out.println("Ingrese nombre: ");
            String nombrePizza = sc().nextLine();
            System.out.println("Ingrese valor: ");
            double valorPizza = sc().nextDouble();

            //Crear la consulta INSERT
            String query = "INSERT INTO Pizza VALUES (" +
                    "'" + codigoPizza + "','" + valorPizza + "','" + nombrePizza + " ' )";
            stmt.executeUpdate(query);

            System.out.println("Datos insertados...");
            //Paso 5: Limpiar
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Errores de jdbc
            se.printStackTrace();
        }catch(Exception e){
            //Errores de Class.forName
            e.printStackTrace();
        }finally{
            //bloque usado para cerrar recursos
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nada que hacer
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Adios!");
    }

    public static Scanner sc() {
        return new Scanner(System.in);
    }
}
