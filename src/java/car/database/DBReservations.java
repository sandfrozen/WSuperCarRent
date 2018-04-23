/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import static car.database.DBAccess.conn;
import car.objects.Reservation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tomek.buslowski
 */
public class DBReservations extends DBAccess {

    public static List<Reservation> getReservations() {
        List<Reservation> res = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations");
            while (rs.next()) {
                Reservation r = new Reservation(rs);
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("getReservations() : " + ex.toString());
        }
        return res;
    }
    
    public static Reservation getReservation(int id) {
        Reservation res = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations WHERE id=" + id);
            if (rs.next()) {
                res = new Reservation(rs);
            }
        } catch (SQLException ex) {
            System.out.println("getReservation() : " + ex.toString());
        }
        return res;
    }
    
    public static boolean addReservation(int car_id, int customer_id, String from, String to) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO Reservations"
                    + "(Reservations.car_id, Reservations.customer_id, Reservations.from, Reservations.to)"
                    + "VALUES (?, ?, ?, ?)");
            ps.setInt(1, car_id);
            ps.setInt(2, customer_id);                 
            ps.setDate(3, Date.valueOf(from));
            ps.setDate(4, Date.valueOf(to));

            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("addReservation(Reservation res) :\n" + ex.toString());
        }
        return false;
    }
    
    public static boolean editReservation(int id, int car_id, int customer_id, String from, String to) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("UPDATE Reservations SET "
                    + "cars.Reservations.car_id=?, cars.Reservations.customer_id=?,"
                    + "cars.Reservations.from=?, cars.Reservations.to=? WHERE cars.Reservations.id=" + id);
            ps.setInt(1, car_id);
            ps.setInt(2, customer_id);
            ps.setDate(3, Date.valueOf(from));  
            ps.setDate(4, Date.valueOf(to));
            
            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("editReservation(Reservation res) : " + ex.toString());
        }
        return false;
    }
    
    public static boolean removeReservation(int id) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            int status = stmt.executeUpdate("DELETE FROM Reservations WHERE id=" + id);
            if (status == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("removeReservation(int id) :\n" + ex.toString());
        }
        return false;
    }
    
    public static List<Reservation> getCarReservations(int carId) {
        List<Reservation> res = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations WHERE Reservations.car_id=" +carId);
            while (rs.next()) {
                Reservation r = new Reservation(rs);
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("getReservations() : " + ex.toString());
        }
        return res;
    }
    
    public static List<Reservation> getCustomerReservations(int customerId) {
        List<Reservation> res = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations WHERE Reservations.customer_id=" +customerId);
            while (rs.next()) {
                Reservation r = new Reservation(rs);
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("getReservations() : " + ex.toString());
        }
        return res;
    }
    
    public static void main(String[] args) throws SQLException {
        //Reservation res = new Reservation(3, 2, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        //DBReservations.addReservation(res);
        //res.id=3;
        //DBReservations.editReservation(res);
        //DBReservations.removeReservation(1);
        //  System.out.println(DBReservations.getCustomerReservations(1));
        //System.out.println(DBReservations.getReservations());
        //System.out.println(DBReservations.getReservation(1));
        
        Date from = Date.valueOf("2018-04-23");
        Date to = Date.valueOf("2018-04-24");
        System.out.println(to.after(from));
    }
}
