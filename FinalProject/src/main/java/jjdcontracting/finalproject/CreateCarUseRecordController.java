package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @FXML private TextField createuse_BookingID;
    @FXML private TextField createuse_StaffID;
    @FXML private TextField createuse_VehicleID;
    @FXML private TextField createuse_DateTimeOut;
    @FXML private TextField createuse_DateTimeIn;
    @FXML private TextArea createuse_Purpose;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Save() throws IOException {
        boolean dateInCheck = false;
        boolean dateOutCheck = false;
        boolean staffIdCheck = false;
        boolean vehicleIdCheck = false;
        boolean bookingDupeCheck = true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOutFormat = null;
        Date dateInFormat = null;
        try {
            //when the user presses the save button, this will get the values from each text field.
            int bookingID = Integer.parseInt(createuse_BookingID.getText());
            int staffID = Integer.parseInt(createuse_StaffID.getText());
            String vehicleID = createuse_VehicleID.getText();
            String dateOut = createuse_DateTimeOut.getText();
            String dateIn = createuse_DateTimeIn.getText();
            String purpose = createuse_Purpose.getText();
            // First verifcation check to see if staffID exists in system
            for (int i = 0; i < App.user.size(); i++) {
                if (App.user.get(i).getStaffID() == staffID) {
                    staffIdCheck = true;
                    break;
                }
            }
            // Second verifcation check to see if vehicles license plate exists in system
            for (int i = 0; i < App.vehicle.size(); i++) {
                if (App.vehicle.get(i).getVehiclePlate().contentEquals(vehicleID)) {
                    vehicleIdCheck = true;
                    break;
                }
            }
            //third verification check to ensure new bookingID is not in use
            for (int i = 0; i < App.uses.size(); i++) {
                if (App.uses.get(i).getSignOutID() == bookingID) {
                    bookingDupeCheck = false;
                    break;
                }
            }
            //checks if the dateOut is in the correct format dd/MM/yyyy
            dateOutFormat = sdf.parse(dateOut);
            if (dateOut.equals(sdf.format(dateOutFormat))) {
                dateOutCheck = true;
            }
            //checks if the dateIn is in the correct format dd/MM/yyyy
            dateInFormat = sdf.parse(dateIn);
            if (dateIn.equals(sdf.format(dateInFormat))) {
                dateInCheck = true;
            }
            // If checks are passed it will add to the array and file
            if (staffIdCheck == true && vehicleIdCheck == true && dateInCheck == true && dateOutCheck == true && bookingDupeCheck == true) {
                // Call the constructor to create the instance (may need to pull up extra info from other arrays to populate the call)
                // Then add the created instance to the array - see similar section in CreateRegisteredUser for working example
                SignOutRecord newUse = new SignOutRecord(bookingID, staffID, vehicleID, purpose, dateOut, dateIn);
                App.uses.add(newUse);
                DataHandler.writeData(App.uses, "SignOutRecords.ser");
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Your record was added.");
                alert.setContentText("You can enter another record, or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
                // If any checks fail it will display error message and not add to the system
            } else if (staffIdCheck == false || vehicleIdCheck == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Unable to save");
                alert.setHeaderText("Your record was not added. Please ensure that the staffID and vehicleID are correct and already entered into the system.");
                alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
            } else if (dateInCheck == false || dateOutCheck == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Unable to save");
                alert.setHeaderText("Your record was not added. Please ensure that the date in and date out are in the correct formats (dd/mm/yyyy).");
                alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
            } else if (bookingDupeCheck == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Unable to save");
                alert.setHeaderText("This booking ID is already in use, please try a different booking ID.");
                alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                alert.showAndWait();
                ClearForm();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Your record could not be added.");
            alert.setContentText("Check that all fields are completed, with numbers where noted and that dates are entered in the correct format (dd/mm/yyyy). \n\nError message: \"" + e + "\".");
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
