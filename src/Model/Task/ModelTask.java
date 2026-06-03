package Model.Task;

public class ModelTask {
    protected Integer id;
    protected Integer projectId;
    protected Integer userId;
    protected String judul;
    protected String status;
    protected String tipeTugas;
    protected String deadline;

    public String getLampiranSpesifik() {
        return "-";
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getProjectId() {
        return projectId;
    }
    
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTipeTugas() {
        return tipeTugas;
    }
    
    public void setTipeTugas(String tipeTugas) {
        this.tipeTugas = tipeTugas;
    }
    
    public String getDeadline() { 
       return deadline;
    }
    
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}