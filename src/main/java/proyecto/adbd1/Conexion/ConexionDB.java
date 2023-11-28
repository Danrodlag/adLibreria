package proyecto.adbd1.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/usulibad";
    private static final String USER = "root";
    private static final String PASS = "root";




    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASS);
    }


}
