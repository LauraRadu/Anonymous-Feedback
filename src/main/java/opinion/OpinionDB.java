package opinion;

import login.DBOper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpinionDB {

    private List<Opinion> listOfPosts = new ArrayList<>();

    public int addOpinionDB(Opinion op) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO opinion (name, opinions) VALUES (?,?) returning id_opinion");
        pSt.setString(1, op.getName());
        pSt.setString(2, op.getOpinion());

        ResultSet rs = pSt.executeQuery();
        rs.next();
        int id=rs.getInt(1);

        rs.close();
        pSt.close();
        conn.close();

        return id;
    }

    public List getListOfPosts(int iduser) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT o.name,o.opinions,o.postdate,o.hour  FROM opinion o " +
                "INNER JOIN intermediaropinion i on o.id_opinion=i.id_opinion AND id_user=?" +
                "order by postdate desc, hour desc");
        pSt.setInt(1, iduser);
        ResultSet rs = pSt.executeQuery();

        while (rs.next()) {
            Opinion op = new Opinion();
            op.setName(rs.getString("name"));
            op.setOpinion(rs.getString("opinions"));
            op.setDate(rs.getDate("postdate"));
            op.setHour(rs.getTime("hour"));
            listOfPosts.add(op);
        }

        pSt.close();
        conn.close();

        return listOfPosts;
    }

    public List getListOfPostsOneUser(String name) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT name,opinions,postdate,hour  FROM opinion WHERE name='?' order by postdate desc, hour desc");
        pSt.setString(1, name);
        ResultSet rs = pSt.executeQuery();

        while (rs.next()) {
            Opinion op = new Opinion();
            op.setName(rs.getString("name"));
            op.setOpinion(rs.getString("opinions"));
            op.setDate(rs.getDate("postdate"));
            op.setHour(rs.getTime("hour"));
            listOfPosts.add(op);
        }

        pSt.close();
        conn.close();

        return listOfPosts;
    }

    public void addIntermediarOpinion(long idOpinion, long[] s) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("INSERT INTO intermediaropinion (id_opinion, id_user) VALUES (?,?)");

        for (long id: s
             ) {
            pSt.setLong(1, idOpinion);
            pSt.setObject(2, id);

            pSt.addBatch();
        }
        pSt.executeBatch();

        pSt.close();
        conn.close();

    }

    public long[] getListId(String[] s) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);

        String string ="?";
        for (int i = 1; i < s.length ; i++) {
            string +=",?";
        }
        PreparedStatement pSt = conn.prepareStatement("SELECT user_id FROM usersaccounts WHERE username in (" + string+ ")");
        for (int i = 1; i <= s.length; i++) {
            pSt.setString(i, s[i-1]);
        }

        ResultSet rs = pSt.executeQuery();
        long[] arr = new long[s.length];
        int id=0;
        while(rs.next()) {
            arr[id] = rs.getLong("user_id");
            id++;
        }

        rs.close();
        pSt.close();
        conn.close();

        return arr;
    }

    public static void main(String[] args) {
        try {
           long[] lo = {3,10,11};
           long one = 63;
            OpinionDB o = new OpinionDB();
            o.addIntermediarOpinion(one,lo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
