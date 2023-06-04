
package jjdcontracting.finalproject;

public class Bus extends Vehicle {

    boolean wheelchairAccessible;
    
    public Bus(String vehiclePlate, String vehicleMake, String vehicleModel, int vehicleYear, boolean wheelchairAccessible) {
        super(vehiclePlate, vehicleMake, vehicleModel, vehicleYear);
        this.wheelchairAccessible = wheelchairAccessible;
    }
    
    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

}
