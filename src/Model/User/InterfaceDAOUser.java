package Model.User;

public interface InterfaceDAOUser {

    public void insert(ModelUser user);

    public ModelUser loginCheck(String username, String password);
}