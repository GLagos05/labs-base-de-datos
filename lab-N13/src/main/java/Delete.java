//Paso 1. Importar los paquetes requeridos
import java.sql.*;
import java.util.Scanner;

public class Delete {
    // url del driver JDBC y la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Comida";
    // usuario y contraseña de la base de datos
    static final String USER = "root";
    static final String PASS = "Gerlagos5#";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            //Paso 2: Cargar driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            //Paso 3: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            //Paso 4: Ejecutar insert
            System.out.println("Creando declaracion...");

            //Consultar datos a eliminar
            System.out.println("Seleccione el código del producto a eliminar: ");
            int cod = sc().nextInt();

            //Crear la consulta UPDATE
            String query = "DELETE FROM Pizza WHERE codigoPizza = " + cod + "";
            pstm = conn.prepareStatement(query);

            pstm.executeUpdate();

            System.out.println("Datos eliminados...");
            //Paso 5: Limpiar
            pstm.close();
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
                if(pstm!=null)
                    pstm.close();
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
