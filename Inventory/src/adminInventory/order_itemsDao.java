package adminInventory;

import java.util.ArrayList;

public interface order_itemsDao {
    public ArrayList<Object> showAllProducts();
    //show products based on id
    public ArrayList<Object> showProductsBasedOnid();
    //Add order
    public void AddOrders(Product pod,int ins,int cus_id);
}
