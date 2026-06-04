package Model.User;

import Config.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOUser implements InterfaceDAOUser {

    Connection connection;

    // QUERY INSERT
    final String insert =
            "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

    // QUERY LOGIN
    final String login =
            "SELECT * FROM users WHERE username=? AND password=?";

    // CONSTRUCTOR
    public DAOUser() {
        connection = Connector.Connect();
    }

    // INSERT USE
    public void insert(ModelUser user) {

        try {

            PreparedStatement statement =
                    connection.prepareStatement(insert);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());

            statement.executeUpdate();

            System.out.println("Register Success!");

        } catch (Exception e) {

            System.out.println("Register Failed!");
            System.out.println(e.getMessage());
        }
    }

    // LOGIN CHECK
    @Override
    public ModelUser loginCheck(String username, String password) {

        ModelUser user = null;

        try {

            PreparedStatement statement =
                    connection.prepareStatement(login);

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            // CEK APAKAH USER ADA
            if (rs.next()) {

                user = new ModelUser();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }

        } catch (Exception e) {

            System.out.println("Login Error!");
            System.out.println(e.getMessage());
        }

        return user;
    }
}