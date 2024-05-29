import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CategoryT extends DBconnector {
    String table = "Category";

    public void print() {
        Connection conn = this.connect();
        try {
            String query = "SELECT * FROM " + this.table + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String type= rs.getString("type");
                System.out.println("type: " + type);
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void insert() {
        Connection conn = this.connect();
        Scanner scanner = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter category type:");
            String type = scanner.nextLine();

            
            String query = "INSERT INTO " + this.table + " (type) VALUES ('" + type + "');";
            stmt.executeUpdate(query);

            System.out.println("Category '" + type + "' inserted successfully.");
            scanner.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void update() {
        Connection conn = this.connect();
        Scanner scanner = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter the current category type:");
            String currentType = scanner.nextLine();

            System.out.println("Enter the new category type:");
            String newType = scanner.nextLine();

           
            String query = "UPDATE " + this.table + " SET type = '" + newType + "' WHERE type= '" + currentType+ "';";
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Category type updated successfully.");
            } else {
                System.out.println("No category found with the type'" + currentType + "'.");
            }
            scanner.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void delete() {
        Connection conn = this.connect();
        Scanner scanner = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter the name of the category to delete:");
            String type= scanner.nextLine();

           
            String query = "DELETE FROM " + this.table + " WHERE type = '" + type + "';";
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Category '" + type + "' deleted successfully.");
            } else {
                System.out.println("No category found with the type '" + type + "'.");
            }
            scanner.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
}