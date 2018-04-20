/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tomek.buslowski
 */
public class DBAccess {
    protected static Connection conn = null;
    protected static Statement stmt = null;
}
