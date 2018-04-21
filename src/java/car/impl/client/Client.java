/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.impl.client;

import car.database.DBCars;
import car.database.DBCustomers;
import car.database.DBReservations;
import car.interfaces.ClientInterface;
import car.objects.Car;
import car.objects.Customer;
import car.objects.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.jws.WebService;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author tomek.buslowski
 */
@WebService(endpointInterface = "car.interfaces.ClientInterface")
public class Client implements ClientInterface {
 
    @Override
    public String greet() {
        return "Hello from greet method";
    }
    
    @Resource
    WebServiceContext wsctx;

    @Override
    public boolean authenticateCustomer() {
        MessageContext mctx = wsctx.getMessageContext();
        //get detail from request headers
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        String username = "";
        String password = "";

        if (userList != null) {
            //get username
            username = userList.get(0).toString();
        }

        if (passList != null) {
            //get password
            password = passList.get(0).toString();
        }

        //Should validate username and password with database
        String passwordFromDB = DBCustomers.getPasswordForMail(username);
        
        return password.equals(passwordFromDB);
    }

    @Override
    public boolean addAccount(Customer customer) {
        return DBCustomers.addCustomer(customer);
    }

    @Override
    public boolean editAccount(Customer customer) {
        return DBCustomers.editCustomer(customer);
    }

    @Override
    public boolean newReservation(Reservation res) {
        return DBReservations.addReservation(res);
    }

    @Override
    public boolean editReservation(Reservation res) {
        return DBReservations.editReservation(res);
    }

    @Override
    public boolean removeReservation(int resId) {
        return DBReservations.removeReservation(resId);
    }

    @Override
    public List<Car> getCars() {
        return DBCars.getCars();
    }

    @Override
    public List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost) {
        // change implmentation
        return DBCars.getCars();
    }

    @Override
    public List<Reservation> getCustomerReservation(int customerId) {
        return DBReservations.getCustomerReservations(customerId);
    }

}
