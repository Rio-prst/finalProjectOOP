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
            String query = "SELECT p.*, GROUP_CONCAT(u.username SEPARATOR ', ') AS nama_anggota " +
                           "FROM projects p " +
                           "LEFT JOIN project_members pm ON p.id = pm.project_id " +
                           "LEFT JOIN users u ON pm.user_id = u.id " +
                           "GROUP BY p.id;";

            PreparedStatement statement = Config.Connector.Connect().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ModelProject proj = new ModelProject();
                proj.setId(resultSet.getInt("id"));
                proj.setNamaProyek(resultSet.getString("nama_proyek"));

                String anggota = resultSet.getString("nama_anggota");
                if (anggota == null || anggota.isEmpty()) {
                    anggota = "Belum ada tim";
                }
                proj.setDeskripsi(resultSet.getString("deskripsi") + " | (Tim: " + anggota + ")");

                listProject.add(proj);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Get All Project Error: " + e.getMessage());
        }
        return listProject;
    }

    @Override
    public void addMemberToProject(int projectId, int userId) {
        try {
            String query = "INSERT INTO project_members (project_id, user_id) VALUES (?, ?);";
            PreparedStatement statement = Config.Connector.Connect().prepareStatement(query);
            statement.setInt(1, projectId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Add Member Failed: " + e.getLocalizedMessage());
        }
    }
}