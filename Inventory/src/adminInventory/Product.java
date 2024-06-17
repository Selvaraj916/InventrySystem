package adminInventory;

public class Product {
    String name;
    int product_id;
    int quantity;
    int unit_price;

    public Product(int product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }
    public Product(String name,int quantity,int up) {
        this.name=name;
        this.unit_price = up;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public int unit_price(){
        return unit_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
