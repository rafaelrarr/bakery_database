import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RecipesT extends DBconnector {
    String table = "Recipes";

    public void print() {
        Connection conn = this.connect();
        try {
            String query = "SELECT * FROM " + this.table + ";";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String recipeName = rs.getString("name");
                int productId = rs.getInt("productId");
                int materialId = rs.getInt("materialId");
                float materialQuantityGram = rs.getFloat("material_quantity_gram");
                int unit = rs.getInt("unit");

                String productName = getNameById("Products", productId);
                String materialName = getNameById("Materials", materialId);

                System.out.println("Recipe Name: " + recipeName);
                System.out.println("Producing Product: " + productName);
                System.out.println("Material: " + materialName);
                System.out.println("Material Quantity (grams): " + materialQuantityGram);
                System.out.println("Amount Produced: " + unit);
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

            System.out.println("Enter recipe name:");
            String recipeName = scanner.nextLine();

            System.out.println("Enter the name of the product this recipe produces:");
            String productName = scanner.nextLine();
            int productId = getIdByName("Products", productName);

            System.out.println("Enter amount produced:");
            int unit = scanner.nextInt();
            scanner.nextLine();

            String input;
            do {

                System.out.println("Enter material name (or press '0' to finish):");
                String materialName = scanner.nextLine();
                if (materialName.equals("0"))
                    break;
                int materialId = getIdByName("Materials", materialName);

                System.out.println("Enter quantity of material '" + materialName + "' in grams:");
                float materialQuantityGram = scanner.nextFloat();
                scanner.nextLine();

                String query = "INSERT INTO " + this.table
                        + " (productId, materialId, unit, material_quantity_gram, name) " +
                        "VALUES (" + productId + ", " + materialId + ", " + unit + ", " + materialQuantityGram + ", '"
                        + recipeName + "');";
                stmt.executeUpdate(query);
            } while (true);

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

            System.out.println("Enter the name of the recipe you want to update:");
            String recipeName = scanner.nextLine();

            System.out.println("Enter the new recipe name:");
            String newRecipeName = scanner.nextLine();

            String query = "UPDATE " + this.table + " SET name = '" + newRecipeName + "' WHERE name = '" + recipeName
                    + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " rows updated.");

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

            System.out.println("Enter the name of the recipe you want to delete:");
            String recipeName = scanner.nextLine();

            String query = "DELETE FROM " + this.table + " WHERE name = '" + recipeName + "';";
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " rows deleted.");

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

