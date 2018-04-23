/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.exceptions;

/**
 *
 * @author tomek.buslowski
 */
public class InvalidReservationDateException extends Exception {
    
    private String errorDetails = "";
    
    public InvalidReservationDateException(String reason, String errorDetails) {
        super(reason);
        this.errorDetails = errorDetails;
    }
    
    public String getFaultInfo() {
        return errorDetails;
    }
}
