package adminInventory;

import java.util.ArrayList;

public interface CustomerDaoInterface {
    //create customer
    public void createCustomer(Customer cus);
    //show all customers
    public ArrayList<Object> showAllCustomers();
    //show customer based on id
    public ArrayList<Object> showCustomerBasedOnId(int id);
    //update customer
    public void updateCustomer();
    //delete customer
    public void deleteCustomer(int id);
    public int getCusId(String phone);
}
