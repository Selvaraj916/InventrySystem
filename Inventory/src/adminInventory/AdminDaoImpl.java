package adminInventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl implements AdminDao {
    DBConnection c;
    @Override
    public void addAdmin(Admin dao1) {
        try {
            c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "INSERT INTO admin_details(email_id,password)Values(?,?)";
            PreparedStatement pt= con.prepareStatement(query);
            pt.setString(1,dao1.getEmail());
            pt.setString(2,dao1.getPass());
            int cot= pt.executeUpdate();
            if(cot!=0){
                System.out.println("Admin added Successfully");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
