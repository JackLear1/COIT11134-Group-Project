
package jjdcontracting.finalproject;
import java.time.LocalDateTime;

public class SignOutRecord extends User{
    
    private int signOutID;
    private String vehicleID;
    private String purpose;
    LocalDateTime dateTimeOut = LocalDateTime.now();
    LocalDateTime dateTimeBack = LocalDateTime.now();

    public SignOutRecord(int signOutID, String vehicleID, String purpose, String staffName, int staffID, int staffExt, String licenseNumber, String licenseExpiry) {
        super(staffName, staffID, staffExt, licenseNumber, licenseExpiry);
        this.signOutID = signOutID;
        this.vehicleID = vehicleID;
        this.purpose = purpose;
    }

    
    
    public int getSignOutID() {
        return signOutID;
    }

    public void setSignOutID(int signOutID) {
        this.signOutID = signOutID;
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

    public LocalDateTime getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(LocalDateTime dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public LocalDateTime getDateTimeBack() {
        return dateTimeBack;
    }

    public void setDateTimeBack(LocalDateTime dateTimeBack) {
        this.dateTimeBack = dateTimeBack;
    }

    public boolean isManualLicense() {
        return manualLicense;
    }

    public void setManualLicense(boolean manualLicense) {
        this.manualLicense = manualLicense;
    }

    public boolean isBusLicense() {
        return busLicense;
    }

    public void setBusLicense(boolean busLicense) {
        this.busLicense = busLicense;
    }

    @Override
    public String toString() {
        return "Sign Out Reccord{" + "Sign Out ID:" + signOutID + ", Vehcile ID:" + vehicleID + ", Purpose:" + purpose +
                ", Date & Time Out=" + dateTimeOut + ", Date & Time Back:" + dateTimeBack + '}';
    }


}

