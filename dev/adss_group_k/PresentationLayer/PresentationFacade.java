package adss_group_k.PresentationLayer;

import adss_group_k.PresentationLayer.Suppliers.UserInput;
import adss_group_k.PresentationLayer.Suppliers.UserOutput;

import static adss_group_k.PresentationLayer.Suppliers.PresentationController.startSupplierMenu;
import adss_group_k.PresentationLayer.Inventory.Main;

public class PresentationFacade {
    private static UserInput input = UserInput.getInstance();
    private static UserOutput output = UserOutput.getInstance();

    public static void main(String[] args) {
        output.println("Welcome to the supplier module!");
        if (input.nextBoolean("Would you like to load example data?")) {
            //service.seedExample();
            output.println("example data loaded.");
        }
        moduleSelector();
    }

    public static void moduleSelector(){
        boolean retry = true;
        int in = 0;
        while (retry) {
            in = input.nextInt("Which module do you need?\n" +
                    "1. Supplier module\n" +
                    "2. Inventory module");
            switch (in) {
                case (1): {
                    retry = false;
                    startSupplierMenu();
                    break;
                }
                case (2): {
                    retry = false;
                    Main.startInventoryMenu();
                    break;
                }
                default:
                    output.println("Please try again.");
            }

        }
    }
}
