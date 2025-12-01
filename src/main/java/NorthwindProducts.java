import java.sql.*;

public class NorthwindProducts {
    public static void main(String[] args) {
        try {
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

        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
