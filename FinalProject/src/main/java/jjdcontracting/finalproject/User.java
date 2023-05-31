
package jjdcontracting.finalproject;

public class User {

    private String staffName;
    private int staffID;
    private int staffExt;
    private String licenseNumber;
    private String licenseExpiry;
    boolean manualLicense = true;
    boolean busLicense = true;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getStaffExt() {
        return staffExt;
    }

    public void setStaffExt(int staffExt) {
        this.staffExt = staffExt;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(String licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
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
        return "User{" + "Staff Members Name=" + staffName + ", Staff Members ID=" + staffID
                + ", Staff Members Extension=" + staffExt + ", Staff Members License Number=" + licenseNumber
                + ", License Expiry=" + licenseExpiry + ", Does the staff member have a manual license?" + manualLicense
                + ", Does the staff member have a bus license?" + busLicense + '}';
    }

}
