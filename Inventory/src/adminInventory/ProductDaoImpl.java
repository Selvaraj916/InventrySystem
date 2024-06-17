package adminInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao{
    DBConnection c;
    @Override

    public ArrayList<Object> showProducts() {


            ArrayList<Object> product = new ArrayList<>();
            try {
                c = DBConnection.getInstance();
                Connection con = c.getConnection();
                String query = "Select * from products";
                //PreparedStatement ptrm=con.prepareStatement(query);
                Statement smpt = con.createStatement();
                ResultSet rs = smpt.executeQuery(query);
                while (rs.next()) {
                    ArrayList<Object> cust = new ArrayList<>();
                    cust.add(rs.getInt(1));
                    cust.add(rs.getString(2));
                    cust.add(rs.getString(3));
                    cust.add(rs.getInt(4));
                    product.add(cust);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return product;
        }
        public int getunitprice(int id){
        try {
            c = DBConnection.getInstance();
            Connection con = c.getConnection();
        //    String query = "Select unit_price from products where product_id='id'";
              String query = "SELECT unit_price FROM products WHERE product_id='" + id + "'";
        //    PreparedStatement ptrm=con.prepareStatement(query);
       //     ptrm.setInt(1,id);
       //     ResultSet rs=ptrm.executeQuery(query);
            Statement smpt=con.createStatement();
            ResultSet rs=smpt.executeQuery(query);
            int s=0;
            if(rs.next())
             s=rs.getInt("unit_price");
        //    System.out.println("Total  "+s);
            return s;
        }
        catch(Exception e){
            e.printStackTrace();
        }

            return 0;
        }
        public void addProduct(Product po){
        try{
            c=DBConnection.getInstance();
            Connection con=c.getConnection();
            String query1="SELECT * FROM products WHERE name=?";
            PreparedStatement pt=con.prepareStatement(query1);
            pt.setString(1,po.getName());
            ResultSet rt=pt.executeQuery();
            if(rt.next()){
                String quer="UPDATE products SET quantity_in_stock=quantity_in_stock+? where name=?";
                PreparedStatement ps=con.prepareStatement(quer);
                ps.setInt(1,po.getQuantity());
                ps.setString(2, po.getName());
                int cot=ps.executeUpdate();
                if(cot!=0){
                    System.out.println("Items added Success fully");
                }
            }
            else {
                String query = "INSERT INTO products (name,quantity_in_stock,unit_price) VALUES (?,?,?)";
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, po.getName());
                pstm.setInt(2, po.getQuantity());
                pstm.setInt(3, po.unit_price());
                int cot = pstm.executeUpdate();
                if (cot != 0) {
                    System.out.println("Product Added Successfully");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        }

}
