package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuarios;

public class RegistroUsuarioServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String pais = request.getParameter("pais");

        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        //convierte una cadena de texto a un objeto Date
        //proviene del paquete java.sql
        usuario.setFechaNacimiento(Date.valueOf(fechaNacimiento));
        usuario.setTelefono(apellido);
        usuario.setGuardiasRealizadas("0");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.insertarUsuario(usuario);
            response.sendRedirect("pages/registrarse.html?exito=true");
        } catch (Exception e) {
            response.sendRedirect("pages/registrarse.html?error=true");
        }
        
        /*
        boolean registroExitoso = usuarioDAO.insertarUsuario(usuario);
        if(registroExitoso){
            response.sendRedirect("pages/registrarse.html?exito=true");
        }
        else{
            response.sendRedirect("pages/registrarse.html?error=true");
        }        
        */
    }
}
