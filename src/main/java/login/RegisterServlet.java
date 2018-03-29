package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String name = request.getParameter("u");
        String password = request.getParameter("p");
        String repeatPassword = request.getParameter("rp");

        DBOper d = new DBOper();

        if(password.equals(repeatPassword)) {
            String p = Password.getSaltedHash(password);
            d.register(email, name, p);
            int value = d.login(name, p);

            if (value != -1) { // user logat
                HttpSession session = request.getSession();
                session.setAttribute("userid", value);
                session.setAttribute("username", name);
                System.out.println("LoginServlet: bravoooo  ");

                response.sendRedirect("opinions.jsp");
            } else {
                System.out.println("LoginServlet:registration not done correctly ");
                String back = "/register.jsp";
                HttpSession session = request.getSession();
                session.removeAttribute("userid");
                session.setAttribute("flagRegister", true);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
                dispatcher.forward(request, response);
            }
        }
    }
}
