package adss_group_k.PresentationLayer.Suppliers;

public class UserOutput {
    private static UserOutput instance = null;

    private UserOutput() {

    }

    public static UserOutput getInstance() {
        if (instance == null)
            instance = new UserOutput();
        return instance;
    }

    public void println(String arg) {
        System.out.println(arg);
    }

    void print(String arg) {
        System.out.print(arg);
    }
}
