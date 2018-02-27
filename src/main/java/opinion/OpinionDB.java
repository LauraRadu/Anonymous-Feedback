package opinion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpinionDB {

    private List<Opinion> listOfPosts = new ArrayList<>();

    public void addOpinionDB(Opinion op) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO opinion (name, opinions) VALUES (?,?)");
        pSt.setString(1, op.getName());
        pSt.setString(2, op.getOpinion());

        int rowsInserted = pSt.executeUpdate();

        pSt.close();
        conn.close();

    }

    public List getListOfPosts() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT name,opinions,postdate  FROM opinion order by postdate desc");
        ResultSet rs = pSt.executeQuery();

        while (rs.next()) {
            Opinion op = new Opinion();
            op.setName(rs.getString("name"));
            op.setOpinion(rs.getString("opinions"));
            op.setDate(rs.getDate("postdate"));
            listOfPosts.add(op);
        }

        pSt.close();
        conn.close();

        return listOfPosts;
    }

    public static void main(String[] args) {
        try {

            OpinionDB o = new OpinionDB();
            o.getListOfPosts();
            for (int i = 0; i < o.listOfPosts.size(); i++) {
                System.out.println(o.listOfPosts.get(i).getDate());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
