package adss_group_k.PresentationLayer;

import adss_group_k.BusinessLayer.Inventory.Service.Service;
import adss_group_k.BusinessLayer.Suppliers.Service.SupplierService;
import adss_group_k.PresentationLayer.Inventory.InventoryPresentationFacade;
import adss_group_k.dataLayer.dao.PersistenceController;

public class Main {

    public static void main(String[] args) {
        new App("database.db").main();
    }

    private static void example(InventoryPresentationFacade pm) {
        pm.execute("addCategory Dairy Products");
        pm.execute("addSubCategory Dairy Products,Milks");
        pm.execute("addSubSubCategory Dairy Products,Milks,3%");
        pm.execute("addProduct My Milk,Tnuva,5.0,5.94,10,1,Dairy Products,Milks,3%");
        pm.execute("addItem 0,Yavne,shelf 2,Amos,2018-02-02 13:55,true");
        pm.execute("addSubCategory Dairy Products,Cheeses");
        pm.execute("addSubSubCategory Dairy Products,Cheeses,Non-fat");
        pm.execute("addProduct Emmek,Tnuva,5.0,5.94,10,1,Dairy Products,Cheeses,Non-fat");
        pm.execute("addItem 1,Yehud,shelf 3,Amit,2019-02-03 14:01,true");
        pm.execute("addItem 1,Yehud,shelf 3,Amit,2019-02-04 08:25,true");
        pm.execute("addItem 1,Yehud,shelf 3,Amit,2019-02-03 13:11,true");
        pm.execute("addItem 1,Yehud,shelf 3,Amit,2019-02-03 14:01,true");
        pm.execute("addItem 1,Yehud,shelf 3,Amit,2019-02-03 14:01,true");
        pm.execute("addCategory Meats");
        pm.execute("addSubCategory Meats,Chicken");
        pm.execute("addSubSubCategory Meats,Chicken,Chicken Breast");
        pm.execute("addProduct Mama-Off,Tnuva,55.00,60.01,5,2,Meats,Chicken,Chicken Breast");
        pm.execute("addItem 2,Rishon,storage 3,Alil,2021-02-12 01:02,false");
    }
}
