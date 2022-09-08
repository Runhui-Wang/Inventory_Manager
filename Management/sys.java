package Management;

import management_sql.connect;

public class sys{
    public static void main(String[] args) {
        //Interface 
        String name = "Warehouse Inventory Management";
        login curr = new login(name);
        curr.initialize();
        connect dbs = new connect("jdbc:mysql://localhost:3306/dtest","root", "AaSs0532", "dtest");
    }
}
