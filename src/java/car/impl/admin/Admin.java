/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.impl.admin;

import car.interfaces.AdminInterface;
import car.database.DBCars;
import car.database.DBCustomers;
import car.database.DBReservations;
import car.objects.Car;
import car.objects.Customer;
import car.objects.Reservation;
import java.util.List;
import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 *
 * @author tomek.buslowski
 */
@WebService(endpointInterface = "car.interfaces.AdminInterface")
@HandlerChain(file="handler-chain.xml")
public class Admin implements AdminInterface {

    @Override
    public List<Car> getCars() {
        return DBCars.getCars();
    }

    @Override
    public List<Car> searchCars(String brand, String model, int doors, String fuelType, int fuleCap, String engine, int range, String gearbox, int gears, int dayCost) {
        // change
        return DBCars.getCars();
    }

    @Override
    public Car getCar(int carId) {
        return DBCars.getCar(carId);
    }

    @Override
    public boolean addCar(Car car) {
        return DBCars.addCar(car);
    }

    @Override
    public boolean editCar(Car car) {
        return DBCars.editCar(car);
    }

    @Override
    public boolean removeCar(int carId) {
        return DBCars.removeCar(carId);
    }

    @Override
    public List<Reservation> carReservations(int carId) {
        return DBReservations.getCarReservations(carId);
    }

    @Override
    public List<Reservation> getReservations() {
        return DBReservations.getReservations();
    }

    @Override
    public Reservation getReservation(int resId) {
        return DBReservations.getReservation(resId);
    }

    @Override
    public boolean newReservation(int car_id, int customer_id, String from, String to) {
        return DBReservations.addReservation(car_id, customer_id, from, to);
    }

    @Override
    public boolean editReservation(int id, int car_id, int customer_id, String from, String to) {
        return DBReservations.editReservation(id, car_id, customer_id, from, to);
    }

    @Override
    public boolean removeReservation(int id) {
        return DBReservations.removeReservation(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return DBCustomers.getCustomers();
    }

    @Override
    public List<Customer> serachCustomers(String name, String surname) {
        // change
        return DBCustomers.getCustomers();
    }

    @Override
    public Customer getCustomer(int customerId) {
        return DBCustomers.getCustomer(customerId);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return DBCustomers.addCustomer(customer);
    }

    @Override
    public boolean editCustomer(Customer customer) {
        return DBCustomers.editCustomer(customer);
    }

    @Override
    public boolean removeCustomer(int customerId) {
        return DBCustomers.removeCustomer(customerId);
    }

    @Override
    public List<Reservation> customerReservations(int clientId) {
        return DBReservations.getCustomerReservations(clientId);
    }

}
