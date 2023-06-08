package jjdcontracting.finalproject;

import java.io.Serializable;

public class Vehicle implements Serializable{
    
    // This is to manage serialisation conflicts while we're all working on the code. 
    // Fine to leave in in a single user implementation, should be removed if implenting as multi.
    private static final long serialVersionUID = 123456789;

    private String vehiclePlate;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    boolean serviceUpToDate = true;
    private String vehicleCategory;
    private Boolean isAvailable;

    public Vehicle(String vehiclePlate, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleCategory, Boolean isAvailable) {
        this.vehiclePlate = vehiclePlate;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleCategory = vehicleCategory;
        this.isAvailable = isAvailable;
    }
    
    public void setVehicleMultiple(String vehicleMake, String vehicleModel, int vehicleYear, Boolean isAvailable, Boolean serviceUpToDate) {
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.isAvailable = isAvailable;
        this.serviceUpToDate = serviceUpToDate;
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
    
    public void setVehicleCategory(String vehicleCategory) {
    this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }

    public void isAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
