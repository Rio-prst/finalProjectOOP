package Model.Task;

import Config.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOTask implements InterfaceDAOTask {

    @Override
    public void insert(ModelTask task) {
        try {
            String query = "INSERT INTO tasks (project_id, user_id, judul, status, tipe_tugas, lampiran_spesifik) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, task.getProjectId());
            statement.setInt(2, task.getUserId());
            statement.setString(3, task.getJudul());
            statement.setString(4, task.getStatus());
            statement.setString(5, task.getTipeTugas());
            statement.setString(6, task.getLampiranSpesifik());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert Task Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void updateStatus(int id, String status) {
        try {
            String query = "UPDATE tasks SET status=? WHERE id=?;";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update Status Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM tasks WHERE id=?;";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Task Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelTask> getAll() {
        List<ModelTask> listTask = new ArrayList<>();
        try {
            String query = "SELECT * FROM tasks;";
            Statement statement = Connector.Connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                String tipe = resultSet.getString("tipe_tugas");
                ModelTask task;

                if (tipe.equals("DEVELOPMENT")) {
                    DevelopmentTask dev = new DevelopmentTask();
                    dev.setRepositoryUrl(resultSet.getString("lampiran_spesifik"));
                    task = dev;
                } else {
                    DesignTask des = new DesignTask();
                    des.setFigmaLink(resultSet.getString("lampiran_spesifik"));
                    task = des;
                }

                task.setId(resultSet.getInt("id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setJudul(resultSet.getString("judul"));
                task.setStatus(resultSet.getString("status"));

                listTask.add(task);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Get All Tasks Error: " + e.getLocalizedMessage());
        }
        return listTask;
    }
}