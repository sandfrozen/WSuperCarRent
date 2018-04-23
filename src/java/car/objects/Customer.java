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
public class Customer implements Serializable {
    
    public int id;
    public String name;
    public String surname;
    public String mail;
    public String password;

    public Customer(String name, String surname, String mail, String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

    public Customer(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.surname = rs.getString("surname");
        this.mail = rs.getString("mail");
        this.password = rs.getString("password");
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.surname + " " + this.mail;
    }
    
    
}
