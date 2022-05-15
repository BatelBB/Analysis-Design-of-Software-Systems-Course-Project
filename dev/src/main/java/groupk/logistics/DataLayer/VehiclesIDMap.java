package groupk.logistics.DataLayer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehiclesIDMap {
    public Map<String, VehicleDTO> vehicleMap;
    private static VehiclesIDMap singletonVehiclesMapperInstance = null;
    private VehiclesIDMap() {
        vehicleMap = new ConcurrentHashMap<String, VehicleDTO>();
    }


    public static VehiclesIDMap getInstance() throws Exception {
        if (singletonVehiclesMapperInstance == null)
            singletonVehiclesMapperInstance = new VehiclesIDMap();
        return singletonVehiclesMapperInstance;
    }


}
