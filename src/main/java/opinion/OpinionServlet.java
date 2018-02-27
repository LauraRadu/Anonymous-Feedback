package opinion;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/postIt")
public class OpinionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action");

        if (action != null && action.equals("read"))
            read(req, resp);
        else
        if (action != null && action.equals("write"))
            write(req, resp);
    }

    int iduser = -1;
    private void read(HttpServletRequest req, HttpServletResponse resp) {

        OpinionDB op = new OpinionDB();

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if (o != null) {
            iduser = (Integer) o;
        }

        JSONObject json = new JSONObject();
        try {
            json.put("posts", op.getListOfPosts());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());
        returnJsonResponse(resp, json.toString());
    }

    private void write(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter("namePerson");
        String opinion = req.getParameter("opinion");
        Opinion op = new Opinion(name, opinion);

        OpinionDB opDB = new OpinionDB();
        try {
            opDB.addOpinionDB(op);
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

}
