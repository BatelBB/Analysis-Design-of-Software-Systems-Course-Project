package BusinessLayer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TruckManager extends User {

    private TruckingsBoard truckingsBoard;
    private Map<String, Driver> drivers; //saves the drivers by their usernames
    private Map<String, Vehicle> vehicles; //saves the vehicles by their registration plate

    public TruckManager(String name, String username, String password) throws Exception {
        super(name, username, password);
        this.role = Role.truckingManager;
        this.truckingsBoard = new TruckingsBoard(this);
        drivers = new ConcurrentHashMap<String, Driver>();
        vehicles = new ConcurrentHashMap<String, Vehicle>();
    }

    public synchronized void addDriver(Driver driver) throws Exception {
        if (driver == null)
            throw new IllegalArgumentException("No driver entered");
        drivers.put(driver.username, driver);
    }

    public synchronized void addVehicle(Vehicle vehicle)  { vehicles.put(vehicle.getRegistationPlate(), vehicle); }

    public synchronized List<String> getDriversUsernames() {
        List<String> toReturn = new LinkedList<String>();
        for (String username : drivers.keySet()) {
            toReturn.add(username);
        }
        return toReturn;
    }

    public synchronized List<String> getVehiclesRegistrationPlates() {
        List<String> toReturn = new LinkedList<String>();
        for (String registrationPlate : vehicles.keySet()) {
            toReturn.add(registrationPlate);
        }
        return toReturn;
    }

    public synchronized void addTrucking(int id, String registrationPlateOfVehicle, LocalDateTime date, String driverUsername, List<Site> sources, List<Site> destinations, List<ProductForTrucking> products) throws Exception {
        if (registrationPlateOfVehicle == null)
            throw new NullPointerException("The registration plate is empty");
        if (driverUsername == null)
            throw new NullPointerException("The driver's username is empty");
        Driver driver = getDriverByUsername(driverUsername);
        Vehicle vehicle = getVehicleByRegistrationPlate(registrationPlateOfVehicle);
        Trucking trucking = new Trucking(id, vehicle, date, driver, sources, destinations, products);
        driver.checkTrucking(trucking);
        truckingsBoard.addTrucking(trucking);
    }

    public synchronized void removeTrucking(int truckingId) {
        truckingsBoard.removeTrucking(truckingId);
    }

    public synchronized String printBoard() {
        return truckingsBoard.printBoard();
    }

    public synchronized String printTruckingsHistory() {
        return truckingsBoard.printTruckingsHistory();
    }

    public synchronized String printFutureTruckings() {
        return truckingsBoard.printFutureTruckings();
    }

    public synchronized String printBoardOfDriver(String driverUsername) {
        Driver driver = getDriverByUsername(driverUsername);
        return truckingsBoard.printBoardOfDriver(driver.username);
    }

    public synchronized String printTruckingsHistoryOfDriver(String driverUsername) {
        Driver driver = getDriverByUsername(driverUsername);
        return truckingsBoard.printTruckingsHistoryOfDriver(driver.username);
    }

    public synchronized String printFutureTruckingsOfDriver(String driverUsername) {
        Driver driver = getDriverByUsername(driverUsername);
        return truckingsBoard.printFutureTruckingsOfDriver(driver.username);
    }

    public synchronized String printBoardOfVehicle(String registrationPlate) {
        return truckingsBoard.printBoardOfVehicle(registrationPlate);
    }

    public synchronized String printTruckingsHistoryOfVehicle(String registrationPlate) {
        return truckingsBoard.printTruckingsHistoryOfVehicle(registrationPlate);
    }

    public synchronized String printFutureTruckingsOfVehicle(String registrationPlate) {
        return truckingsBoard.printFutureTruckingsOfVehicle(registrationPlate);
    }

    public synchronized void addSourcesToTrucking(int truckingId, List<Site> sources) throws Exception {
        truckingsBoard.addSourcesToTrucking(truckingId, sources);
    }

    public synchronized void addDestinationsToTrucking(int truckingId, List<Site> destinations) throws Exception {
        truckingsBoard.addDestinationsToTrucking(truckingId, destinations);
    }

    public synchronized void addProductsToTrucking(int truckingId, ProductForTrucking productForTrucking) throws Exception {
        truckingsBoard.addProductsToTrucking(truckingId, productForTrucking);
    }

    public synchronized void updateSourcesOnTrucking(int truckingId, List<Site> sources) throws Exception {
        truckingsBoard.updateSourcesOnTrucking(truckingId, sources);
    }

    public synchronized void updateDestinationsOnTrucking(int truckingId, List<Site> destinations) throws Exception {
        truckingsBoard.updateDestinationsOnTrucking(truckingId, destinations);
    }

    public synchronized void moveProductsToTrucking(int truckingId, Products productSKU) throws Exception {
        truckingsBoard.moveProductsToTrucking(truckingId, productSKU);
    }

    public synchronized void updateVehicleOnTrucking(int truckingId, String registrationOfVehicle) throws Exception {
        Vehicle vehicle = getVehicleByRegistrationPlate(registrationOfVehicle);
        truckingsBoard.updateVehicleOnTrucking(truckingId, vehicle);
    }

    public synchronized void updateDriverOnTrucking(int truckingId, String driverUsername) throws Exception {
        Driver driver = getDriverByUsername(driverUsername);
        truckingsBoard.updateDriverOnTrucking(truckingId, driver);
    }

    public synchronized void updateDateOnTrucking(int truckingId, LocalDateTime date) throws Exception {
        truckingsBoard.updateDateOnTrucking(truckingId, date);
    }

    protected synchronized void updateTotalWeight(int truckingId, int newWeight, Driver driver) throws Exception {
        truckingsBoard.updateTotalWeightOfTrucking(truckingId, newWeight, driver.getUsername());
    }

    private Driver getDriverByUsername(String driverUsername) {
        Driver driver = drivers.get(driverUsername);
        if (driver == null)
            throw new IllegalArgumentException("The driver is not under the current truck manager");
        return driver;
    }

    private Vehicle getVehicleByRegistrationPlate(String RegistrationPlate) {
        Vehicle vehicle = vehicles.get(RegistrationPlate);
        if (vehicle == null)
            throw new IllegalArgumentException("The vehicle is not under the current truck manager");
        return vehicle;
    }

}