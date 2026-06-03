package Model.Task;

import java.util.List;

public interface InterfaceDAOTask {
    public void insert(ModelTask task);
    public void updateStatus(int id, String status);
    public void delete(int id);
    public List<ModelTask> getAll();
    public List<ModelTask> getTasksByUserId(int userId); 
}