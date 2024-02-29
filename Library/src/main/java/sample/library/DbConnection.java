package sample.library;
import java.sql.*;

public class DbConnection {
    public DbConnection(){
    }

    public Connection getConnection() throws SQLException
    {
        final String userName = "root";
        final String password = "1380";
        final String dbUrl = "jdbc:mysql://localhost:3307/library";
        return DriverManager.getConnection(dbUrl,userName,password);
    }

    public void showErrorMessage(SQLException exception)
    {
        System.out.println("Error : "+exception.getMessage());
        System.out.println("Error code : "+exception.getErrorCode());
    }

}



