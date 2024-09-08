package controller;

import model.Usuarios;
import dao.UsuarioDAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UsuarioDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/GestionUsuariosServlet")
public class GestionPersonalServlet {
    private UsuarioDAO UsuarioDAO;
    private ObjectMapper objectMapper;
    
    public GestionPersonalServlet() {
        this.UsuarioDAO = new UsuarioDAO();
        this.objectMapper = new ObjectMapper();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            // Si se proporciona un ID, obtener un usuario específico
            int id = Integer.parseInt(idParam);
            Usuarios usuario = UsuarioDAO.obtenerPorId(id);
            objectMapper.writeValue(response.getWriter(), usuario);
        } else {
            // Si no se proporciona ID, obtener todos los usuarios
            List<Usuarios> usuarios = UsuarioDAO.obtenerTodos();
            objectMapper.writeValue(response.getWriter(), usuarios);
            //convierte la lista de usuarios en JSON y la envía como respuesta HTTP
        }
    }
    
}
