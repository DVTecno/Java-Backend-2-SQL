package estancias.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO {
protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String DATABASE = "estancias_exterior";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public DAO() {
    }

    protected void conectarBase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String urlBaseDatos = "jdbc:mysql://localhost:3306/tienda?useSSl=false";
            this.conexion = DriverManager.getConnection(urlBaseDatos, "root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        }
    }

    protected void desconectarBase() throws Exception {
        try {
            if (this.resultado != null) {
                this.resultado.close();
            }

            if (this.sentencia != null) {
                this.sentencia.close();
            }

            if (this.conexion != null) {
                this.conexion.close();
            }

        } catch (Exception e) {
            throw e;
        }
    }

    protected void insertarModificarEliminar(String sql) throws Exception {
        try {
            this.conectarBase();
            this.sentencia = this.conexion.createStatement();
            this.sentencia.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.desconectarBase();
        }

    }

    protected void consultarBase(String sql) throws Exception {
        try {
            this.conectarBase();
            this.sentencia = this.conexion.createStatement();
            this.resultado = this.sentencia.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
}