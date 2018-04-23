/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tomek.buslowski
 */
public class Car implements Serializable {
    public int id;
    public String brand;
    public String model;
    public int doors;
    public int fuelCap;
    public String fuelType;
    public int range;
    public String gearbox;
    public int gears;
    public float dayCost;
    public String imageUrl;

    public Car(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.brand = rs.getString("brand");
        this.model = rs.getString("model");
        this.doors = rs.getInt("doors");
        this.fuelCap = rs.getInt("fuelCap");
        this.fuelType = rs.getString("fuelType");
        this.range = rs.getInt("range");
        this.gearbox = rs.getString("gearbox");
        this.gears = rs.getInt("gears");
        this.dayCost = rs.getFloat("dayCost");
        this.imageUrl = rs.getString("imageUrl");
    }

    public Car(String brand, String model, int doors, int fuelCap, String fuelType, int range, String gearbox, int gears, float dayCost, String imageUrl) {
        this.brand = brand;
        this.model = model;
        this.doors = doors;
        this.fuelCap = fuelCap;
        this.fuelType = fuelType;
        this.range = range;
        this.gearbox = gearbox;
        this.gears = gears;
        this.dayCost = dayCost;
        this.imageUrl = imageUrl;
    }
    
    @Override
    public String toString() {
        return this.brand + " - " + this.model + " " + this.doors;
    }
    
    public String toUpperCase() {
        return this.brand.toUpperCase() + " " + this.model.toUpperCase();
    }
}
