package Config;
import java.sql.*;

public class Connector {
    private static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private static String namaDb = "db_project_management";
    private static String urlDb = "jdbc:mysql://localhost:3306/" + namaDb;
    private static String usernameDb = "root";
    private static String passwordDb = "";
    
    static Connection conn;
    
    //menghubungkan program ke database
    public static Connection Connect() {
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
            
            //menampilkan pesan jika koneksi ke db sukses
            System.out.println("MySQL Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getLocalizedMessage());
        }
        
        return conn;
    }
    
}