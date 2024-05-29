import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SaleT extends DBconnector {
    String table = "Sale";

    public void print() {
        Connection conn = this.connect();
        try {
            String query = "SELECT * FROM " + this.table + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String productName = getNameById("Products", productId);
                String type = rs.getString("type");
                float priceSale = rs.getFloat("priceSale");
                float quantitySold = rs.getFloat("quantity_sold");
                String date = rs.getString("date");

                System.out.println("Name: " + productName);
                System.out.println("Type: " + type);
                System.out.println("Price Sale: " + priceSale);
                System.out.println("Quantity Sold: " + quantitySold);
                System.out.println("Date: " + date);
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
                System.out.println("Enter product name:");
                String productName = scanner.nextLine();
                int productId = getIdByName("Products", productName);

                System.out.println("Enter product type:");
                String type = scanner.nextLine();

                System.out.println("Enter price sale:");
                float priceSale = scanner.nextFloat();
                scanner.nextLine();

                // Prompt user for quantity sold
                System.out.println("Enter quantity sold:");
                float quantitySold = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Is the date today? (yes/no)");
                String todayOption = scanner.nextLine().toLowerCase();
                String date;
                if (todayOption.equals("yes")) {

                    date = "CURDATE()";
                } else {

                    System.out.println("Enter the date with the next format(YYYY-MM-DD):");
                    date = "'" + scanner.nextLine() + "'";
                }

                String query = "INSERT INTO " + this.table + " (productId, type, priceSale, quantity_sold, date) " +
                        "VALUES (" + productId + ", '" + type + "', " + priceSale + ", " + quantitySold + ", " + date
                        + ");";
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

            System.out.println("Enter date (YYYY-MM-DD):");
            String date = scanner.nextLine();

            String query = "DELETE FROM " + this.table + " WHERE productId IN (SELECT id FROM Products WHERE name = '"
                    + productName + "') AND date = '" + date + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " rows deleted.");

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

            System.out.println("Enter date (YYYY-MM-DD):");
            String date = scanner.nextLine();

            System.out.println("Enter the new quantity sold:");
            float newQuantitySold = scanner.nextFloat();
            scanner.nextLine();

            String query = "UPDATE " + this.table + " SET quantity_sold = " + newQuantitySold
                    + " WHERE productId IN (SELECT id FROM Products WHERE name = '" + productName + "') AND date = '"
                    + date + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " sales updated.");

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

    }

    public String getNameById(String tableName, int id) {
        Connection conn = this.connect();
        String name = "";

        try {
            String query = "SELECT name FROM " + tableName + " WHERE id = " + id + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString("name");
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

        return name;
    }

    public int getIdByName(String tableName, String name) {
        Connection conn = this.connect();
        int id = -1;

        try {
            String query = "SELECT id FROM " + tableName + " WHERE name = '" + name + "';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                id = rs.getInt("id");
            }
            stmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }

        return id;
    }
}