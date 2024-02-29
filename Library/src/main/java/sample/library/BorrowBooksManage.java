package sample.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class BorrowBooksManage {

    public void insert(String bookName,String authorName,int numberOfPage,String category,int yearOfPublication,String userName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        try{
            connection = helper.getConnection();
            String sql = "insert into borrowed_books (bookName,authorName,numberOfPage,category,yearOfPublication,username) values (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            statement.setString(2,authorName);
            statement.setInt(3,numberOfPage);
            statement.setString(4,category);
            statement.setInt(5,yearOfPublication);
            statement.setString(6,userName);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public void delete(String bookName, String username)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        try{
            connection= helper.getConnection();
            statement = connection.prepareStatement("delete from borrowed_books where bookName=? AND username=?");
            statement.setString(1,bookName.toUpperCase());
            statement.setString(2,username);
            statement.executeUpdate();
            int durum = 1;
            if(durum ==1)
            {
                String updateQuery = "UPDATE books SET status = true WHERE bookName = ? AND status = false";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1,bookName.toUpperCase());
                updateStatement.executeUpdate();
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public int borrowBookControl(String bookName,String username)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from borrowed_books where bookName=? and username=?");
            statement.setString(1,bookName);
            statement.setString(2,username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return 0;
            }
            else {
                return 1;
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 1;
        }
    }

    public String bookNameControl(String bookName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from borrowed_books where bookName=?");
            statement.setString(1,bookName.toUpperCase());
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                String borrowBook = resultSet.getString("bookName");
                return borrowBook;
            }
            else{
                return "YOK";
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return "YOK";
        }
    }

    public List<String> userNameControl(String userName) {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        List<String> borrowedBooks = new ArrayList<>(); // Kitap isimlerini tutacak liste

        try {
            connection = helper.getConnection();
            statement = connection.prepareStatement("SELECT * FROM borrowed_books WHERE username=?");
            statement.setString(1, userName);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String borrowBook = resultSet.getString("bookName");
                borrowedBooks.add(borrowBook);
            }

        } catch (SQLException exception) {
            helper.showErrorMessage(exception);
        }

        if (borrowedBooks.isEmpty()) {
            borrowedBooks.add("YOK");
        }

        return borrowedBooks;
    }


}
