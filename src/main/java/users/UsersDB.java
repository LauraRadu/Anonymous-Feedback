package users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDB {
    final static String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
    final static String USERNAME = "fasttrackit_dev";
    final static String PASSWORD = "fasttrackit_dev";

    public List getAllUsers () {
        List<String> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT username FROM usersaccounts");
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
