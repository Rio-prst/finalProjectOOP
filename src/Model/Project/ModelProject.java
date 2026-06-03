package Model.Project;

public class ModelProject {
    private Integer id;
    private String namaProyek;
    private String deskripsi;

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id; 
    }
    
    public String getNamaProyek() { 
       return namaProyek; 
    }
    
    public void setNamaProyek(String namaProyek) { 
       this.namaProyek = namaProyek; 
    }
    
    public String getDeskripsi() { 
       return deskripsi; 
    }
    
    public void setDeskripsi(String deskripsi) { 
        this.deskripsi = deskripsi;
    }
}