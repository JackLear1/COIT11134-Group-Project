package jjdcontracting.finalproject;

import java.io.Serializable;

public class SignOutRecord implements Serializable{
    
    private int signOutID;
    private int staffID;
    private String vehicleID;
    private String purpose;
    private String dateTimeOut;
    private String dateTimeIn;

    public SignOutRecord(int signOutID, int staffID, String vehicleID, String purpose, String dateTimeOut, String dateTimeIn) {
        this.signOutID = signOutID;
        this.staffID = staffID;
        this.vehicleID = vehicleID;
        this.purpose = purpose;
        this.dateTimeOut = dateTimeOut;
        this.dateTimeIn = dateTimeIn;
    }

    public int getSignOutID() {
        return signOutID;
    }

    public void setSignOutID(int signOutID) {
        this.signOutID = signOutID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(String dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public String getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(String dateTimeBack) {
        this.dateTimeIn = dateTimeBack;
    }

    @Override
    public String toString() {
        return "Sign Out Reccord{" + "Sign Out ID:" + signOutID + ", Vehcile ID:" + vehicleID + ", Purpose:" + purpose +
                ", Date & Time Out=" + dateTimeOut + ", Date & Time Back:" + dateTimeIn + '}';
    }


}

