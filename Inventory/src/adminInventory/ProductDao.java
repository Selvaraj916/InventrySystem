package adminInventory;

import java.util.ArrayList;

public interface ProductDao {
    public ArrayList<Object> showProducts();
    public void addProduct(Product po);
}
