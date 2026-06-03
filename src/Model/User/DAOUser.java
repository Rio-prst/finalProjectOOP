package Model.User;

import Config.Connector;
import java.sql.*;

public class DAOUser implements InterfaceDAOUser {
    @Override
    public ModelUser loginCheck(String username, String password) {
        ModelUser user = null;
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?;";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new ModelUser();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getString("role"));
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Login Query Error: " + e.getLocalizedMessage());
        }
        return user;
    }
}