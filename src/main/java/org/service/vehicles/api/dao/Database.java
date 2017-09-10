package org.service.vehicles.api.dao;

import org.service.vehicles.api.services.dataobject.Vehicle;

import java.util.HashMap;

/*
* In Memory storage to be replaced by Database connection later on
* */
public class Database {
    public static HashMap<Integer, Vehicle> inMemStorage = new HashMap<Integer, Vehicle>();
    /*public Connection getConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/testsystem";
			Connection connection = null;
			cClass.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "root");
			return connection;
		} catch (Exception e) {
			throw e;
		}

	}*/

}