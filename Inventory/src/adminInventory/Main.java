package adminInventory;
import java.util.Iterator;
import java.util.Scanner;
public class Main {

    public static void main(String args[]) {
        Scanner sel = new Scanner(System.in);
        System.out.println("Enter 1 for customer 2 for admin:");
        String role1=sel.nextLine();
        int role = Integer.parseInt(role1);
        if(role==2){
            User user=new User();
            int checkadmin = 0;
            System.out.println("Enter your email:");
            String pho = sel.nextLine();
            System.out.println("Enter password:");
            String pas = sel.nextLine();
            Admin admin=new Admin();
            admin.setEmail(pho);
            admin.setPass(pas);
            boolean ver = user.loginadmin(admin);
            if (ver == false) {
                System.out.println("Invalid email or password");
            }
            else{
                System.out.println("Loged in Successfully");
                AdminView.adminview(admin);
            }

        }
        if(role == 1) {
            System.out.println("1.To Register");
            System.out.println("2.To Login");
            String chec=sel.nextLine();
            int che=Integer.parseInt(chec);
            if(che==1){
                Customer cus=new Customer();
                CustomerDaoImpl dao=new CustomerDaoImpl();
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
                CustomerView.customerview(cus);
            }
            else if(che==2) {
                User user = new User();
                int checkadmin = 0;
                System.out.println("Enter your Phone:");
                String pho = sel.nextLine();
                System.out.println("Enter password:");
                String pas = sel.nextLine();
                Customer cus = new Customer();
                cus.setPhone(pho);
                cus.setPass(pas);
                boolean ver = user.login(cus);
                if (ver == false) {
                    System.out.println("Invalid email or password");
                } else {
                    System.out.println("Logged in Successfully");
                    CustomerView.customerview(cus);
                }
            }
        }
    }
}
