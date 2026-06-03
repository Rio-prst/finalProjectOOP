package Model.Task;

public class DevelopmentTask extends ModelTask {
    private String repositoryUrl;

    public DevelopmentTask() {
        this.tipeTugas = "DEVELOPMENT";
    }

    @Override
    public String getLampiranSpesifik() {
        return this.repositoryUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }
    
    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }
}