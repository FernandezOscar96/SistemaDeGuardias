package dao;

import conexionDB.conexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
     public boolean validarUsuario(String email, String contrasena) {
        boolean validar = false;
        String sql = "SELECT * FROM registro WHERE email = ? AND password = ?";
        
        try {
            //obtenemos la conexion
            Connection conexion = conexionDB.obtenerConexion();
            //preparar la consulta
            PreparedStatement consulta = conexion.prepareStatement(sql);
            //argumentos
            consulta.setString(1, email);
            consulta.setString(2, contrasena);
            //ejecutar la consulta
            ResultSet resultado = consulta.executeQuery();
            
            validar = resultado.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return validar;
    }
     
     public boolean validarPermisos(){
         return true;
     }

    public void actualizarSesion(String email, boolean b) {

        System.out.println(email);
        System.out.println(b);
         String sql = "UPDATE registro SET sesion = ? WHERE email = ?";

         try {
             //obtenemos la conexion
             Connection conexion = conexionDB.obtenerConexion();
             //preparar la consulta
             PreparedStatement consulta = conexion.prepareStatement(sql);
             //argumentos
             consulta.setInt(1, b ? 1 : 0); // Establece 1 si estado es true, 0 si es false
             consulta.setString(2, email);
             consulta.executeUpdate();
         } catch (SQLException e){
             e.printStackTrace(); // Manejo de errores
         }
    }
}
