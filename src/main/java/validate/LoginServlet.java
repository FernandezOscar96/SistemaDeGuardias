
package validate;


import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario de login
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        

        // Instanciar el DAO para validar las credenciales
        UserDAO userDao = new UserDAO();
        userDao.actualizarSesion(email, true); // Establecer 'sesion' en 1
        boolean usuarioValido = userDao.validarUsuario(email, password);

        // Redirigir según la validación
        if (usuarioValido) {
            request.getSession().setAttribute("userEmail", email);
            response.sendRedirect("pages/inicio.html");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
