package adminInventory;

import java.util.ArrayList;

public interface OrderDao {
    //show all order
    public ArrayList<Object> showAllOrders();
    //show orders based on id
    public ArrayList<Object> showOrdersBasedOnId(int id);
    //update order
    public void updateOrders();
    //delete order
    public void deleteOrders(int id);
}
