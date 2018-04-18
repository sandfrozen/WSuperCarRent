/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.interfaces;

import car.objects.Car;
import car.objects.Client;
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
    boolean addAccount(Client client);
    @WebMethod
    boolean editAccount(Client client);
    
    @WebMethod
    boolean newRes(Reservation res);
    @WebMethod
    boolean editRes(Reservation res);
    @WebMethod
    boolean delRes(Reservation res);
    
    @WebMethod
    List<Reservation> getCars();
    @WebMethod
    List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost);
    @WebMethod
    List<Reservation> getClientRes(int clientId);
    
}
