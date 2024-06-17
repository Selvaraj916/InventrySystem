package adminInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {


    DBConnection c;
    @Override
    public ArrayList<Object> showAllOrders() {
        ArrayList<Object> customer = new ArrayList<>();
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "Select * from orders";

            String q="SELECT o.order_id, o.customer_id, o.order_date,os.name FROM orders o JOIN order_status os ON o.status = os.order_status_id";
            //PreparedStatement ptrm=con.prepareStatement(query);
            Statement smpt = con.createStatement();
            ResultSet rs = smpt.executeQuery(q);
            while (rs.next()) {
                ArrayList<Object> cust = new ArrayList<>();
                cust.add(rs.getInt(1));
                cust.add(rs.getInt(2));
                cust.add(rs.getDate(3));
                cust.add(rs.getString(4));
                customer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ArrayList<Object> showOrdersBasedOnId(int id) {
        ArrayList<Object> customer = new ArrayList<>();
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "Select * from order WHERE order_id=?";
            PreparedStatement ptrm = con.prepareStatement(query);
            ptrm.setInt(1, id);
            ResultSet rs = ptrm.executeQuery();
            while (rs.next()) {
                ArrayList<Object> cust = new ArrayList<>();
                cust.add(rs.getInt(1));
                cust.add(rs.getString(2));
                cust.add(rs.getString(3));
                cust.add(rs.getString(4));
                cust.add(rs.getString(5));
                cust.add(rs.getString(6));
                cust.add(rs.getString(7));
                customer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;

    }

    @Override
    public void updateOrders() {

    }

    @Override
    public void deleteOrders(int id) {
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "DELETE FROM orders WHERE order_id=?";
            PreparedStatement ptrm = con.prepareStatement(query);
            ptrm.setInt(1, id);
            int cot = ptrm.executeUpdate();
            if (cot != 0) {
                System.out.println("Order Deleted successfully");
            } else {
                System.out.println("No order ID matches");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
