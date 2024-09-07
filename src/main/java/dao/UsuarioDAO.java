package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import conexionDB.conexionDB;
import model.Usuarios;

public class UsuarioDAO {
    
    public boolean insertarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO registro (num_serie, nombre, apellido, telefono, email, password, fechaNacimiento, guardiasRealizadas, administracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getNum_serie());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(6, usuario.getPassword());
            pstmt.setDate(7, usuario.getFechaNacimiento());
            pstmt.setString(8, usuario.getGuardiasRealizadas());
            pstmt.setString(9, usuario.getAdministracion());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
