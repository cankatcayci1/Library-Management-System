package sample.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BooksManage {

    public ObservableList<Books> getDataBooks() throws SQLException
    {
        DbConnection helper = new DbConnection();
        Connection connection = helper.getConnection();
        ObservableList<Books> list = FXCollections.observableArrayList();
        PreparedStatement ps = connection.prepareStatement("select bookName,authorName,numberOfPage,category,yearOfPublication,status from books");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            list.add(new Books(
                    rs.getString("bookName"),
                    rs.getString("authorName"),
                    rs.getInt("numberOfPage"),
                    rs.getString("category"),
                    rs.getInt("yearOfPublication"),
                    rs.getBoolean("status")
            ));
        }
        return list;
    }

    public void insertBook(String bookName,String authorName,int numberOfPage, String category,int yearOfPublication )
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        try{
            connection = helper.getConnection();
            String sql = "insert into books (bookName,authorName,numberOfPage,category,yearOfPublication) values (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            statement.setString(2,authorName);
            statement.setInt(3,numberOfPage);
            statement.setString(4,category);
            statement.setInt(5,yearOfPublication);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public int bookControl(String borrowBookName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from books where bookName=?");
            statement.setString(1,borrowBookName);
            resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                return 0;
            }
            else
            {
                return 1;
            }

        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 1;
        }
    }

    public void booksData(String bookName, String userName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.prepareStatement("select bookName,authorName,numberOfPage,category,yearOfPublication,status from books where bookName=? AND status=true");
            statement.setString(1,bookName.toUpperCase());
            resultSet = statement.executeQuery();
            BorrowBooksManage borrowBookManager = new BorrowBooksManage();
            int durum=0;
            while (resultSet.next()){
                borrowBookManager.insert(resultSet.getString("bookName"),resultSet.getString("authorName"),resultSet.getInt("numberOfPage"),resultSet.getString("category"),resultSet.getInt("yearOfPublication"),userName);
                durum = 1;
            }
            if(durum==1)
            {
                String updateQuery = "UPDATE books SET status = false WHERE bookName=? AND status = true";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1,bookName.toUpperCase());
                updateStatement.executeUpdate();
            }

        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }



    public int numberOfBook()
    {
        int toplam = 0;
        Connection connection;
        DbConnection helper = new DbConnection();
        Statement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select bookId from books");
            while (resultSet.next()){
                toplam ++;
            }
            return toplam;
        }catch (SQLException exception){
            return toplam;
        }
    }


}
