package org.service.vehicles.api.dao;

import org.service.vehicles.api.services.dataobject.Vehicle;

import java.util.ArrayList;


/**
 * Created by ramya on 17/2/17.
 */
public class VehicleDAOImpl {

    /*Database db = new Database();
    Connection connection = null;
    PreparedStatement preparedStatementOrderTable = null;
    PreparedStatement preparedStatementOrderLineTable = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatementForSeats = null;

    public boolean createVehicleObject(Vehicle vehicle) {
        try {
            connection = db.getConnection();
            //placeholder for insert query
            String query = "Insert into Vehicle where ";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

    public void createOrUpdateVehicleObject(Vehicle vehicle) {
        int vehicleId = vehicle.getId();
        if (!Database.inMemStorage.containsKey(vehicleId))
            Database.inMemStorage.put(vehicleId, vehicle);
        else
            Database.inMemStorage.put(vehicleId, vehicle);

    }

    public Vehicle getVehicleObjectById(int id) {
        return Database.inMemStorage.get(id);

    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();

        for (int i : Database.inMemStorage.keySet()) {
            vehicleArrayList.add(Database.inMemStorage.get(i));
        }

        return vehicleArrayList;
    }

    public void deleteVehicleObject(Vehicle vehicle) {
        int id = vehicle.getId();
        if (Database.inMemStorage.get(id) != null) {
            Database.inMemStorage.remove(id);
        }

    }
}
