package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.parser.DaoParser;
import com.codecool.web.model.Address;
import com.codecool.web.model.Role;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT user_id, user_name, email, role, country, zip_code, city, street FROM users " +
            "WHERE user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchUser(resultSet);
        }
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT user_id, user_name, email, role, country, zip_code, city, street FROM users " +
            "WHERE email = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchUser(resultSet);
        }
    }

    @Override
    public void registerUser(String name, String password, String email) throws SQLException {
        if (isEmailUsed(email)) {
            throw new SQLException("Email already used!");
        }
        String sql = "INSERT INTO users (user_name, password, email) " +
            "VALUES(?, crypt(?, gen_salt('bf', 9)), ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);

            executeInsert(statement);
        }
    }

    @Override
    public boolean isRegistered(String email, String password) throws SQLException {
        String sql = "SELECT email FROM users " +
            "WHERE email = ? AND password = crypt(?, password);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return true;
            } else if (isEmailUsed(email)) {
                throw new SQLException("Wrong password!");
            } else {
                throw new SQLException("Email not found! Register first!");
            }
        }
    }

    private boolean isEmailUsed(String email) throws SQLException {
        String sql = "SELECT email FROM users " +
            "WHERE email = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        }
    }

    @Override
    public void updateProfile(String name, String email, Address address) throws SQLException {
        String sql = "UPDATE users SET (user_name, country, zip_code, city, street) = (?, ?, ?, ?, ?) " +
            "WHERE email = ?; " ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, address.getCountry());
            statement.setInt(3, Integer.valueOf(address.getZipCode()));
            statement.setString(4, address.getCity());
            statement.setString(5, address.getStreet());
            statement.setString(6, email);

            statement.executeUpdate();
        }
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("user_id");
        String name = resultSet.getString("user_name");
        String email = resultSet.getString("email");
        Role role = DaoParser.parseToRole(resultSet.getString("role"));

        String country = resultSet.getString("country");
        String zip_code = resultSet.getString("zip_code");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");

        return new User(id, name, email, role, new Address(country, zip_code, city, street));
    }
}
