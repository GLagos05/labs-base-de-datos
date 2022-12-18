import java.sql.*;
public class Act3 {
    // url del driver JDBC y la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Persona";
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

            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();

            // Se realiza la consulta
            // Queremos obtener el id del primer contacto con nombre Juan
            ResultSet rs = stmt.executeQuery("SELECT id FROM contacto WHERE nombre='Juan'");

            if(rs.next()) { //Si rs.next() devuelve true significa que al menos hay un registro
                int id = rs.getInt("id"); //se obtienen su id
                //se actualiza el registro
                stmt.executeUpdate("UPDATE contacto SET telefono='987654321' WHERE id=" + id);
            }

            //Paso 5: Limpiar
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
