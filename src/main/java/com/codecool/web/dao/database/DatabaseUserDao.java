package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.parser.DaoParser;
import com.codecool.web.dao.parser.DaoParserException;
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
    public User findById(int id) throws SQLException, DaoParserException {
        String sql = "SELECT user_id, user_name, email, role, country, zip_code, city, street FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchUser(resultSet);
        }
    }

    @Override
    public void registerUser(String name, String email, Address address) throws SQLException {

    }

    @Override
    public boolean isEmailAvailable(String email) throws SQLException {
        return false;
    }

    @Override
    public boolean isRegistered(String email, String password) throws SQLException {
        return false;
    }

    private User fetchUser(ResultSet resultSet) throws SQLException, DaoParserException {
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
