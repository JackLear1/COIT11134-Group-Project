package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ViewSingleUsersController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //will allow user to search through user records
    @FXML
    private void Search() throws IOException {

        int staffID = Integer.parseInt(staffIdTextField.getText());

        for (int i = 0; i < App.user.size(); i++) {
            if (App.user.get(i).getStaffID() == staffID) {

                int foundID = i;
                recallInfo(foundID);
                break;
            } else {
                staffIdTextField.setText("StaffID number not found.");
            }
        }
    }

    private void recallInfo(int id) {
        staffNameTextField.setText(App.user.get(id).getStaffName());
        staffIdTextField.setText(App.user.get(id).getStaffID());
        staffExtTextField.setText(App.user.get(id).getStaffExt());
        licenseNumberTextField.setText(App.user.get(id).getLicenseNumber());
        licenseExpiryTextField.setText(App.user.get(id).getLicenseExpiry());
        manualLicenseTextField.setText(App.user.get(id).getManualLicense());
        busLicenseTextField.setText(App.user.get(id).getBusLicense());
    }

//will save edits that user has made to record
    @FXML
    private void Save() throws IOException {

    }

    //will allow user to edit currently viewed record
    @FXML
    private void edit() throws IOException {

        staffID selectedStaffId = tableView.getSelectionModel().getSelectedItem();
        if (selectedStaffId != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUserDialog.fxml"));
            Parent root = loader.load();
            EditUserDialogController controller = loader.getController();
            controller.setStaffId(selectedStaffId);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            tableView.refresh();
        }
    }

    //will delete currently viewed record
    @FXML
    private void Delete() throws IOException {

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
