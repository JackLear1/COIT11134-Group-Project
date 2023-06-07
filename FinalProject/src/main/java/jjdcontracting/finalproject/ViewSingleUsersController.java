package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ViewSingleUsersController implements Initializable {

    @FXML
    private ToggleGroup drivemanual;
    @FXML
    private ToggleGroup drivebuses;
    @FXML
    private TextField staffIdSearch;
    @FXML
    private TextField staffNameTextField;
    @FXML
    private TextField staffIdTextField;
    @FXML
    private TextField staffExtTextField;
    @FXML
    private TextField licenseNumberTextField;
    @FXML
    private TextField licenseExpiryTextField;
    @FXML
    private RadioButton manualYes;
    @FXML
    private RadioButton manualNo;
    @FXML
    private RadioButton busYes;
    @FXML
    private RadioButton busNo;

    int sessionID = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staffNameTextField.setEditable(false);
        staffIdTextField.setEditable(false);
        staffExtTextField.setEditable(false);
        licenseNumberTextField.setEditable(false);
        licenseExpiryTextField.setEditable(false);
        manualYes.setDisable(true);
        manualNo.setDisable(true);
        busYes.setDisable(true);
        busNo.setDisable(true);
    }

    //will allow user to search through user records
    @FXML
    private void Search() throws IOException {

        int searchKey = Integer.parseInt(staffIdSearch.getText());

        for (int i = 0; i < App.user.size(); i++) {
            if (App.user.get(i).getStaffID() == searchKey) {
                sessionID = i;
                recallInfo(sessionID);
                break;
            } else {
                staffIdSearch.setText("StaffID number not found.");
            }
        }
    }

    //will pull the data from the array using the found sessionID
    private void recallInfo(int id) {
        staffNameTextField.setText(App.user.get(id).getStaffName());
        staffIdTextField.setText(String.valueOf(App.user.get(id).getStaffID()));
        staffExtTextField.setText(String.valueOf(App.user.get(id).getStaffExt()));
        licenseNumberTextField.setText(App.user.get(id).getLicenseNumber());
        licenseExpiryTextField.setText(App.user.get(id).getLicenseExpiry());

        if (App.user.get(id).busLicense == true) {
            manualYes.setSelected(true);
        } else if (App.user.get(id).busLicense == false) {
            manualNo.setSelected(true);
        }

        if (App.user.get(id).manualLicense == true) {
            busYes.setSelected(true);
        } else if (App.user.get(id).manualLicense == false) {
            busNo.setSelected(true);
        }
    }

//will save edits that user has made to record
    @FXML
    private void Save() throws IOException {

        Boolean userLicensedManual = null;
        Boolean userLicensedBus = null;

        try {
            if (sessionID == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Please search for a valid user before continuing");
                alert.setContentText("You can enter a user ID to search for a user at the top of the page.");
                alert.showAndWait();
            } else if (sessionID >= 0) {
                //when the user presses the save button, this will get the values from each text field
                String userName = staffNameTextField.getText();
                int userExt = Integer.parseInt(staffExtTextField.getText());
                String userLicenseNo = licenseNumberTextField.getText();
                String userLicenseExpiry = licenseExpiryTextField.getText();
                if (manualYes.isSelected()) {
                    userLicensedManual = true;
                } else if (manualNo.isSelected()) {
                    userLicensedManual = false;
                }
                if (busYes.isSelected()) {
                    userLicensedBus = true;
                } else if (busNo.isSelected()) {
                    userLicensedBus = false;
                }

                App.user.get(sessionID).setStaffName(userName);
                App.user.get(sessionID).setStaffExt(userExt);
                App.user.get(sessionID).setLicenseNumber(userLicenseNo);
                App.user.get(sessionID).setLicenseExpiry(userLicenseExpiry);
                App.user.get(sessionID).setManualLicense(userLicensedManual);
                App.user.get(sessionID).setBusLicense(userLicensedBus);

                DataHandler.writeData(App.user, "UserRecords.ser");

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("User " + App.user.get(sessionID).getStaffID() + " was edited.");
                alert.setContentText("You can enter another record, or use Back to return to the main menu.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Your record could not edited.");
            alert.setContentText("Check that all fields are completed, with numbers where noted. \n\nError message: \"" + e + "\".");
            alert.showAndWait();
            e.printStackTrace();
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
            staffNameTextField.setEditable(true);
            staffExtTextField.setEditable(true);
            licenseNumberTextField.setEditable(true);
            licenseExpiryTextField.setEditable(true);
            manualYes.setDisable(false);
            manualNo.setDisable(false);
            busYes.setDisable(false);
            busNo.setDisable(false);
        }
    }

    @FXML
    private void Delete() throws IOException {
        if (sessionID == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please search for a valid user before continuing");
            alert.setContentText("You can enter a user ID to search for a user at the top of the page.");
            alert.showAndWait();
        } else if (sessionID >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "WARNING, You are about to delete this user. Are you sure?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    App.user.remove(sessionID);
                    try {
                        DataHandler.writeData(App.user, "UserRecords.ser");
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
