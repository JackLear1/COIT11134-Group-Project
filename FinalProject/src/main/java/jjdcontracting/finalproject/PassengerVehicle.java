package jjdcontracting.finalproject;

public class PassengerVehicle extends Vehicle{

    private int passengerSeats;
    boolean manualTransmission;

    public PassengerVehicle(String vehiclePlate, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleCategory, Boolean isAvailable, int passengerSeats, Boolean isManualTransmission) {
        super(vehiclePlate, vehicleMake, vehicleModel, vehicleYear, vehicleCategory, isAvailable);
        this.passengerSeats = passengerSeats;
        this.manualTransmission = isManualTransmission;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(int passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public boolean isManualTransmission() {
        return manualTransmission;
    }

    public void setManualTransmission(boolean manualTransmission) {
        this.manualTransmission = manualTransmission;
    }

}
