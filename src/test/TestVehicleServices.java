import org.junit.Test;
import org.service.vehicles.api.services.VehicleService;
import org.service.vehicles.api.services.dataobject.Vehicle;

import static org.junit.Assert.assertEquals;

/**
 * Created by ramya on 19/2/17.
 */
public class TestVehicleServices {
    @Test
    public void testCreateUpdateVehicle() {
        System.out.println("Testing CreateUpdateVehicle()");
        VehicleService vehicleService = new VehicleService();
        Vehicle vehicleSample = new Vehicle();
        vehicleSample.setId(1000);
        vehicleSample.setMake("Make - 1000");
        vehicleSample.setModel("Model - 1000");
        vehicleSample.setcClass("Class - 1000");
        vehicleSample.setYear(2017);

        int iVehicleID = vehicleService.createorUpdateVehicle(vehicleSample);

        Vehicle vehicleSampleGet = vehicleService.getVehicleById(iVehicleID);
        assertEquals(vehicleSample.getId(), vehicleSampleGet.getId());
        assertEquals(vehicleSample.getMake(), vehicleSampleGet.getMake());
        assertEquals(vehicleSample.getModel(), vehicleSampleGet.getModel());
        assertEquals(vehicleSample.getcClass(), vehicleSampleGet.getcClass());
        assertEquals(vehicleSample.getYear(), vehicleSampleGet.getYear());

    }
    
}
