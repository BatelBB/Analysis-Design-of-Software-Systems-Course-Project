package groupk.shared.presentation.command;

import groupk.logistics.business.DLicense;
import groupk.shared.presentation.CommandRunner;
import groupk.shared.service.dto.Employee;
import groupk.shared.service.dto.Product;
import groupk.shared.service.dto.Shift;
import groupk.shared.service.dto.Site;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LoadSample implements Command {
    @Override
    public String name() {
        return "load sample";
    }

    @Override
    public String description() {
        return "insert sample data";
    }

    @Override
    public boolean isMatching(String line) {
        return line.startsWith("load sample");
    }

    @Override
    public void execute(String[] command, CommandRunner runner) {
        if (command.length != 2) {
            System.out.println("Error: Too many arguments.");
            System.out.println("Usage:");
            System.out.println("> load sample");
            return;
        }

        System.out.println("Hang on! This might take a minute on slower drives.");

        Set<Employee.ShiftDateTime> all = new LinkedHashSet<Employee.ShiftDateTime>();
        for (Employee.ShiftDateTime s : Employee.ShiftDateTime.values())
            all.add(s);
        HashMap<Employee.Role, Integer> r1 = new HashMap<>();
        for (Employee.Role role : Employee.Role.values())
            r1.put(role, 0);
        r1.replace(Employee.Role.ShiftManager, 1);
        Site source = new Site("tamirHouse", "0543397995", "center", "batYam", "tamirStr", 13, 2, 3);
        Site destination = new Site("idoHouse", "0524321231", "center", "herzliya", "idoStr", 100, 1, 6);
        List<Site> sources = new LinkedList<>();
        List<Site> destinations = new LinkedList<>();
        sources.add(source);
        destinations.add(destination);
        Product product = new Product("Eggs_4902505139314", 2);
        List<Product> products = new LinkedList<>();
        products.add(product);

        Site source2 = new Site("miri", "0522226668", "north", "haifa", "miriSTR", 13, 2, 3);
        Site destination2 = new Site("lior", "0536545648", "sourh", "beersheva", "liorSTR", 100, 1, 6);
        List<Site> sources2 = new LinkedList<>();
        List<Site> destinations2 = new LinkedList<>();
        sources2.add(source2);
        destinations2.add(destination2);
        Product product2 = new Product("Water_7290019056966", 2);
        List<Product> products2 = new LinkedList<>();
        products.add(product);

        // HR.
        Employee HR1 = runner.getService().createEmployee(
                "Avi",
                "111",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.HumanResources,
                all).getValue();
        Employee HR2 = runner.getService().createEmployee(
                "Eli",
                "112",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.HumanResources,
                all).getValue();
        Employee HR3 = runner.getService().createEmployee(
                "Dana",
                "113",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.HumanResources,
                all).getValue();
        Employee HR4 = runner.getService().createEmployee(
                "Noa",
                "114",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.HumanResources,
                all).getValue();

        // Cashiers.
        Employee C1 = runner.getService().createEmployee(
                "Eli",
                "212",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Cashier,
                all).getValue();
        Employee C2 = runner.getService().createEmployee(
                "Noa",
                "214",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Cashier,
                all).getValue();

        // Drivers:
        Employee D1 = runner.getService().createEmployee(
                "Avi",
                "311",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Driver,
                all).getValue();
        Employee D2 = runner.getService().createEmployee(
                "Dana",
                "313",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Driver,
                all).getValue();

        // Logistics:
        Employee L1 = runner.getService().createEmployee(
                "Eli",
                "412",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Logistics,
                all).getValue();
        Employee L2 = runner.getService().createEmployee(
                "Noa",
                "414",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Logistics,
                all).getValue();

        // Logistics Managers:
        Employee LM1 = runner.getService().createEmployee(
                "Eli",
                "512",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.LogisticsManager,
                all).getValue();
        Employee LM2 = runner.getService().createEmployee(
                "Noa",
                "514",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.LogisticsManager,
                all).getValue();

        // Shift Managers:
        Employee SM1 = runner.getService().createEmployee(
                "Eli",
                "612",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.ShiftManager,
                all).getValue();
        Employee SM2 = runner.getService().createEmployee(
                "Noa",
                "614",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.ShiftManager,
                all).getValue();

        // Stockers:
        Employee S1 = runner.getService().createEmployee(
                "Avi",
                "711",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Stocker,
                all).getValue();
        Employee S2 = runner.getService().createEmployee(
                "Dana",
                "713",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.Stocker,
                all).getValue();

        // Store Managers:
        Employee StoreM1 = runner.getService().createEmployee(
                "Avi",
                "811",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.StoreManager,
                all).getValue();
        Employee StoreM2 = runner.getService().createEmployee(
                "Dana",
                "813",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.StoreManager,
                all).getValue();

        // Trucking Manger:
        Employee TM1 = runner.getService().createEmployee(
                "Avi",
                "911",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.TruckingManger,
                all).getValue();
        Employee TM2 = runner.getService().createEmployee(
                "Dana",
                "913",
                "Example",
                1,
                1, new GregorianCalendar(),
                30,
                0,
                0,
                Employee.Role.TruckingManger,
                all).getValue();

        //staff
        LinkedList<Employee> staff = new LinkedList<>();
        staff.add(SM1);
        //create shifts
        Shift shift1 = runner.getService().createShift(
                HR1.id,
                new GregorianCalendar(2023, Calendar.APRIL, 21),
                Shift.Type.Evening,
                staff,
                r1).getValue();

        Shift shift2 = runner.getService().createShift(
                HR1.id,
                new GregorianCalendar(2023, Calendar.APRIL, 23),
                Shift.Type.Morning,
                staff,
                r1).getValue();
        runner.getService().addEmployeeToShift(HR1.id, shift1.getDate(), shift1.getType(), D1.id);
        runner.getService().addEmployeeToShift(HR1.id, shift2.getDate(), shift2.getType(), D2.id);

        //add vehicles
        runner.getService().createVehicle(
                TM1.id,
                "B",
                "12315678",
                "mercedes",
                4,
                32);

        runner.getService().createVehicle(
                TM1.id,
                "C",
                "12345678",
                "volvo",
                8,
                22);


        //add licences

        runner.getService().addLicenseForDriver(
                D1.id,
                DLicense.B.name());


        runner.getService().addLicenseForDriver(
                D2.id,
                DLicense.C.name());


        //add Truckings7

        runner.getService().createDelivery(
                TM1.id,
                "12345678",
                LocalDateTime.of(2023, Month.APRIL, 23, 8, 0),
                D2.id,
                sources,
                destinations,
                products,
                2,
                0);


        runner.getService().createDelivery(
                TM1.id,
                "12315678",
                LocalDateTime.of(2023, Month.APRIL, 21, 18, 0),
                D1.id,
                sources2,
                destinations2,
                products2,
                1,
                0);

    }
}