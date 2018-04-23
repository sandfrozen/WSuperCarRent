/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.interfaces;

import car.exceptions.InvalidReservationDateException;
import car.objects.Car;
import car.objects.Customer;
import car.objects.Reservation;
import java.awt.Image;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author tomek.buslowski
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface ClientInterface {
    
    @WebMethod
    String greet();
    
    @WebMethod
    boolean authenticateCustomer();
    @WebMethod
    public int loginCustomer(String mail, String password);
    @WebMethod
    public boolean addAccount(String name, String surname, String mail, String password);
    @WebMethod
    boolean editAccount(Customer customer);
    @WebMethod
    public Customer getCustomer(int id);
    
    @WebMethod
    boolean newReservation(int car_id, int customer_id, String from, String to) throws InvalidReservationDateException;
    @WebMethod
    boolean editReservation(int id, int car_id, int customer_id, String from, String to) throws InvalidReservationDateException;
    @WebMethod
    boolean removeReservation(int resId);
    
    @WebMethod
    public Car getCar(int id);
    @WebMethod
    Image downloadCarImage(int carId);
    @WebMethod
    List<Car> getCars();
    @WebMethod
    List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost);
    @WebMethod
    Reservation getReservation(int id);
    @WebMethod
    List<Reservation> getCustomerReservations(int customerId);
    
}
