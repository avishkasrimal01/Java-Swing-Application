package Database;

import java.sql.*;
import java.sql.PreparedStatement;

public class Database {
    private final String URL="jdbc:mysql://localhost:3306/oop";
    private final String UName="root";
    private final String Password="";
    private static Database instance;
    private Connection con;
    PreparedStatement statement;

    private Database()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(URL,UName,Password);
            System.out.println("Database Connection Sucess");
        }catch (ClassNotFoundException ex)
        {
            System.out.println("Driver Class Error "+ex.getMessage());
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
        }
    }
    public static Database getSingleInstance()//Singleton Design Pattern
    {
        try {
            if (instance == null) {
                instance = new Database();
            } else if (instance.con.isClosed()) {
                instance = new Database();
            }else{
                return instance;
            }
            return instance;
        }catch (SQLException ex)
        {
            System.out.println("Database Connection Error "+ex.getMessage());
            return null;
        }
    }
    public boolean ExecuteQuery(String sqlQ)
    {
        try
        {
            Statement st=con.createStatement();
            int result=st.executeUpdate(sqlQ);
            return result>0;
        }catch (SQLException ex)
        {
            System.out.println("SQL Error "+ex.getMessage());
            return false;
        }
    }


}
