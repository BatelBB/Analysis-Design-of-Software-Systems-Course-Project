package PresentaionLayer;

import ServiceLayer.Response;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class TruckManagerFunctionality extends UserFunctionality {

    public TruckManagerFunctionality() throws Exception {
        super();

    }

    public void removeTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Response response = service.removeTrucking(id);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Trucking number " + id + " removed successfully");

    }

    public void addVehicle() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose vehicle license: ");
        System.out.println("1. B");
        System.out.println("2. C");
        System.out.println("3. C1");
        System.out.println("4. C + E");
        String license = Main.getChoiceFromArray(new String[]{"B", "C", "C1", "C+E"});
        System.out.println("Enter the vehicle's model");
        String model = myObj.nextLine();
        System.out.println("Enter the vehicle's registration plate");
        String registrationPlate = myObj.nextLine();
        System.out.println("Enter the vehicle's weight");
        int weight, maxWeight;
        weight = checkIntField();
        System.out.println("Enter the vehicle's max weight");
        maxWeight = checkIntField();
        Response<String> response = service.addVehicle(license, registrationPlate, model, weight, maxWeight);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Vehicle added successfully");
    }


    private int checkIntField() {
        Scanner myObj = new Scanner(System.in);
        boolean done = false;
        int intField = 0;
        while (!done) {
            String stringField = myObj.next();
            try {
                intField = Integer.parseInt(stringField);
                done = true;
            } catch (NumberFormatException nfe) {
                System.out.println("This field is a number");
            }
        }
        return intField;
    }

    private <T> void printList(List<T> list) {
        int number = 1 ;
        for (T element : list) {
            {
                System.out.println(String.valueOf(number) +". " + element.toString());
                number++;
            }
        }
    }

    public void getDriversUsernames() {
        Response<List<String>> response = service.getDriversUsernames();
        if (response.ErrorOccured()) System.out.println(response.getErrorMessage());
        else printList(response.getValue());
    }


    public void getVehiclesRegistrationPlates() {
        Response<List<String>> response = service.getVehiclesRegistrationPlates();
        if (response.ErrorOccured()) System.out.println(response.getErrorMessage());
        else printList(response.getValue());
    }

    public void addTrucking() throws Exception {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter registration plate of the vehicle");
        String registrationPlate = getVehiclesFromTheUser();
        System.out.println("Enter year");
        int year = checkIntField();
        System.out.println("Enter month");
        int month = checkIntField();
        System.out.println("Enter day");
        int day = checkIntField();
        System.out.println("Enter hour");
        int hour = checkIntField();
        System.out.println("Enter minute");
        int minutes = checkIntField();
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minutes);
        System.out.println("Enter driver username");
        String driverUserName = getDriverUsernameFromTheUser();
        List<List<String>> sources = new LinkedList<>();
        System.out.println("Enter 1 to add source or 2 to end the action");
        String answer = myObj.nextLine();
        while (!answer.equals("2")) {
            if (answer.equals("1")) {
                sources.add(createSite());
                System.out.println("Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            }
        }
        List<List<String>> destinations = new LinkedList<>();
        System.out.println("Enter 1 to add destination or 2 to end the action");
        String answerTag = myObj.nextLine();
        while (!answerTag.equals("2")) {
            if (answerTag.equals("1")) {
                destinations.add(createSite());
                System.out.println("Enter 1 to add destination or 2 to end the action");
                answerTag = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add destination or 2 to end the action");
                answerTag = myObj.nextLine();
            }
        }
        List<Map<String, Integer>> products = new LinkedList<>();
        System.out.println("Enter 1 to add product or 2 to end the action");
        String ans = myObj.nextLine();
        while (!ans.equals("2")) {
            if (ans.equals("1")) {
                products.add(createProduct());
                System.out.println("Enter 1 to add product or 2 to end the action");
                ans = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add product or 2 to end the action");
                ans = myObj.nextLine();
            }
        }
        System.out.println("Enter length of the Trucking in hours");
        int hourT = checkIntField();
        System.out.println("Enter length of the Trucking in minutes");
        int minutesT = checkIntField();
        Response<String> response = service.addTrucking(registrationPlate, date, driverUserName, sources, destinations, products, hourT, minutesT);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Trucking added successfully");
    }

    public void printTruckingsBoard() {
        Response<String> response = service.printBoard();
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println(response.getValue());
    }

    public void printTruckingsHistory() {
        Response<String> response = service.printTruckingsHistory();
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println(response.getValue());
    }


    public void printFutureTruckings() {
        Response<String> response = service.printFutureTruckings();
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println(response.getValue());
    }

    public void printBoardOfDriver() {
        String driverUsername = getDriverUsernameFromTheUser();
        if (driverUsername != null) {
            Response<String> response = service.printBoardOfDriver(driverUsername);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void printTruckingHistoryOfDriver() {
        String driverUsername = getDriverUsernameFromTheUser();
        if (driverUsername != null) {
            Response<String> response = service.printTruckingsHistoryOfDriver(driverUsername);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void printFutureTruckingsOfDriver() {
        String driverUsername = getDriverUsernameFromTheUser();
        if (driverUsername != null) {
            Response<String> response = service.printFutureTruckingsOfDriver(driverUsername);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void printBoardOfVehicle() {
        String registationPlate = getVehiclesFromTheUser();
        if (registationPlate != null) {
            Response<String> response = service.printBoardOfVehicle(registationPlate);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void printTruckingsHistoryOfVehicle() {
        String registationPlate = getVehiclesFromTheUser();
        if (registationPlate != null) {
            Response<String> response = service.printTruckingsHistoryOfVehicle(registationPlate);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void printFutureTruckingsOfVehicle() {
        String registationPlate = getVehiclesFromTheUser();
        if (registationPlate != null) {
            Response<String> response = service.printFutureTruckingsOfVehicle(registationPlate);
            if (response.ErrorOccured())
                System.out.println(response.getErrorMessage());
            else
                System.out.println(response.getValue());
        }
    }

    public void addSourcesToTrucking() {
        List<List<String>> sources = new LinkedList<>();
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter 1 to add source or 2 to end the action");
        String answer = myObj.nextLine();
        while (!answer.equals("2")) {
            if (answer.equals("1")) {
                sources.add(createSite());
                System.out.println("Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            }
        }
        Response<String> response = service.addSourcesToTrucking(id, sources);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Added sources successfully");

    }

    public void updateSourcesOnTrucking() {
        List<List<String>> sources = new LinkedList<>();
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter 1 to add source or 2 to end the action");
        String answer = myObj.nextLine();
        while (!answer.equals("2")) {
            if (answer.equals("1")) {
                sources.add(createSite());
                System.out.println("Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add source or 2 to end the action");
                answer = myObj.nextLine();
            }
        }
        Response<String> response = service.updateSourcesOnTrucking(id, sources);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Update sources successfully");

    }

    public void addDestinationToTrucking() {
        List<List<String>> destinations = new LinkedList<>();
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter 1 to add destination or 2 to end the action");
        String answer = myObj.nextLine();
        while (!answer.equals("2")) {
            if (answer.equals("1")) {
                destinations.add(createSite());
                System.out.println("Enter 1 to add destination or 2 to end the action");
                answer = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add destination or 2 to end the action");
                answer = myObj.nextLine();
            }
        }
        Response<String> response = service.addDestinationToTrucking(id, destinations);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Added destionations successfully");

    }


    public void updateDestinationsOnTrucking() {
        List<List<String>> destinations = new LinkedList<>();
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter 1 to add destination or 2 to end the action");
        String answer = myObj.nextLine();
        while (!answer.equals("2")) {
            if (answer.equals("1")) {
                destinations.add(createSite());
                System.out.println("Enter 1 to add destination or 2 to end the action");
                answer = myObj.nextLine();
            } else {
                System.out.println("Wrong input, Enter 1 to add destination or 2 to end the action");
                answer = myObj.nextLine();
            }
        }
        Response<String> response = service.updateDestinationsOnTrucking(id, destinations);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Update destionations successfully");

    }

    private Map<String, Integer> createProduct() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose product: ");
        System.out.println("1. eggs");
        System.out.println("2. milk");
        System.out.println("3. water");
        String productName = Main.getChoiceFromArray(new String[]{"eggs", "milk", "water"});
        System.out.println("Enter quantity");
        int quantity = checkIntField();
        Map products = new ConcurrentHashMap();
        products.put(productName, quantity);
        return products;
    }

    private List<String> createSite() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter contact guy name");
        String name = myObj.nextLine();
        System.out.println("Enter city name");
        String city = myObj.nextLine();
        System.out.println("Enter phone number to contact");
        String pn = myObj.nextLine();
        System.out.println("Enter street");
        String street = myObj.nextLine();
        System.out.println("Enter house number");
        String hn = String.valueOf(checkIntField());
        System.out.println("Enter floor number");
        String fn = String.valueOf(checkIntField());
        System.out.println("Enter apartment number");
        String an = myObj.nextLine();
        System.out.println("Enter area");
        String area = myObj.nextLine();
        List<String> site = new LinkedList<>();
        site.add(name);
        site.add(city);
        site.add(pn);
        site.add(street);
        site.add(hn);
        site.add(fn);
        site.add(an);
        site.add(area);
        return site;
    }


    public void updateVehicleOnTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the vehicle registration plate");
        String registrationPlate = getVehiclesFromTheUser();
        Response<String> response = service.updateVehicleOnTrucking(id, registrationPlate);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Update vehicle successfully");

    }

    public void updateDriverOnTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter the driver username");
        String driverusername = getDriverUsernameFromTheUser();
        Response<String> response = service.updateDriverOnTrucking(id, driverusername);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Update driver successfully");

    }

    public void moveProductsToTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose product: ");
        System.out.println("1. eggs");
        System.out.println("2. milk");
        System.out.println("3. water");
        String productName = Main.getChoiceFromArray(new String[]{"eggs", "milk", "water"});
        Response<String> response = service.moveProductsToTrucking(id, productName);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Removed product successfully");

    }

    public void addProductToTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose product: ");
        System.out.println("1. eggs");
        System.out.println("2. milk");
        System.out.println("3. water");
        String productName = Main.getChoiceFromArray(new String[]{"eggs", "milk", "water"});
        System.out.println("Enter quantity");
        int quantity = checkIntField();
        Response<String> response = service.addProductToTrucking(id, productName, quantity);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Added product successfully");

    }

    public void updateDateOnTrucking() {
        System.out.println("Enter Trucking ID");
        int id = checkIntField();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter year");
        int year = checkIntField();
        System.out.println("Enter month");
        int month = checkIntField();
        System.out.println("Enter day");
        int day = checkIntField();
        System.out.println("Enter hour");
        int hour = checkIntField();
        System.out.println("Enter minute");
        int minutes = checkIntField();
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minutes);
        Response<String> response = service.updateDateOnTrucking(id, date);
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Update successfully");

    }


    private String getDriverUsernameFromTheUser() {
        Response<List<String>> drivers = service.getDriversUsernames();
        if (drivers.ErrorOccured()) {
            System.out.println(drivers.getErrorMessage());
            return null;
        }
        if (drivers.getValue() == null | drivers.getValue().size() == 0) {
            System.out.println("You have no drivers");
            return null;
        } else {
            System.out.println("Choose driver:");
            int choice = Main.printOptionsList(drivers.getValue());
            return drivers.getValue().get(choice-1);
        }
    }


    private String getVehiclesFromTheUser() {
        Response<List<String>> registrationPlates = service.getVehiclesRegistrationPlates();
        if (registrationPlates.ErrorOccured()) {
            System.out.println(registrationPlates.getErrorMessage());
            return null;
        }
        if (registrationPlates.getValue() == null | registrationPlates.getValue().size() == 0) {
            System.out.println("You have no vehicles");
            return null;
        } else {
            System.out.println("Choose vehicle:");
            int choice = Main.printOptionsList(registrationPlates.getValue());
            return registrationPlates.getValue().get(choice-1);
        }
    }

    public void getRegisterCode() {
        Response<List<String>> response = service.getRegisterCode();
        if (response.ErrorOccured())
            System.out.println(response.getErrorMessage());
        else
            System.out.println("Trucking manager register code: " + response.getValue());
    }

}
