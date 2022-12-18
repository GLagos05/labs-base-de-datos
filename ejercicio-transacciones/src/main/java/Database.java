
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static final String CLASE = "com.mysql.jdbc.Driver";

    private final String host;
    private final String usuario;
    private final String clave;
    private final String nombre;
    private final String url;

    private Connection link;
    private Statement statement;

    private String mensajeError;

    /**
     * Constructor
     *
     * Aqui almacenaremos los datos respectivos a la conexion con el DBMS:
     *
     * @param host Direccion del servidor DBMS
     * @param usuario Usuario del DBMS
     * @param clave Clave del usuario
     * @param nombre Nombre de la base de datos a la que se conectara
     *
     */
    public Database(String host, String usuario, String clave, String nombre) {

        /* Asignamos los atributos */
        this.host = host;
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;

        /* Asignamos el mensaje de error */
        this.mensajeError = "";

        /* Creamos la URL */
        this.url = "jdbc:mysql://" + this.host + "/" + this.nombre;

    }

    /**
     * Este metodo inicia la conexion a la base de datos
     *
     * @return boolean Resultado de la operacion TRUE si se conecto
     * exitosamente, FALSE en caso contrario.
     */
    public boolean conectar() {

        try {

            Class.forName(CLASE).newInstance();
            this.link = DriverManager.getConnection(this.url, this.usuario, this.clave);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            this.mensajeError = e.getMessage();
            return false;
        }

        return true;
    }

    /**
     * Se encarga de ejecutar una consulta, que no arrojara ningun resultado
     * (INSERT, UPDATE, DELETE).
     *
     * @param consulta Consulta a ejecutar
     * @return boolean Resultado de la operacion TRUE si se desconecto
     * exitosamente, FALSE en caso contrario.
     */
    public boolean consulta(String consulta) {

        int resultado;

        try {

            this.statement = this.link.createStatement();
            resultado = this.statement.executeUpdate(consulta);

        } catch (SQLException e) {
            this.mensajeError = e.getMessage();
            return false;
        }

        return (resultado > 0);
    }

    /**
     * Se encarga de ejecutar una consulta, que arrojara un resultado (SELECT)
     *
     * @param consulta Consulta a ejecutar
     * @return ArrayList Lista con los resultados obtenidos de la consulta
     */
    public ArrayList<Object> obtener(String consulta) {

        /* Resultados */
        ArrayList<Object> listado = new ArrayList<>();
        ResultSet resultado;

        /* Realizamos la consulta */
        try {

            this.statement = this.link.createStatement();
            resultado = this.statement.executeQuery(consulta);

        } catch (SQLException e) {
            this.mensajeError = e.getMessage();
            return null;
        }

        /* Guardamos el resultado */
        try {

            while (resultado.next()) {
                listado.add(resultado);
            }

        } catch (SQLException e) {
            this.mensajeError = e.getMessage();
            return null;
        }

        return listado;
    }

    /**
     * Realiza la desconexion del DBMS
     *
     * @return boolean Resultado de la operacion TRUE si se desconecto
     * exitosamente, FALSE en caso contrario.
     */
    public boolean desconectar() {

        try {

            this.link.close();

        } catch (SQLException e) {
            this.mensajeError = e.getMessage();
            return false;
        }

        return true;
    }

    public String getMensajeError() {
        return mensajeError;
    }

}