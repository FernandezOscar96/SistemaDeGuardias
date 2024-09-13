package validate;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdministradorServlet")
public class AdministradorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario de login
        String password = request.getParameter("administrador");
        

        // Instanciar el DAO para validar las credenciales
        UserDAO userDao = new UserDAO();
        boolean usuarioValido = userDao.validarPermisos();

        // Redirigir según la validación
        if (usuarioValido) {
            response.sendRedirect("pages/gestionUsuarios.html");
        } else {
            response.sendRedirect("inicio.html");
        }
    }
}

