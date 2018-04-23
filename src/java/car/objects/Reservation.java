/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.objects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author tomek.buslowski
 */
public class Reservation implements Serializable {
    
    public int id;
    public int car_id;
    public int customer_id;
    public String from;
    public String to;

    public Reservation(int car_id, int customer_id, String from, String to) {
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.from = from;
        this.to = to;
    }
    
    public Reservation(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.car_id = rs.getInt("car_id");
        this.customer_id = rs.getInt("customer_id");
        this.from = rs.getDate("from").toString();
        this.to =  rs.getDate("to").toString();
    }
    
    @Override
    public String toString() {
        return this.id + " " + this.car_id + " " + this.customer_id + " " + this.from + " " + this.to;
    }
}
