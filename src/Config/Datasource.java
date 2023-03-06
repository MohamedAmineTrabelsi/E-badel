
package Config;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Datasource {

    private static String url = "jdbc:mysql://localhost:3306/badel";
    private static String user = "root";
    private static String pwd = "";
    
    private Connection cnx;
    private static Datasource instance;
    
    private Datasource() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("database connected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public static Datasource getInstance() {
        if(instance == null){
            instance = new Datasource();
        }
        return instance;
    }
    
    
    
    
    
}
