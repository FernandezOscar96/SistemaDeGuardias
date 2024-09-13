package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import conexionDB.conexionDB;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Usuarios;

public class UsuarioDAO {

    public boolean insertarUsuario(Usuarios usuario) {
        String sql = "INSERT INTO registro (num_serie, nombre, apellido, telefono, email, password, fechaNacimiento, guardiasRealizadas, administracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNum_serie());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getEmail());
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

    private Usuarios extraerUsuarioDeResultSet(ResultSet rs) throws Exception {
        Usuarios usuario = new Usuarios();
        usuario.setId(rs.getInt("id"));
        usuario.setNum_serie(rs.getString("num_serie"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        usuario.setGuardiasRealizadas(rs.getString("guardiasRealizadas"));
        return usuario;
    }

    public List<Usuarios> obtenerTodos() {

        List<Usuarios> usuarios = new ArrayList<>();
        String query = "SELECT * FROM registro";

        try (Connection conn = conexionDB.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Usuarios usuario = extraerUsuarioDeResultSet(rs);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuarios obtenerPorId(int id) {
        String query = "SELECT * FROM registro WHERE id = ?";
        try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extraerUsuarioDeResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(Usuarios usuario) {
        String query = "UPDATE registro SET nombre = ?, apellido = ?, email = ?, password = ?, fechaNacimiento = ?, pais = ? WHERE id = ?";
        try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, usuario.getNum_serie());
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getPassword());
            pstmt.setDate(5, usuario.getFechaNacimiento());
            pstmt.setString(6, usuario.getGuardiasRealizadas());
            pstmt.setInt(7, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String query = "DELETE FROM registro WHERE id = ?";
        try (Connection conn = conexionDB.obtenerConexion(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
