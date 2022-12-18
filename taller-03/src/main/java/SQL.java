import java.sql.*;
import java.util.Scanner;

public class SQL {
    // url del driver JDBC y la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/tienda";
    // usuario y contraseña de la base de datos
    static final String USER = "root";
    static final String PASS = "Gerlagos5#";
    static Connection conn = null;
    static Statement stmt = null;
    static PreparedStatement pstm = null;

    public static void connect() {
        try {
            //Paso 2: Cargar driver JDBC
            Class.forName(JDBC_DRIVER);

            //Paso 3: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException se) {
            //Errores de jdbc
            se.printStackTrace();
        } catch (Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        }
    }

    public static void select() {
        try {
            connect();

            //Paso 4: Ejecutar select
            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select producto.*, fabricante.* from producto inner join fabricante" +
                    " on producto.codigo_fabricante = fabricante.codigo");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                        rs.getDouble(3) + " " + rs.getInt(4) + " " + rs.getInt(5)
                        + " " + rs.getString(6));
            }

            //Paso 5: Limpiar
            rs.close();
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

    public static void insert() {
        try {
            connect();

            //Paso 4: Ejecutar INSERT
            System.out.println("Creando declaracion...");
            stmt = conn.createStatement();

            //Insertar datos de la tabla
            System.out.println("Ingrese código: ");
            int cod = sc().nextInt();
            System.out.println("Ingrese nombre: ");
            String name = sc().nextLine();
            System.out.println("Ingrese valor: ");
            double val = sc().nextDouble();
            System.out.println("Ingrese código del Fabricante: ");
            int codFab = sc().nextInt();

            //Crear la consulta INSERT
            String query = "INSERT INTO producto VALUES (" +
                    "'" + cod + "','" + name + "','" + val + "','" + codFab + " ' )";
            stmt.executeUpdate(query);

            System.out.println("Datos insertados...");

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

    public static void update() {
        try {
            connect();

            //Ingresar datos a actualizar
            System.out.println("Seleccione el código del producto a modificar: ");
            int cod = sc().nextInt();
            System.out.println("Ingrese valor: ");
            double val = sc().nextDouble();

            //Paso 4: Ejecutar UPDATE
            System.out.println("Creando declaracion...");

            //Crear la consulta UPDATE
            String query = "UPDATE producto set precio = ? where codigo = ?";
            pstm = conn.prepareStatement(query);

            pstm.setDouble(1, val);
            pstm.setInt(2, cod);

            pstm.executeUpdate();

            System.out.println("Datos modificados...");

            //Paso 5: Limpiar
            pstm.close();
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
                if (pstm != null)
                    pstm.close();
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

    public static void delete() {
        try {
            connect();

            //Paso 4: Ejecutar update
            System.out.println("Creando declaracion...");

            //Consultar datos a eliminar
            System.out.println("Seleccione el código del producto a eliminar: ");
            int cod = sc().nextInt();

            //Crear la consulta UPDATE
            String query = "DELETE FROM producto WHERE codigo = " + cod + "";
            pstm = conn.prepareStatement(query);

            pstm.executeUpdate();

            System.out.println("Datos eliminados...");

            //Paso 5: Limpiar
            pstm.close();
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
                if (pstm != null)
                    pstm.close();
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
