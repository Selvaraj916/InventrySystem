package adminInventory;
import java.util.*;

public class AdminView {
    public static void adminview(Admin admin){
        Scanner sel=new Scanner(System.in);
        CustomerDaoImpl dao = new CustomerDaoImpl();
        System.out.println("Enter From the Menu");
        System.out.println("Welcome to Customer management application");
        do {
            System.out.println("Enter from the menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Show All Customer");
            System.out.println("3. Show Customer Based on Id");
            System.out.println("4. Delete the Customer");
            System.out.println("5. Show All Orders");
            System.out.println("6. Add Product");
            System.out.println("7. Add new Admin");
            System.out.println("9. Show customer Order");
            System.out.println("8. Exit");
            String ca1 = sel.nextLine();
            int ca = Integer.parseInt(ca1);
            switch (ca) {
                case 1: {
                    Customer cus=new Customer();
                    System.out.println("Enter First_Name: ");
                    String fname = sel.nextLine();
                    System.out.print("Enter Last_Name: ");
                    String lname = sel.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sel.nextLine();
                    System.out.print("Enter city: ");
                    String city = sel.nextLine();
                    System.out.print("Enter state: ");
                    String state = sel.nextLine();
                    System.out.print("Enter password: ");
                    String pass = sel.nextLine();
                    cus.setPhone(phone);
                    cus.setF_name(fname);
                    cus.setL_name(lname);
                    cus.setCity(city);
                    cus.setState(state);
                    cus.setPass(pass);
                    dao.createCustomer(cus);
                    break;
                }
                case 2: {
                    java.util.ArrayList<Object> obj = dao.showAllCustomers();
                    Iterator itr = obj.iterator();
                    System.out.println("Cus_id     F_Name     L_Name     City       State      Phone      ");
                    System.out.println("------------------------------------------------------------------");
                    while (itr.hasNext()) {
                        String s = "";
                        s += itr.next();
                        System.out.println(s);
                        for (int i = s.length(); i <= 11; i++) {
                            System.out.print(" ");
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter the ID you want to see");
                    String id1 = sel.nextLine();
                    int id = Integer.parseInt(id1);
                    java.util.ArrayList<Object> obj = dao.showCustomerBasedOnId(id);
                    if(obj!=null) {
                    Iterator itr = obj.iterator();
                        System.out.println("Cus_id     F_Name     L_Name     City       State      Phone      Points     ");
                        System.out.println("-----------------------------------------------------------------------------");
                        int f=0;
                        while (itr.hasNext()) {
                            String s = "";
                            s += itr.next();
                            System.out.println(s);
                            for (int i = s.length(); i <= 11; i++) {
                                System.out.print(" ");
                            }
                            f=1;
                        }
                        if(f==1){
                            System.out.println("No orders have been placed yet");
                        }
                    }

                    break;
                }
                case 4: {
                    System.out.println("Enter the ID you want to delete");
                    String id1 = sel.nextLine();
                    int id = Integer.parseInt(id1);
                    dao.deleteCustomer(id);
                }
                case 5: {
                    OrderDaoImpl od = new OrderDaoImpl();
                    java.util.ArrayList<Object> obj = od.showAllOrders();
                    Iterator itr = obj.iterator();
                    System.out.println("Order_id     Customer_id     Order_date     Status     ");
                    System.out.println("-----------------------------------------------------------------------------");
                    while (itr.hasNext()) {
                        String s = "";
                        s += itr.next();
                        System.out.println(s);
                        for (int i = s.length(); i <= 11; i++) {
                            System.out.print(" ");
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("Enter Product Name:");
                    String pn = sel.nextLine();
                    System.out.println("Enter Quantity:");
                    String quq1 = sel.nextLine();
                    int qua = Integer.parseInt(quq1);
                    System.out.println("Enter unit_price:");
                    String up1 = sel.nextLine();
                    int up = Integer.parseInt(up1);
                    Product pd = new Product(pn, qua, up);
                    ProductDaoImpl pod = new ProductDaoImpl();
                    pod.addProduct(pd);
                    break;
                }
                case 7: {
                    System.out.println("Enter new admin email:");
                    String na = sel.nextLine();
                    System.out.println("Enter new admin password:");
                    String pas = sel.nextLine();
                    Admin admi = new Admin();
                    admi.setPass(pas);
                    admi.setEmail(na);
                    AdminDaoImpl dao1 = new AdminDaoImpl();
                    dao1.addAdmin(admi);
                    break;
                }
                case 8: {
                    System.out.println("Thank you");
                    System.out.println();
                    System.exit(0);
                }
                case 10: {
                    dao.updateCustomer();
                    break;
                }
                case 9:{
                    System.out.println("Show order by customer id");
                    String cs=sel.nextLine();
                    int csi=Integer.parseInt(cs);
                    ArrayList<Object> list=new ArrayList<>();
                    order_itemsimpl myod=new order_itemsimpl();
                    list=myod.myOrder(csi);
                    Iterator itr=list.iterator();
                    if(list!=null){
                        System.out.println("order_id     f_name     l_name       name       price");
                        System.out.println("-----------------------------------------------------");
                    }
                    while(itr.hasNext()){
                        System.out.println(itr.next());
                    }
                    System.out.println();
                    break;
                }
            }
        }while(true);

        }
}
