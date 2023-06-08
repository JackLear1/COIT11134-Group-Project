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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewSingleCarUsesController implements Initializable {

    @FXML
    private TextField bookingIdSearch;
    @FXML
    private TextField errorMsg;
    @FXML
    private TextField bookingIdTextField;
    @FXML
    private TextField vehicleIdTextField;
    @FXML
    private TextField dateOutTextField;
    @FXML
    private TextField staffIdTextField;
    @FXML
    private TextField dateInTextField;
    @FXML
    private TextField borrowingTextField;
    @FXML
    private Text helpEdit;

    int sessionID = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookingIdTextField.setEditable(false);
        staffIdTextField.setEditable(false);
        vehicleIdTextField.setEditable(false);
        dateOutTextField.setEditable(false);
        dateInTextField.setEditable(false);
        borrowingTextField.setEditable(false);
        helpEdit.setVisible(false);
        errorMsg.setVisible(false);
    }

    //will allow user to search through car use records
    @FXML
    private void Search() throws IOException {
        try {

            int searchKey = Integer.parseInt(bookingIdSearch.getText());

            for (int i = 0; i < App.user.size(); i++) {
                if (App.uses.get(i).getSignOutID() == searchKey) {
                    sessionID = i;
                    recallInfo(sessionID);
                    errorMsg.setVisible(false);
                    break;
                } else {
                    errorMsg.setVisible(true);
                    errorMsg.setText("ID not found!");
                }
            }
        } catch (NumberFormatException e) {
            errorMsg.setVisible(true);
            errorMsg.setText("Please enter an ID!");
        }
    }

    //will pull the data from the array using the found sessionID
    private void recallInfo(int id) {
        bookingIdTextField.setText(String.valueOf(App.uses.get(id).getSignOutID()));
        staffIdTextField.setText(String.valueOf(App.uses.get(id).getStaffID()));
        vehicleIdTextField.setText(App.uses.get(id).getVehicleID());
        dateOutTextField.setText(App.uses.get(id).getDateTimeOut());
        dateInTextField.setText(App.uses.get(id).getDateTimeIn());
        borrowingTextField.setText(App.uses.get(id).getPurpose());

    }

    //will save edits that user has made to record
    @FXML
    private void Save() throws IOException {
        boolean dateInCheck = false;
        boolean dateOutCheck = false;
        boolean staffIdCheck = false;
        boolean vehicleIdCheck = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOutFormat = null;
        Date dateInFormat = null;
        try {
            if (sessionID == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Please search for a valid user before continuing");
                alert.setContentText("You can enter a user ID to search for a user at the top of the page.");
                alert.showAndWait();
            } else if (sessionID >= 0) {

                int staffID = Integer.parseInt(staffIdTextField.getText());
                String vehicleID = vehicleIdTextField.getText();
                String dateOut = dateOutTextField.getText();
                String dateIn = dateInTextField.getText();
                String purpose = borrowingTextField.getText();
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
                if (staffIdCheck == true && vehicleIdCheck == true && dateInCheck == true && dateOutCheck == true) {
                    // Call the constructor to create the instance (may need to pull up extra info from other arrays to populate the call)
                    // Then add the created instance to the array - see similar section in CreateRegisteredUser for working example

                    App.uses.get(sessionID).setStaffID(staffID);
                    App.uses.get(sessionID).setVehicleID(vehicleID);
                    App.uses.get(sessionID).setDateTimeOut(dateOut);
                    App.uses.get(sessionID).setDateTimeIn(dateIn);
                    App.uses.get(sessionID).setPurpose(purpose);

                    DataHandler.writeData(App.uses, "SignOutRecords.ser");
                    // Show success message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Your record was edited.");
                    alert.setContentText("You can edit another record, or use Back to return to the main menu.");
                    alert.showAndWait();

                    // If any checks fail it will display error message and not add to the system
                } else if (staffIdCheck == false || vehicleIdCheck == false) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Unable to save");
                    alert.setHeaderText("Your record was not added. Please ensure that the staffID and vehicleID are correct and already entered into the system.");
                    alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                    alert.showAndWait();

                } else if (dateInCheck == false || dateOutCheck == false) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Unable to save");
                    alert.setHeaderText("Your record was not added. Please ensure that the date in and date out are in the correct formats (dd/mm/yyyy).");
                    alert.setContentText("You can try to enter the record again , or use Back to return to the main menu.");
                    alert.showAndWait();
                }

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Your record could not be added.");
            alert.setContentText("Check that all fields are completed, with numbers where noted and that dates are entered in the correct format (dd/mm/yyyy). \n\nError message: \"" + e + "\".");
            alert.showAndWait();
        }

    }

    //will allow user to edit currently viewed record
    @FXML
    private void Edit() throws IOException {
        if (sessionID == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please search for a valid user before continuing");
            alert.setContentText("You can enter a user ID to search for a user at the top of the page.");
            alert.showAndWait();
        } else if (sessionID >= 0) {
            helpEdit.setVisible(true);
            staffIdTextField.setEditable(true);
            vehicleIdTextField.setEditable(true);
            dateOutTextField.setEditable(true);
            dateInTextField.setEditable(true);
            borrowingTextField.setEditable(true);
        }
    }

    //will delete currently viewed record
    @FXML
    private void Delete() throws IOException {
        if (sessionID == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please search for a valid user before continuing");
            alert.setContentText("You can enter a user ID to search for a user at the top of the page.");
            alert.showAndWait();
        } else if (sessionID >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "WARNING, You are about to delete this car use record. Are you sure?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    App.uses.remove(sessionID);
                    try {
                        DataHandler.writeData(App.uses, "SignOutRecords.ser");
                    } catch (IOException ex) {
                        Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                        deleteAlert.setTitle("Error");
                        deleteAlert.setHeaderText("Record could not be deleted.");
                        deleteAlert.setContentText("Please check that a correct userID was entered. \n\nError message: \"" + ex + "\".");
                        ex.printStackTrace();
                    }
                }
            });
        }
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
