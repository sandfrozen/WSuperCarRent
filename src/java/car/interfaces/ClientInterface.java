/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.interfaces;

import car.objects.Car;
import car.objects.Customer;
import car.objects.Reservation;
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
    boolean addAccount(Customer customer);
    @WebMethod
    boolean editAccount(Customer customer);
    
    @WebMethod
    boolean newReservation(Reservation res);
    @WebMethod
    boolean editReservation(Reservation res);
    @WebMethod
    boolean removeReservation(int resId);
    
    @WebMethod
    List<Car> getCars();
    @WebMethod
    List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost);
    @WebMethod
    List<Reservation> getCustomerReservation(int customerId);
    
}
