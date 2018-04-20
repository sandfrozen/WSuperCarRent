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
public interface AdminInterface {
    
    @WebMethod
    List<Car> getCars();
    @WebMethod
    List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost);
    @WebMethod
    Car getCar(int carId);
    @WebMethod
    boolean addCar(Car car);
    @WebMethod
    boolean editCar(Car car);
    @WebMethod
    boolean removeCar(int carId);
    @WebMethod
    List<Reservation> carReservations(int carId);
    
    @WebMethod
    List<Reservation> getReservations();
    @WebMethod
    Reservation getReservation(int resId);
    @WebMethod
    boolean addReservation(Reservation res);
    @WebMethod
    boolean editReservation(Reservation res);
    @WebMethod
    boolean removeReservation(int id);
    
    @WebMethod
    List<Customer> getCustomers();
    @WebMethod
    List<Customer> serachCustomers(String name, String surname);
    @WebMethod
    Customer getCustomer(int customerId);
    @WebMethod
    boolean addCustomer(Customer customer);
    @WebMethod
    boolean editCustomer(Customer customer);
    @WebMethod
    boolean removeCustomer(int customerId);
    @WebMethod
    List<Reservation> customerReservations(int clientId);
}
