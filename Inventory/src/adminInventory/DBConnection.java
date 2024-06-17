package adminInventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Class.forName;

public class DBConnection {
    public static Connection con=null;
    public static DBConnection instance=null;

    private DBConnection() {

        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","selvaraj@04");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","selvaraj@04");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static DBConnection getInstance(){
            if(instance==null){
                instance=new DBConnection();
            }
            return instance;
        }
    public Connection getConnection(){
        return con;
    }

}
