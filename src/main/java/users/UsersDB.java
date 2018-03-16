package users;

import login.DBOper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDB {

    public List getAllUsers () {
        List<String> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DBOper.URL, DBOper.USERNAME, DBOper.PASSWORD);


            PreparedStatement pSt = conn.prepareStatement("SELECT user_id, username FROM usersaccounts");
            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("username").trim());
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        UsersDB o = new UsersDB();
        List<String> l = o.getAllUsers();
        for (String s : l
             ) {
            System.out.println(s);
        }
    }
}
