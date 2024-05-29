import java.util.Scanner;

public class Console {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        runMenu();
        scanner.close(); // Close the scanner after all menu operations are complete
    }

    public static void runMenu() {
        int choice;

        do {
            System.out.println("Please choose an option: ");
            System.out.println("1. Insert data");
            System.out.println("2. Retrieve data");
            System.out.println("3. Update data");
            System.out.println("4. Delete data");

            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertMenu();
                    break;
                case 2:
                    retrieveMenu();
                    break;
                case 3:
                    updateMenu();
                    break;
                case 4:
                    deleteMenu();
                    break;
              
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
                    break;
            }
        } while (choice != 0);
    }

    public static void insertMenu() {
        int choice;

        do {
            System.out.println("Please choose an option: ");
            System.out.println("1. Insert a new Category");
            System.out.println("2. Insert a new Product");
            System.out.println("3. Insert a new Material");
            System.out.println("4. Insert a new Sale");
            System.out.println("5. Insert a new Recipe");
            System.out.println("0. Go back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new CategoryT().insert();
                    break;
                case 2:
                    new ProductsT().insert();
                    break;
                case 3:
                    new MaterialsT().insert();
                    break;
                case 4:
                    new SaleT().insert();
                    break;
                case 5:
                    new RecipesT().insert();
                    break;
                case 0:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    break;
            }
        } while (choice != 0);
    }

    public static void retrieveMenu() {
        int choice;

        do {
            System.out.println("Please choose an option: ");
            System.out.println("1. Retrieve all Categories");
            System.out.println("2. Retrieve all Products");
            System.out.println("3. Retrieve all Materials");
            System.out.println("4. Retrieve Sales Report");
            System.out.println("5. Retrieve a Recipe");
            System.out.println("0. Go back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new CategoryT().print();
                    break;
                case 2:
                    new ProductsT().print();
                    break;
                case 3:
                    new MaterialsT().print();
                    break;
                case 4:
                    new SaleT().print();
                    break;
                case 5:
                    new RecipesT().print();
                    break;
                case 0:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    break;
            }
        } while (choice != 0);
    }

    public static void updateMenu() {
        int choice;

        do {
            System.out.println("Please choose an option: ");
            System.out.println("1. Update Category");
            System.out.println("2. Update Products");
            System.out.println("3. Update Materials");
            System.out.println("4. Update Sales");
            System.out.println("5. Update Recipe");
            System.out.println("0. Go back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new CategoryT().update();
                    break;
                case 2:
                    new ProductsT().update();
                    break;
                case 3:
                    new MaterialsT().update();
                    break;
                case 4:
                    new SaleT().update();
                    break;
                case 5:
                    new RecipesT().update();
                    break;
                case 0:
                    System.out.println("Going back...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    break;
            }
        } while (choice != 0);
    }

    public static void deleteMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {

            System.out.println("Please choose an option: ");
            System.out.println("1. Delete a  Category");
            System.out.println("2. Delete a Products");
            System.out.println("3. Delete a Materials");
            System.out.println("4. Delete a Sales");
            System.out.println("5. Delete a Recipe");

            System.out.println("0. Go back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    new CategoryT().delete();
                    
                    break;
                case 2:
                    new ProductsT().delete();
                    
                    break;
                case 3:
                    new MaterialsT().delete();
                    
                    break;
                case 4:
                    new SaleT().delete();
                    
                    break;
                case 5:
                    new RecipesT().delete();
                    
                    break;
                case 0:
                    runMenu();
                    System.out.println("Going back...");
                    break;
                default:

                    System.out.println("Invalid choice. Please enter a number between 0 and 5.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

}
