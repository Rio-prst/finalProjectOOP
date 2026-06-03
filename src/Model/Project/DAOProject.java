package Model.Project;

import Config.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProject implements InterfaceDAOProject {

    @Override
    public void insert(ModelProject project) {
        try {
            String query = "INSERT INTO projects (nama_proyek, deskripsi) VALUES (?, ?);";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, project.getNamaProyek());
            statement.setString(2, project.getDeskripsi());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert Project Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelProject project) {
        try {
            String query = "UPDATE projects SET nama_proyek=?, deskripsi=? WHERE id=?;";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, project.getNamaProyek());
            statement.setString(2, project.getDeskripsi());
            statement.setInt(3, project.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update Project Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM projects WHERE id=?;";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Project Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelProject> getAll() {
        List<ModelProject> listProject = new ArrayList<>();
        try {
            String query = "SELECT * FROM projects;";
            Statement statement = Connector.Connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ModelProject proj = new ModelProject();
                proj.setId(resultSet.getInt("id"));
                proj.setNamaProyek(resultSet.getString("nama_proyek"));
                proj.setDeskripsi(resultSet.getString("deskripsi"));

                listProject.add(proj);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Get All Projects Error: " + e.getLocalizedMessage());
        }
        return listProject;
    }
}