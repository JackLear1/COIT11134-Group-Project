
package jjdcontracting.finalproject;

import java.io.Serializable;

public class Bus extends Vehicle implements Serializable{

    boolean wheelchairAccessible;
    
    public Bus(String vehiclePlate, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleCategory, Boolean isAvailable, boolean wheelchairAccessible) {
        super(vehiclePlate, vehicleMake, vehicleModel, vehicleYear, vehicleCategory, isAvailable);
        this.wheelchairAccessible = wheelchairAccessible;
    }
    
    public void setBusMultiple(String vehicleMake, String vehicleModel, int vehicleYear, Boolean isAvailable, Boolean serviceUpToDate, Boolean isAccessible) {
        setVehicleMultiple(vehicleMake, vehicleModel, vehicleYear, isAvailable, serviceUpToDate); 
        this.wheelchairAccessible = isAccessible;
    }
    
    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

}
