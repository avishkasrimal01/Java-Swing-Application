package Service;

import Database.Database;
import Orders.Models.OrderM;
import java.sql.*;

public class OrderService {

    private Database singleConn;
    static Connection connection;
    PreparedStatement statement2;
    public OrderService()
    {
        singleConn=Database.getSingleInstance();
    }

    public boolean additem(OrderM order)
    {
        try
        {
            String query="insert into orders values('"+order.orderno+"','"+order.customername+"','"+order.category+"','"+order.price+"','"+order.vno+"')";
            boolean result=singleConn.ExecuteQuery(query);
            dbConnect();
            return result;
        }catch (Exception ex)
        {
            System.out.println("Cannot insert an Order."+ex);
            return false;
        }
    }

    public void dbConnect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "");
            System.out.println("connected to DB");

        } catch (SQLException ex) {
            ex.printStackTrace();  //used the " " because to covert the ex from SQLEXception to String
        }
    }


    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Employee details
    }




    public static void updateorder(String Id, String newName, String cat, String price, String vno) {



        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL update statement
            String updateQuery = "UPDATE orders SET name=?,category=?,price=?,vno=?,empno=? WHERE orderno=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set parameters
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, cat);
                preparedStatement.setString(3, price);
                preparedStatement.setString(4, vno);
                preparedStatement.setString(5, Id);

                // Execute update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    System.out.println("Order information updated successfully.");
                } else {
                    System.out.println("No Order found with the specified ID.");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(String Id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // SQL delete statement

            String deleteQuery = "DELETE FROM orders WHERE orderno=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // Set parameter
                preparedStatement.setString(1, Id);
                // Execute delete
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the delete was successful
                if (rowsAffected > 0) {
                    System.out.println("Order deleted successfully.");
                } else {
                    System.out.println("No Order found with the specified ID.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
