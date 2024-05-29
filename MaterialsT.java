import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MaterialsT extends DBconnector {
    String table = "Materials";

    public void print() {
        Connection conn = this.connect();
        try {
            String query = "SELECT * FROM " + this.table + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                float weightGram = rs.getFloat("weight_gram");
                float price = rs.getFloat("price");

                System.out.println("Name: " + name + " Weight (grams): " + weightGram + "Price: " + price + "$");

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

                System.out.println("Insert weight in grams:");
                float weightGram = scanner.nextFloat();
                scanner.nextLine();

                System.out.println("Insert price:");
                float price = scanner.nextFloat();
                scanner.nextLine();

                String query = "INSERT INTO " + this.table + " (name, weight_gram, price) VALUES ('" + name + "', "
                        + weightGram + ", " + price + ");";
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

            System.out.println("Enter the name of the material you want to delete: ");
            String condition = scanner.nextLine();

            String query = "DELETE FROM " + this.table + " WHERE name= '" + condition + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + "  material deleted.");

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

            System.out.println("Enter the name of the material you want to update: ");
            String materialName = scanner.nextLine();

            System.out.println("Enter the new price: ");
            float newPrice = scanner.nextFloat();
            scanner.nextLine();

            String query = "UPDATE " + this.table + " SET price = " + newPrice + " WHERE name = '" + materialName
                    + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " material/s updated");

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
        }
    }
}
