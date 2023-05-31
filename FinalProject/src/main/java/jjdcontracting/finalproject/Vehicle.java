package jjdcontracting.finalproject;

public class Vehicle {

    private String vehiclePlate;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    boolean serviceUpToDate = true;

    public Vehicle(String vehiclePlate, String vehicleMake, String vehicleModel, int vehicleYear) {
        this.vehiclePlate = vehiclePlate;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public boolean isServiceUpToDate() {
        return serviceUpToDate;
    }

    public void setServiceUpToDate(boolean serviceUpToDate) {
        this.serviceUpToDate = serviceUpToDate;
    }

}
