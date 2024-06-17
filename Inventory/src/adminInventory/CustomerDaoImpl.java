package adminInventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDaoInterface {

    DBConnection c;

    @Override
    public void createCustomer(Customer cus) {
        try {

            c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query1 = "SELECT * FROM customer where phone=?";
            PreparedStatement pt = con.prepareStatement(query1);
            pt.setString(1, cus.getPhone());
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successfully");
            } else {
                String query = "INSERT INTO customer (f_name, l_name, city, state, phone,password) VALUES (?, ?, ?, ?, ?,?)";


                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, cus.getF_name());
                pstm.setString(2, cus.getL_name());
                pstm.setString(3, cus.getCity());
                pstm.setString(4, cus.getState());
                pstm.setString(5, cus.getPhone());
                pstm.setString(6, cus.getPass());
                int cnt = pstm.executeUpdate();
                if (cnt != 0) {
                    System.out.println("Customer added successfully");
                }

                String qu = "INSERT INTO users(phone,pass)Values(?,?)";
                PreparedStatement ptr = con.prepareStatement(qu);
                ptr.setString(1, cus.getPhone());
                ptr.setString(2, cus.getPass());
                int cr = ptr.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Object> showAllCustomers() {
        ArrayList<Object> customer = new ArrayList<>();
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "Select cus_id,f_name,l_name,city,state,phone from customer";
            //PreparedStatement ptrm=con.prepareStatement(query);
            Statement smpt = con.createStatement();
            ResultSet rs = smpt.executeQuery(query);
            while (rs.next()) {
                ArrayList<Object> cust = new ArrayList<>();
                cust.add(rs.getInt(1));
                cust.add(rs.getString(2));
                cust.add(rs.getString(3));
                cust.add(rs.getString(4));
                cust.add(rs.getString(5));
                cust.add(rs.getString(6));
                customer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public ArrayList<Object> showCustomerBasedOnId(int id) {
        ArrayList<Object> customer = new ArrayList<>();
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "Select cus_id,f_name,l_name,city,state,phone from customer WHERE cus_id=?";
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

                customer.add(cust);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;

    }

    @Override
    public void updateCustomer() {

    }

    @Override
    public void deleteCustomer(int id) {
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "DELETE FROM customer WHERE cus_id=?";
            PreparedStatement ptrm = con.prepareStatement(query);
            ptrm.setInt(1, id);
            int cot = ptrm.executeUpdate();
            if (cot != 0) {
                System.out.println("Customer Deleted successfully");
            } else {
                System.out.println("No Customer ID matches");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCusId(String phone) {
        try {
            DBConnection c = DBConnection.getInstance();
            Connection con = c.getConnection();
            String query = "SELECT cus_id FROM customer WHERE phone=?";
            PreparedStatement ptrm = con.prepareStatement(query);
            ptrm.setString(1, phone);
            ResultSet rs = ptrm.executeQuery();
            if (rs.next()) {
                return rs.getInt("cus_id");
            } else {
                System.out.println("No Customer ID Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
