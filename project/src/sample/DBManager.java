package sample;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class DBManager extends BuyProducts {

    private Connection conn = null;

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");///
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/onlineshop?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void AddProducts(Products product) {
        String in = "INSERT INTO " + "`" + "onlineshop" + "`.`products` (`name`, `quality`, `date`, `price`, `count`) VALUES (?,?,?,?,?);";
        try {

            PreparedStatement prst = connect().prepareStatement(in);
            prst.setString(1, product.getName());
            prst.setString(2, product.getQuality());
            prst.setDate(3, (java.sql.Date) product.getDate());
            prst.setInt(4, product.getPrice());
            prst.setInt(5, product.getCount());

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String staticName;
    static String surname;

    public void addUser(User user) {
        staticName = user.getFirstName();
        surname = user.getLastName();
        String insert = "INSERT INTO " + "`" + "onlineshop" + "`.`" + Constants.USER_TABLE + "` (`" + Constants.USER_FIRSTNAME + "`, `" +
                Constants.USER_LASTNAME + "`, `" + Constants.USER_USERNAME + "`, `" + Constants.USER_PASSWORD + "`, `" +
                Constants.USER_LOCATION + "`, `" + Constants.USER_GENDER + "`) VALUES (?,?,?,?,?,?);";
        try {

            PreparedStatement prSt = connect().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.execute();
            prSt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Authorization(String login, String password) throws SQLException {  ////SUCCESS or FAIL
        Boolean isLogin = false;
        ResultSet resSet = null;
        PreparedStatement prSt = null;
        String select = "SELECT * FROM `" + "onlineshop" + "`.`" + Constants.USER_TABLE + "` WHERE (`" + Constants.USER_USERNAME + "` = ?) AND (`" + Constants.USER_PASSWORD + "` = ?);";
        try {
            prSt = connect().prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, password);

            resSet = prSt.executeQuery();
            if (resSet.next()) {
                isLogin = true;
                staticName = resSet.getString("firstname");
                surname = resSet.getString("lastname");
            } else isLogin = false;
        } catch (SQLException e) {
            isLogin = false;
        } finally {
            prSt.close();
            resSet.close();
        }
        return isLogin;
    }

    public void Edit(String name, String surname, String password) {
        int id = 0;
        ResultSet resSet = null;
        PreparedStatement prSt = null;
        String select = "SELECT * FROM `" + "onlineshop" + "`.`" + Constants.USER_TABLE + "` WHERE (`" + Constants.USER_FIRSTNAME + "` = ?);";
        try {
            prSt = connect().prepareStatement(select);
            prSt.setString(1, staticName);
            resSet = prSt.executeQuery();
            if (resSet.next()) {
                id = resSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {

            String iduser = String.valueOf(id);
            String update = "UPDATE `users` SET `firstname`=?,`lastname`=?,`password`=? WHERE id=" + iduser;
            PreparedStatement ps = connect().prepareStatement(update);
            // set the preparedstatement parameters
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, password);
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
            JFrame frame = new JFrame("Updated");
            JOptionPane.showMessageDialog(frame, "Updated");
        } catch (Exception se) {
            System.out.println(se);
        }
    }

    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> productsArrayList = new ArrayList<>();
        try {
            PreparedStatement statement = connect().prepareStatement("SELECT * FROM onlineshop.products");
            ResultSet resultSet = statement.executeQuery();/// Select

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String quality = resultSet.getString("quality");
                Date date = resultSet.getDate("date");
                Integer price = resultSet.getInt("price");
                Integer count = resultSet.getInt("count");

                productsArrayList.add(new Products(name, quality, date, price, count));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsArrayList;
    }


}
