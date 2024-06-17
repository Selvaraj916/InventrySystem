package adminInventory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class order_itemsimpl {
    DBConnection c;
    public void AddOrders(Product pod,int ins,int cus_id){
            try{

                c=DBConnection.getInstance();
                Connection con=c.getConnection();
                String query="INSERT INTO order_items (product_id, quantity,price) VALUES (?,?,?)";
                PreparedStatement pstm = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            //    PreparedStatement pstm=con.prepareStatement(query);
                pstm.setInt(1,pod.getProduct_id());
                pstm.setInt(2,pod.getQuantity());
                pstm.setInt(3,ins);
                int cnt=pstm.executeUpdate();
                int oid=0;
                ResultSet gk = pstm.getGeneratedKeys();
                if(cnt!=0) {

                    if (gk.next()) {
                        oid = gk.getInt(1);
                    }
                  //  System.out.println("try "+oid);
                    System.out.println("Order Placed successfully");
                    LocalDate currentDate = LocalDate.now();
                    String query1 = "INSERT INTO orders(order_id,customer_id,order_date,status) VALUES (?,?,?,?)";
                    PreparedStatement pstm1=con.prepareStatement(query1);
                    pstm1.setInt(1,oid);
                    pstm1.setInt(2,cus_id);
                    pstm1.setDate(3, Date.valueOf(currentDate));
                    pstm1.setInt(4,1);
                    int cnt1=pstm1.executeUpdate();
                    String que="UPDATE products SET quantity_in_stock=quantity_in_stock-? WHERE product_id=?";
                    PreparedStatement pq=con.prepareStatement(que);
                    pq.setInt(1,pod.getQuantity());
                    pq.setInt(2,pod.getProduct_id());
                    int co=pq.executeUpdate();
                /*    if(co!=0){
                        System.out.println("--");
                    } */

                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
    }
    public ArrayList<Object> myOrder(int cus){
        ArrayList<Object> list=new ArrayList<>();
        try{
            c=DBConnection.getInstance();
            Connection con=c.getConnection();
          //  String query="SELECT ORDERS.CUSTOMER_ID,ORDER_ITEMS.PRODUCT_ID,PRODUCTS.NAME FROM ORDERS JOIN ORDER_ITEMS ON ORDERS.ORDER_ID=ORDER_ITEMS.ORDER_ID JOIN PRODUCTS ON ORDER_ITEMS.PRODUCT_ID=PRODUCTS.PRODUCT_ID WHERE Customer_id=?";
            String query="SELECT ORDERS.ORDER_ID,CUSTOMER.F_NAME,CUSTOMER.L_NAME,PRODUCTS.NAME,ORDER_ITEMS.PRICE FROM ORDERS JOIN ORDER_ITEMS ON ORDERS.ORDER_ID=ORDER_ITEMS.ORDER_ID JOIN PRODUCTS ON ORDER_ITEMS.PRODUCT_ID=PRODUCTS.PRODUCT_ID JOIN CUSTOMER ON CUSTOMER.CUS_ID=ORDERS.CUSTOMER_ID WHERE customer_id=?";
            PreparedStatement pt=con.prepareStatement(query);
            pt.setInt(1,cus);
            ResultSet rs=pt.executeQuery();
            while(rs.next()){
                ArrayList<Object> list1=new ArrayList<>();
                int ci=rs.getInt("order_id");
                String f_name=rs.getString("f_name");
                String l_name=rs.getString("l_name");
                String name=rs.getString("name");
                int pri=rs.getInt("price");
                list1.add(ci);
                list1.add(f_name);
                list1.add(l_name);
                list1.add(name);
                list1.add(pri);
                list.add(list1);
            }
            return list;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
