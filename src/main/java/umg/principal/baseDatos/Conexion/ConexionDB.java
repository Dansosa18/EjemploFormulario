package umg.principal.baseDatos.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private final String url = "jdbc:mysql://localhost:3306/db_telebot";
    private final String user = "root";
    private final String password = "DMBDAD";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MySQL", e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}
