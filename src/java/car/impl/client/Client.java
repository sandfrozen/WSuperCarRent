/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.impl.client;

import car.database.DBCars;
import car.database.DBCustomers;
import car.database.DBReservations;
import car.exceptions.InvalidReservationDateException;
import car.interfaces.ClientInterface;
import car.objects.Car;
import car.objects.Customer;
import car.objects.Reservation;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.ws.WebServiceContext;
import javax.jws.WebService;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author tomek.buslowski
 */
@MTOM
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

        System.out.println("//////////////Server got: " + username + " " + password);

        //Should validate username and password with database
        String passwordFromDB = DBCustomers.getPasswordForMail(username);

        return password.equals(passwordFromDB);
    }

    @Override
    public int loginCustomer(String mail, String password) {
        String passwordFromDB = DBCustomers.getPasswordForMail(mail);
        if (password.equals(passwordFromDB)) {
            int id = DBCustomers.getCustomer(mail).id;
            if (id > 0) {
                return id;
            }
        }
        return 0;
    }

    @Override
    public boolean addAccount(String name, String surname, String mail, String password) {
        return DBCustomers.addCustomer(new Customer(name, surname, mail, password));
    }

    @Override
    public Customer getCustomer(int id) {
        return DBCustomers.getCustomer(id);
    }

    @Override
    public boolean editAccount(Customer customer) {
        return DBCustomers.editCustomer(customer);
    }

    @Override
    public boolean newReservation(int car_id, int customer_id, String from, String to) throws InvalidReservationDateException {
        List<Reservation> carReservations = DBReservations.getCarReservations(car_id);
        Date fromDate = Date.valueOf(from);
        Date toDate = Date.valueOf(to);
        for (Reservation r : carReservations) {
            Date from2 = Date.valueOf(r.from);
            Date to2 = Date.valueOf(r.to);
            if (!(to2.before(fromDate) || toDate.before(from2))) {
                throw new InvalidReservationDateException("Samochód w tym terminie jest już zarezerwowany.", "Zmień czas rezerwacji");
            }
        }

        return DBReservations.addReservation(car_id, customer_id, from, to);
    }

    @Override
    public boolean editReservation(int id, int car_id, int customer_id, String from, String to) throws InvalidReservationDateException {
        List<Reservation> carReservations = DBReservations.getCarReservations(car_id);
        Date fromDate = Date.valueOf(from);
        Date toDate = Date.valueOf(to);
        for (Reservation r : carReservations) {
            if (r.id == id) {
                continue;
            }
            Date from2 = Date.valueOf(r.from);
            Date to2 = Date.valueOf(r.to);
            if (!(to2.before(fromDate) || toDate.before(from2))) {
                throw new InvalidReservationDateException("Samochód w tym terminie jest już zarezerwowany.", "Zmień czas rezerwacji");
            }
        }
        return DBReservations.editReservation(id, car_id, customer_id, from, to);
    }

    @Override
    public boolean removeReservation(int resId) {
        return DBReservations.removeReservation(resId);
    }

    @Override
    public Car getCar(int id) {
        return DBCars.getCar(id);
    }

    @Override
    public Image downloadCarImage(int carId) {  
        String surl = DBCars.getCarImage(carId);

        try {
            URL url = new URL(surl);
            Image image = ImageIO.read(url); 
            return image;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
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
    public Reservation getReservation(int resId) {
        return DBReservations.getReservation(resId);
    }

    @Override
    public List<Reservation> getCustomerReservations(int customerId) {
        List<Reservation> res = DBReservations.getCustomerReservations(customerId);
        return res;
    }

    public static void main(String[] args) throws SQLException {
        Client c = new Client();
        System.out.println(c.downloadCarImage(3));
        System.out.println(c.downloadCarImage(2));

    }
}
