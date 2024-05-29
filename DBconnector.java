import java.sql.*;

public class DBconnector {

    public Connection connect() {

        Connection conn = null;

        try {
            conn = getConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://cs.neiu.edu:3306/SP24CS3151_rromero8?serverTimezone=UTC&";
        url += "user=SP24CS3151_rromero8&password=rromero8707297";
        return DriverManager.getConnection(url);
    }

}
