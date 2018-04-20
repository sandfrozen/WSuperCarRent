/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import car.objects.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomek.buslowski
 */
public class DBAccess {

    private static Connection conn = null;
    private static Statement stmt = null;

    public static Client getClient(int id) {
        Client c = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Client WHERE idclient=" + id);
            if (rs.next()) {
                c = new Client(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public static List<Car> getCars() {
        List<Car> cars = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Car");
            while (rs.next()) {
                Car c = new Car(rs);
                cars.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cars;
    }
    
    public static List<Reservation> getReservations() {
        List<Reservation> res = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM reservation");
            while (rs.next()) {
                Reservation r = new Reservation(rs);
                res.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(DBAccess.getClient(0));
        System.out.println(DBAccess.getCars().get(0).dayCost);
        System.out.println(DBAccess.getReservations());
    }
}
