/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author tomek.buslowski
 */
public class Reservation {
    
    public int id;
    public int idclient;
    public int idcar;
    public Timestamp from;
    public Timestamp to;

    public Reservation(int id, int idclient, int idcar, Timestamp from, Timestamp to) {
        this.id = id;
        this.idclient = idclient;
        this.idcar = idcar;
        this.from = from;
        this.to = to;
    }
    
    public Reservation(ResultSet rs) throws SQLException {
        this.id = rs.getInt("idreservation");
        this.idclient = rs.getInt("idclient");
        this.idcar = rs.getInt("idcar");
        this.from = rs.getTimestamp("from");
        this.to =  rs.getTimestamp("to");
    }
    
    @Override
    public String toString() {
        return this.id + " " + this.idclient + " " + this.idcar + " " + this.from + " " + this.to;
    }
}
