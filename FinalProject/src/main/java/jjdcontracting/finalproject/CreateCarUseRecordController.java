package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateCarUseRecordController implements Initializable {

    //captures the values inputed by user
    // TODO: This was set up following the screens in the project plan
    // Field are not 1:1 for fields in the SignOutRecord class (eg signoutID is envisaged)
    // Reconciliation/cleanup of these needed, also knock on changes to the ClearForm() method later in this class
    @FXML
    private TextField createuse_BookingID;
    @FXML
    private TextField createuse_StaffID;
    @FXML
    private TextField createuse_VehicleID;
    @FXML
    private TextField createuse_DateTimeOut;
    @FXML
    private TextField createuse_DateTimeIn;
    @FXML
    private TextArea createuse_Purpose;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Save() throws IOException {
        boolean staffIdCheck = false;
        boolean vehicleIdCheck = false;
        try {

            //when the user presses the save button, this will get the values from each text field.
            int bookingID = Integer.parseInt(createuse_BookingID.getText());
            int staffID = Integer.parseInt(createuse_StaffID.getText());
            String vehicleID = createuse_VehicleID.getText();
            String dateOut = createuse_DateTimeOut.getText();
            String dateIn = createuse_DateTimeIn.getText();
            String purpose = createuse_Purpose.getText();

            for (int i = 0; i < App.user.size(); i++) {
                if (App.user.get(i).getStaffID() == staffID) {
                    staffIdCheck = true;
                    break;
                }
            }

            for (int i = 0; i < App.vehicle.size(); i++) {
                if (App.vehicle.get(i).getVehiclePlate().contentEquals(vehicleID)) {
                    vehicleIdCheck = true;
                    break;
                }
            }

            if (staffIdCheck == true && vehicleIdCheck == true) {
                SignOutRecord newUse = new SignOutRecord(bookingID, staffID, vehicleID, purpose, dateOut, dateIn);
                App.uses.add(newUse);
                DataHandler.writeData(App.uses, "SignOutRecords.ser");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Your record was added.");
                alert.setContentText("You can enter another record, or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
            } else if (staffIdCheck == false || vehicleIdCheck == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Unable to save");
                alert.setHeaderText("Your record was not added. Please ensure that the staffID and vehicleID are correct and already entered into the system.");
                alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
            }

            // TODO: Determine approach to date/time out and back. 
            // Currently a value can be captured in the form but goes nowhere, instead current datetime is added for both in and back in the constructor (likely to be wrong).
            // Maybe a toggle for "make sign out date now and return blank to be entered later" as default?
            // TODO Validations? Probably only need to check for critical blanks. Parseint errors caught already in catch below. 
            // Might also need a staff duplication check, or do we just tolerate duplicate entries in this version?
            // TODO: Someone to look at intended usage of the SignOutRecord class and come up with appropriate insertion below
            // Call the constructor to create the instance (may need to pull up extra info from other arrays to populate the call)
            // Then add the created instance to the array - see similar section in CreateRegisteredUser for working example
            // SignOutRecord newRecord = new SignOutRecord(ARGS);
            // App.uses.add(newRecord);
            // Show success message
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Your record could not be added.");
            alert.setContentText("Check that all fields are completed, with numbers where noted. \n\nError message: \"" + e + "\".");
            alert.showAndWait();
        }

    }

    @FXML
    private void ClearForm() {
        createuse_BookingID.clear();
        createuse_StaffID.clear();
        createuse_VehicleID.clear();
        createuse_DateTimeOut.clear();
        createuse_DateTimeIn.clear();
        createuse_Purpose.clear();
    }

    //will take user back to MainMenu
    @FXML
    private void Back() throws IOException {
        App.setRoot("MainMenu");
    }

    //will ask for user confirmation before closing program
    @FXML
    private void exitClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit this program?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

}
