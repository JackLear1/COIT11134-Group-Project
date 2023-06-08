package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;

public class ViewSingleUsersController implements Initializable {

    @FXML private ToggleGroup drivemanual;
    @FXML private ToggleGroup drivebuses;
    @FXML private TextField staffIdSearch;
    @FXML private TextField staffNameTextField;
    @FXML private TextField staffIdTextField;
    @FXML private TextField staffExtTextField;
    @FXML private TextField licenseNumberTextField;
    @FXML private TextField licenseExpiryTextField;
    @FXML private RadioButton manualYes;
    @FXML private RadioButton manualNo;
    @FXML private RadioButton busYes;
    @FXML private RadioButton busNo;
    @FXML private TextField errorMsg;
    @FXML private Text helpEdit;
    @FXML private Button userSave;
    @FXML private Button userBack;
    @FXML private Button userEdit;
    @FXML private Button userDelete;

    int sessionID = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        setButtonsAndMessages("start");
        setFormViewOnly();
    }

    //will allow user to search through user records
    @FXML
    private void Search() throws IOException {
        try {
        
        int searchKey = Integer.parseInt(staffIdSearch.getText());

        for (int i = 0; i < App.user.size(); i++) {
            if (App.user.get(i).getStaffID() == searchKey) {
                sessionID = i;
                recallInfo(sessionID);
                setButtonsAndMessages("searchresult");
                break;
            } else {
                setButtonsAndMessages("noID");
            }
        }
        } catch (NumberFormatException e) {
                setButtonsAndMessages("needID");
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
                setButtonsAndMessages("editsuccess");
                setFormViewOnly();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("User " + App.user.get(sessionID).getStaffID() + " was edited.");
                alert.setContentText("You can search for another record, or use Back to return to the main menu.");
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
            setButtonsAndMessages("editing");
            setFormEditable();
        }
    }

    @FXML
    private void Delete() throws IOException {
        if (sessionID == -1) {
            // Shouldn't happen now because Delete only shows with an entry, but leaving just in case
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
                        setButtonsAndMessages("deletesuccess");
                        clearForm();
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

    //will take user back to Main Menu
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
    
    @FXML
    private void setFormViewOnly() {
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
    
    @FXML
    private void setFormEditable() {
        //ID not editable
        staffIdTextField.setEditable(false);
        // Other fields editable
        staffNameTextField.setEditable(true);
        staffExtTextField.setEditable(true);
        licenseNumberTextField.setEditable(true);
        licenseExpiryTextField.setEditable(true);
        manualYes.setDisable(false);
        manualNo.setDisable(false);
        busYes.setDisable(false);
        busNo.setDisable(false); 
    }
    
    @FXML
    private void clearForm(){
        staffNameTextField.clear();
        staffIdTextField.clear();
        staffExtTextField.clear();
        licenseNumberTextField.clear();
        licenseExpiryTextField.clear();
        manualYes.setSelected(false);
        manualNo.setSelected(false);
        busYes.setSelected(false);
        busNo.setSelected(false);
    }
    
    @FXML
    private void setButtonsAndMessages(String str) {
        switch (str) {
            case "start":
                errorMsg.setVisible(false);
                helpEdit.setVisible(false);
                userEdit.setVisible(false);
                userDelete.setVisible(false);
                userSave.setVisible(false);
                break;
            case "searchresult":    
                errorMsg.setStyle("-fx-border-color: green;");
                errorMsg.setText("Viewing:");
                errorMsg.setVisible(true);
                userSave.setVisible(false);
                userEdit.setVisible(true);
                userDelete.setVisible(true);
                helpEdit.setVisible(true);
                break;
            case "noID":
                errorMsg.setVisible(true);
                errorMsg.setStyle("-fx-border-color: red;");
                errorMsg.setText("ID not found!");
                break;
            case "needID":
                errorMsg.setVisible(true);
                errorMsg.setStyle("-fx-border-color: red;");
                errorMsg.setText("Please enter an ID!");
                break;
            case "editing":
                errorMsg.setText("Editing:");
                errorMsg.setStyle("-fx-border-color: green;");
                errorMsg.setVisible(true);
                helpEdit.setVisible(true);
                userSave.setVisible(true);
                userBack.setVisible(true);
                userEdit.setVisible(false);
                userDelete.setVisible(true);
                break;
            case "deletesuccess":
                errorMsg.setText("Deleted!");
                errorMsg.setStyle("-fx-border-color: green;");
                errorMsg.setVisible(true);
                helpEdit.setVisible(false);
                userSave.setVisible(false);
                userBack.setVisible(true);
                userEdit.setVisible(false);
                userDelete.setVisible(false);
            case "editsuccess":
                errorMsg.setText("Edits saved!");
                errorMsg.setStyle("-fx-border-color: green;");
                errorMsg.setVisible(true);
                helpEdit.setVisible(true);
                userSave.setVisible(false);
                userBack.setVisible(true);
                userEdit.setVisible(true);
                userDelete.setVisible(true);
        }
    }
}
