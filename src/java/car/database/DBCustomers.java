/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import static car.database.DBAccess.conn;
import car.objects.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author tomek.buslowski
 */
public class DBCustomers extends DBAccess {
    
    // Customer Authentication
    // in reality return name for Customer mail
    public static String getPasswordForMail(String mail) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cars.Customers.name FROM Customers WHERE mail='"+mail+"'");
            
            while (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            System.out.println("getPasswordForEmail() : " + ex.toString());
        }
        return null;
    }

    public static List<Customer> getCustomers() {
        List<Customer> res = new LinkedList<>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
            while (rs.next()) {
                Customer r = new Customer(rs);
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("getCustomers() : " + ex.toString());
        }
        return res;
    }

    public static Customer getCustomer(int id) {
        Customer customer = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers WHERE id=" + id);
            if (rs.next()) {
                customer = new Customer(rs);
            }
        } catch (SQLException ex) {
            System.out.println("getCustomer(int id) : " + ex.toString());
        }
        return customer;
    }

    public static boolean addCustomer(Customer customer) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO Customers "
                    + "(Customers.name, Customers.surname, Customers.mail) "
                    + "VALUES (?, ?, ?)");
            ps.setString(1, customer.name);
            ps.setString(2, customer.surname);
            ps.setString(3, customer.mail);

            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("addCustomer(Customer customer) :\n" + ex.toString());
        }
        return false;
    }

    public static boolean editCustomer(Customer customer) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("UPDATE Customers SET "
                    + "cars.Customers.name=?, cars.Customers.surname=?,"
                    + "cars.Customers.mail=? WHERE cars.Customers.id=" + customer.id);
            ps.setString(1, customer.name);
            ps.setString(2, customer.surname);
            ps.setString(3, customer.mail);

            int status = ps.executeUpdate();
            if (status == 1) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("editCustomer(Customer customer) : " + ex.toString());
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean removeCustomer(int id) {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            int status = stmt.executeUpdate("DELETE FROM Customers WHERE id=" + id);
            if (status == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("removeCustomer(int id) :\n" + ex.toString());
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
//        Customer c = new Customer("Tomek33", "Tomek33", "tom33@asd.pl");
//        if( DBCustomers.addCustomer(c)) {
//            System.out.println("Dodano");
//        }
//        c.id = 4;
//        if( DBCustomers.editCustomer(c)) {
//            System.out.println("Edytowano");
//        }
//        if (DBCustomers.removeCustomer(4)) {
//            System.out.println("Usunięto");
//        }
//        System.out.println(DBCustomers.getCustomers());

        System.out.println(getPasswordForMail("asd@we.pl"));
    }

}
