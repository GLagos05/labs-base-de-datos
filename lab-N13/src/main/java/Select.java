//Paso 1. Importar los paquetes requeridos
import java.sql.*;
public class Select {
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
            //Paso 4: Ejecutar una consulta
            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Pizza";
            ResultSet rs = stmt.executeQuery(sql);
            //Paso 5: Extraer datos del resultado
            while(rs.next()){
                //Recuperar por el nombre de la columna
                int codigoPizza = rs.getInt("codigoPizza");
                int valorPizza = rs.getInt("valorPizza");
                String nombrePizza = rs.getString("nombrePizza");
                //Mostar resultados
                System.out.print("Codigo: " + codigoPizza);
                System.out.print(", Valor: " + valorPizza);
                System.out.println(", Nombre: " + nombrePizza);
            }
            //Paso 6: Limpiar
            rs.close();
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
}
