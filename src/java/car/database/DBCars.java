/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import car.objects.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tomek.buslowski
 */
public class DBCars extends DBAccess {

    public static List<Car> getCars() {
        List<Car> cars = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cars");
            while (rs.next()) {
                Car c = new Car(rs);
                cars.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("getCars() : " + ex.toString());
        }
        return cars;
    }

    public static Car getCar(int id) {
        Car car = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cars WHERE id=" + id);
            if (rs.next()) {
                car = new Car(rs);
            }
        } catch (SQLException ex) {
            System.out.println("getCars() : " + ex.toString());
        }
        return car;
    }
    
    public static String getCarImage(int carId) {
        String url = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imageUrl FROM Cars WHERE id=" + carId);
            if (rs.next()) {
                url = rs.getString("imageUrl");
            }
        } catch (SQLException ex) {
            System.out.println("getCars() : " + ex.toString());
        }
        return url;
    }

    public static boolean addCar(Car car) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO Cars"
                    + "(cars.brand, cars.model, cars.doors, cars.fuelCap, cars.fuelType,"
                    + "cars.range, cars.gearbox, cars.gears, cars.dayCost, cars.imageUrl)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, car.brand);
            ps.setString(2, car.model);
            ps.setInt(3, car.doors);
            ps.setInt(4, car.fuelCap);
            ps.setString(5, car.fuelType);
            ps.setInt(6, car.range);
            ps.setString(7, car.gearbox);
            ps.setInt(8, car.gears);
            ps.setFloat(9, car.dayCost);
            ps.setString(10, car.imageUrl);

            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("addCar(Car car) : " + ex.toString());
        }
        return false;
    }

    public static boolean editCar(Car car) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("UPDATE Cars SET cars.brand=?,"
                    + "cars.model=?, cars.doors=?, cars.fuelCap=?, cars.fuelType=?,"
                    + "cars.range=?, cars.gearbox=?, cars.gears=?, cars.dayCost=?,"
                    + "cars.imageUrl=? WHERE cars.id=" + car.id);
            ps.setString(1, car.brand);
            ps.setString(2, car.model);
            ps.setInt(3, car.doors);
            ps.setInt(4, car.fuelCap);
            ps.setString(5, car.fuelType);
            ps.setInt(6, car.range);
            ps.setString(7, car.gearbox);
            ps.setInt(8, car.gears);
            ps.setFloat(9, car.dayCost);
            ps.setString(10, car.imageUrl);
            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("editCar(Car car) : " + ex.toString());
        }
        return false;
    }

    public static boolean removeCar(int id) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            int status = stmt.executeUpdate("DELETE FROM cars WHERE id=" + id);
            if (status == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("removeCar(int id) : " + ex.toString());
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        // Main for Tests
        //Car car = new Car("WW", "Golf", 4, 50, "benzyna", 500, "automat", 5, (float) 49.99, "url");
        //car.id = 1;
        //DBCars.addCar(car);
        //DBCars.editCar(car);
        //DBCars.removeCar(3);
        System.out.println(DBCars.getCarImage(1));
        //System.out.println(DBCars.getCar(1));
    }
}
