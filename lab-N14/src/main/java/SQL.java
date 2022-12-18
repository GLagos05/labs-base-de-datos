import java.sql.*;
import java.util.Scanner;

public class SQL {
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

            System.out.println("=========Menú=========");
            System.out.println("[1] Pizza Americana");
            System.out.println("[2] Pizza Vegetriana");
            System.out.println("[3] Pizza Doble Queso");

            System.out.println("Seleccione el codigo de la pizza a modificar: ");
            int cod = sc().nextInt();
            System.out.println("Ingrese nombre: ");
            String name = sc().nextLine();
            System.out.println("Ingrese valor: ");
            int val = sc().nextInt();
            String query = "UPDATE Pizza set valorPizza = ?, nombrePizza = ? where codigoPizza = ?";

            System.out.println("Creando declaracion...");
            pstm = conn.prepareStatement(query);

            pstm.setInt(1,val);
            pstm.setString(2,name);
            pstm.setInt(3,cod);

/*
            System.out.println("Ingrese código: ");
            int cod = sc().nextInt();
            System.out.println("Ingrese valor: ");
            int val = sc().nextInt();
            System.out.println("Ingrese nombre: ");
            String name = sc().next();

            String sql;

            sql = "INSERT INTO Pizza " +"VALUES ("+cod+", "+val+", '"+name+"')";

 */
            pstm.executeUpdate();
/*
            sql = "INSERT INTO Pizza " +
                    "VALUES (4, 4000, 'Queso y pepperoni')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Pizza " +
                    "VALUES (5, 5000, 'Hawaiana')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO Pizza " +
                    "VALUES(6, 6000, 'Pollo')";
            stmt.executeUpdate(sql);*/
            System.out.println("Datos modificados...");
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

    public static Scanner sc(){
        return new Scanner(System.in);
    }

}
