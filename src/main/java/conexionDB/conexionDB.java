
package conexionDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {
    
    private static final String url = "jdbc:mysql://localhost:3306/bomberos_canuelas";
    private static final String user = "root";
    private static final String password = "22775410";

    public static Connection obtenerConexion() throws SQLException {
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC", e);
        }
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void main(String[] args) {
        try {
            Connection con = conexionDB.obtenerConexion();
            if(con != null){
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
