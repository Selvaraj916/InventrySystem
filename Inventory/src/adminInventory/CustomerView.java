package adminInventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CustomerView {

    public static boolean payment(int ins){
        Scanner sel=new Scanner(System.in);
        System.out.println("Enter 1 to pay 2 to exit");
        String bul1=sel.nextLine();
        int but=Integer.parseInt(bul1);
        if(but==1){
            System.out.println("Payment Successfull");
            return true;
        }
        return false;
    }

    public static void customerview(Customer cus){
        Scanner sel=new Scanner(System.in);
        CustomerDaoImpl dao=new CustomerDaoImpl();
        int cus_id=dao.getCusId(cus.getPhone());
        do {
            System.out.println("Enter from the menu:");
            System.out.println("1.Show all products");
            System.out.println("2.Place Order");
            System.out.println("3.Show my Order");
            System.out.println("4.Exit");
            String cas1=sel.nextLine();
            int cas = Integer.parseInt(cas1);
            switch (cas) {

                case 1: {
                    ProductDaoImpl pro = new ProductDaoImpl();
                    java.util.ArrayList<Object> obj = pro.showProducts();
                    Iterator itr = obj.iterator();
                    System.out.println("Product_id     Name     Quantity_in_Stock     unit_price     ");
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
                case 2: {
                    ProductDaoImpl pro = new ProductDaoImpl();
                    System.out.println("Enter the product ID:");
                    String id1=sel.nextLine();
                    int id = Integer.parseInt(id1);
                    System.out.println("Enter the quantity:");
                    String quq1=sel.nextLine();
                    int qua =Integer.parseInt(quq1) ;
                    int getunitprice=pro.getunitprice(id);
                    int ins=qua*getunitprice;
                    System.out.println("Total "+ins);
                    boolean pay=payment(ins);
                    if(pay==true) {
                        Product pod = new Product(id, qua);
                        order_itemsimpl od = new order_itemsimpl();
                        od.AddOrders(pod, ins,cus_id);
                    }
                    break;
                }
                case 3:{
                    ArrayList<Object> list=new ArrayList<>();
                    order_itemsimpl myod=new order_itemsimpl();
                    list=myod.myOrder(cus_id);
                    if(list!=null) {
                        Iterator itr = list.iterator();
                        System.out.println("order_id     f_name     l_name       name       price");
                        System.out.println("-----------------------------------------------------");
                        int f=0;
                        while (itr.hasNext()) {
                            System.out.println(itr.next());
                            f=1;
                        }
                        if(f==0){
                            System.out.println("No Order have been placed by you yet");
                        }
                        System.out.println();
                    }
                    break;
                }
                case 4:{
                    System.out.println("Thank you for using our service");
                    System.exit(0);
                }
            }
        }while(true);

    }
}
