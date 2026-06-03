package Model.Project;

import java.util.List;

public interface InterfaceDAOProject {
    public void insert(ModelProject project);
    public void update(ModelProject project);
    public void delete(int id);
    public void addMemberToProject(int projectId, int userId);
    public List<ModelProject> getAll();
}