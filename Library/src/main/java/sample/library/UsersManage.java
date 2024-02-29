package sample.library;

import java.sql.*;

public class UsersManage {
    public void insert(String name, String surname, String userName, String password)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        try{
            connection = helper.getConnection();
            String sql = "insert into users (name,surname,username,password) values (?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,surname);
            statement.setString(3,userName);
            statement.setString(4,password);
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }

    public int loginControl(String userName, String password)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from users where username=? and password=?");
            statement.setString(1,userName);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return 0;
            } else {
                return 1;
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 1;
        }
    }
    public int usernameControl(String userName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select * from users where username=?");
            statement.setString(1,userName);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return 0;
            } else {
                return 1;
            }
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return 0;
        }
    }
    public String nameSurname(String userName)
    {
        Connection connection;
        DbConnection helper = new DbConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = helper.getConnection();
            statement = connection.prepareStatement("Select name,surname from users where username=?");
            statement.setString(1,userName);
            resultSet = statement.executeQuery();
            String nameAndSurname = "Hata1";
            while (resultSet.next()){
                nameAndSurname = resultSet.getString("name")+" "+resultSet.getString("surname");
            }
            return nameAndSurname;
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
            return "Hata";
        }
    }

    public int numberOfUser()
    {
        int toplam = 0;
        Connection connection;
        DbConnection helper = new DbConnection();
        Statement statement;
        ResultSet resultSet;
        try{
            connection = helper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select userId from users");
            while (resultSet.next()){
                toplam ++;
            }
            return toplam;
        }catch (SQLException exception){
            return toplam;
        }
    }

}
