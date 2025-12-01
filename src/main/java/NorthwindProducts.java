import java.sql.*;
import java.util.Scanner;

public class NorthwindProducts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("What do you want to do?");
            System.out.println(" 1) Display all products");
            System.out.println(" 2) Display all customers");
            System.out.println(" 0) Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        displayCustomers();
                        break;
                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error occurred:");
                System.err.println("Error Message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void displayProducts() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "yearup");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Northwind.Products");

        while (resultSet.next()) {
            int productId = resultSet.getInt("ProductID");
            String name = resultSet.getString("ProductName");
            double unitPrice = resultSet.getDouble("UnitPrice");
            int unitsInStock = resultSet.getInt("UnitsInStock");

            System.out.println(name);
            System.out.println("Product ID: " + productId);
            System.out.println("Unit Price: $" + unitPrice);
            System.out.println("Units in Stock: " + unitsInStock);
            System.out.println("-------------------");
        }

        connection.close();
    }

    private static void displayCustomers() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Northwind", "root", "yearup");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(
                "SELECT ContactName, CompanyName, City, Country, Phone " +
                        "FROM Northwind.Customers " +
                        "ORDER BY Country"
        );

        while (resultSet.next()) {
            String contactName = resultSet.getString("ContactName");
            String companyName = resultSet.getString("CompanyName");
            String city = resultSet.getString("City");
            String country = resultSet.getString("Country");
            String phone = resultSet.getString("Phone");

            System.out.println("Contact Name: " + contactName);
            System.out.println("Company Name: " + companyName);
            System.out.println("City: " + city);
            System.out.println("Country: " + country);
            System.out.println("Phone: " + phone);
            System.out.println("-------------------");
        }

        connection.close();
    }
}
