package login;

import java.sql.*;

public class DBOper {
    public final static String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
    public final static String USERNAME = "fasttrackit_dev";
    public final static String PASSWORD = "fasttrackit_dev";

    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int login(String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT user_id FROM usersaccounts where (username='"+user+ "' or email='"+ user + "') and password='"+pwd+"'");
            ResultSet rs = pSt.executeQuery();

            while (rs.next()) {
                found = rs.getInt("user_id");
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;
    }

    public String getHashPassword(String user) {

        String hashPwd=null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("SELECT password FROM usersaccounts where (username='"+user+ "' or email='"+ user + "')");
            ResultSet rs = pSt.executeQuery();

            if(rs.next()) {
                hashPwd = rs.getString("password");
            }

            rs.close();
            pSt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hashPwd;
    }

    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int register(String email, String user, String pwd) {

        int found = -1;
            try {
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                PreparedStatement pSt = conn.prepareStatement("INSERT INTO usersaccounts (email,username,password) VALUES (?,?,?)");
                pSt.setString(1, email);
                pSt.setString(2, user);
                pSt.setString(3, pwd);

                int rowsInserted = pSt.executeUpdate();

                pSt.close();

                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return found;
    }



    public static void main(String[] args) {

        DBOper d = new DBOper();
        int value = d.login("laura", "laura");
        System.out.println(value);
    }
}
