package Model.Task;

public class DesignTask extends ModelTask {
    private String figmaLink;

    public DesignTask() {
        this.tipeTugas = "DESIGN";
    }

    @Override
    public String getLampiranSpesifik() {
        return this.figmaLink;
    }

    public String getFigmaLink() { return figmaLink; }
    public void setFigmaLink(String figmaLink) { this.figmaLink = figmaLink; }
}