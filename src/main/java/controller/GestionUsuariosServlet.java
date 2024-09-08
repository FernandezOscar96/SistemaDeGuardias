package controller;

import model.Usuarios;
import dao.UsuarioDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GestionPersonalServlet")
public class GestionUsuariosServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private ObjectMapper objectMapper;

    public GestionUsuariosServlet() {
        this.usuarioDAO = new UsuarioDAO();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Usuarios usuario = usuarioDAO.obtenerPorId(id);
            objectMapper.writeValue(response.getWriter(), usuario);
        } else {
            List<Usuarios> usuarios = usuarioDAO.obtenerTodos();
            objectMapper.writeValue(response.getWriter(), usuarios);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Usuarios usuario = objectMapper.readValue(request.getReader(), Usuarios.class);
        boolean exito = usuarioDAO.modificar(usuario);
        response.setContentType("application/json");
        // Establece la codificación de caracteres de la respuesta como UTF-8
        response.setCharacterEncoding("UTF-8");
        // Escribe la respuesta JSON
        response.getWriter().write("{\"exito\": " + exito + "}");

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // -- Obtiene el ID del parámetro de la URL 
        int id = Integer.parseInt(request.getParameter("id"));

        // -- Intenta eliminar el usuario
        boolean exito = usuarioDAO.eliminar(id);

        // -- Prepara y envía la respuesta
        // Establece el tipo de contenido de la respuesta como JSON
        response.setContentType("application/json");
        // Establece la codificación de caracteres de la respuesta como UTF-8
        response.setCharacterEncoding("UTF-8");
        // Escribe la respuesta JSON
        response.getWriter().write("{\"exito\": " + exito + "}");

    }
}
