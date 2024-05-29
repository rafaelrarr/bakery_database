import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductsT extends DBconnector {
    String table = "Products";

    public void print() {
        Connection conn = this.connect();
        try {
            String query = "SELECT * FROM " + this.table + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");

                System.out.println("Name: " + name);
                System.out.println("Type: " + type);
                System.out.println();
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

            String input;
            do {

                System.out.println("Insert name:");
                String name = scanner.nextLine();

                System.out.println("Insert type:");
                String type = scanner.nextLine();

                String query = "INSERT INTO " + this.table + " (name, type) VALUES ('" + name + "', '" + type + "');";
                stmt.executeUpdate(query);

                System.out.println("Do you want to insert another record? (yes/no)");
                input = scanner.nextLine().toLowerCase();
            } while (!input.equals("no"));

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void delete() {
        Connection conn = this.connect();
        Scanner scanner = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter the name of the product to delete:");
            String productName = scanner.nextLine();

            String query = "DELETE FROM " + this.table + " WHERE name = '" + productName + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + "products deleted.");

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }

    public void update() {
        Connection conn = this.connect();
        Scanner scanner = new Scanner(System.in);

        try {
            Statement stmt = conn.createStatement();

            System.out.println("Enter the name of the product you want to update: ");
            String productName = scanner.nextLine();

            System.out.println("Enter 'name' to update product name or 'type' to update product type:");
            String updateField = scanner.nextLine();

            System.out.println("Enter the new value:");
            String newValue = scanner.nextLine();

            String query;
            if (updateField.equalsIgnoreCase("name")) {
                query = "UPDATE " + this.table + " SET name = '" + newValue + "' WHERE name = '" + productName + "';";
            } else if (updateField.equalsIgnoreCase("type")) {
                query = "UPDATE " + this.table + " SET type = '" + newValue + "' WHERE name = '" + productName + "';";
            } else {
                System.out.println("Invalid update field.");
                return;
            }

            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " product updated.");

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
}
