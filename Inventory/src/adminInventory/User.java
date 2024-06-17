package adminInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    DBConnection c;
    public boolean login(Customer cus){
        try{
            c=DBConnection.getInstance();
            Connection con=c.getConnection();
            String query="SELECT phone,password from customer where phone=?";
            PreparedStatement pt= con.prepareStatement(query);
            pt.setString(1, cus.getPhone());
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                String s=rs.getString(2);
                if(s.equals(cus.getPass())){
                    return true;
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean loginadmin(Admin admin){
        try{
            c=DBConnection.getInstance();
            Connection con=c.getConnection();
            String query="SELECT email_id,password FROM admin_details where password=?";
            PreparedStatement pt= con.prepareStatement(query);
            pt.setString(1, admin.getPass());
            ResultSet rs=pt.executeQuery();
            if(rs.next()){
                String s=rs.getString(1);
                if(s.equals(admin.getEmail())){
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
