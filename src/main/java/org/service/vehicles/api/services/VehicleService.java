package org.service.vehicles.api.services;

import org.service.vehicles.api.dao.VehicleDAOImpl;
import org.service.vehicles.api.services.dataobject.ApiError;
import org.service.vehicles.api.services.dataobject.Vehicle;

import java.util.ArrayList;

/**
 * Created by ramya on 17/2/17.
 */
public class VehicleService {

    public int createorUpdateVehicle(Vehicle vehicle) {

        if (vehicle != null) {
            VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
            vehicleDAO.createOrUpdateVehicleObject(vehicle);
            return vehicle.getId();
        } else
            return -1;
    }


    public Vehicle getVehicleById(int id) {
        Vehicle vehicle = null;
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        vehicle = vehicleDAO.getVehicleObjectById(id);
        return vehicle;
    }

    public ArrayList<Vehicle> getVehicles() {

        ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        vehicleArrayList = vehicleDAO.getVehicles();
        return vehicleArrayList;

    }

    public void getDelete(Vehicle vehicle) {
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        vehicleDAO.deleteVehicleObject(vehicle);


    }

    public ApiError validate(Vehicle vehicle) {
        // Vehicles must have a non-null / non-empty make, model, and class specified, and the year must

        if (vehicle.getMake() == null || vehicle.getMake().trim().length() == 0)
            return new ApiError(9000, "Make of the Vehicle is not Optional");
        if (vehicle.getModel() == null || vehicle.getModel().trim().length() == 0)
            return new ApiError(9000, "Model of the Vehicle is not Optional");
        if (vehicle.getcClass() == null || vehicle.getcClass().trim().length() == 0)
            return new ApiError(9000, "Class of the Vehicle is not Optional");
        if (vehicle.getYear() < 1950 || vehicle.getYear() > 2050)
            return new ApiError(9000, "Year of the Vehicle Should be between 1950 and 2050");
        return null;
    }

    public ArrayList<Vehicle> getVehicleByMake(String sMake) {
        ArrayList<Vehicle> vehicles = getVehicles();
        ArrayList<Vehicle> matchedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (sMake.equalsIgnoreCase(vehicle.getMake())
                    || vehicle.getMake().toLowerCase().startsWith((sMake.toLowerCase()))
                    || vehicle.getMake().toLowerCase().contains((sMake.toLowerCase()))) {
                matchedVehicles.add(vehicle);
            }
        }
        return matchedVehicles;
    }
    public ArrayList<Vehicle> getVehicleByModel(String sModel) {
        ArrayList<Vehicle> vehicles = getVehicles();
        ArrayList<Vehicle> matchedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (sModel.equalsIgnoreCase(vehicle.getModel())
                    || vehicle.getModel().toLowerCase().startsWith((sModel.toLowerCase()))
                    || vehicle.getModel().toLowerCase().contains((sModel.toLowerCase()))) {
                matchedVehicles.add(vehicle);
            }
        }
        return matchedVehicles;
    }
    public ArrayList<Vehicle> getVehicleByModelAndMake(String sModel, String sMake) {
        ArrayList<Vehicle> vehicles = getVehicles();
        ArrayList<Vehicle> matchedVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (sModel.equalsIgnoreCase(vehicle.getModel())
                    || vehicle.getModel().toLowerCase().startsWith((sModel.toLowerCase()))
                    || vehicle.getModel().toLowerCase().contains((sModel.toLowerCase()))
                    &&
                sMake.equalsIgnoreCase(vehicle.getMake())
                    || vehicle.getMake().toLowerCase().startsWith((sMake.toLowerCase()))
                    || vehicle.getMake().toLowerCase().contains((sMake.toLowerCase())) ) {
                matchedVehicles.add(vehicle);
            }
        }
        return matchedVehicles;
    }
}

