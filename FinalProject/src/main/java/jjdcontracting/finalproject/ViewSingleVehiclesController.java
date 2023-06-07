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

public class ViewSingleVehiclesController implements Initializable {

    // Declare fields from the FXML
    @FXML
    private TextField errorMsg;
    @FXML
    private TextField helpEdit;
    @FXML
    private TextField vehicleSearch;
    @FXML
    private TextField vehicleID;
    @FXML
    private RadioButton vehicleTypePassenger;
    @FXML
    private RadioButton vehicleTypeBus;
    @FXML
    private TextField vehicleMake;
    @FXML
    private TextField vehicleModel;
    @FXML
    private TextField vehicleYear;
    @FXML
    private TextField vehicleCapacity;
    @FXML
    private ToggleGroup manualauto;
    @FXML
    private RadioButton vehicleTransManual;
    @FXML
    private RadioButton vehicleTransAuto;
    @FXML
    private ToggleGroup serviceyesno;
    @FXML
    private RadioButton vehicleServicedYes;
    @FXML
    private RadioButton vehicleServicedNo;
    @FXML
    private ToggleGroup accessibleyesno;
    @FXML
    private RadioButton vehicleAccessibleYes;
    @FXML
    private RadioButton vehicleAccessibleNo;
    @FXML
    private Button vehicleSave;
    @FXML
    private Button vehicleBack;
    @FXML
    private Button vehicleEdit;
    @FXML
    private Button vehicleDelete;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        helpEdit.setVisible(false);
        vehicleEdit.setVisible(false);
        vehicleEdit.setVisible(false);
        vehicleSave.setVisible(false);
    }
    
    //will allow user to search through vehicle records
    @FXML
    private void Search() throws IOException {
        
        // error if no search text
        if (vehicleSearch.getText().isEmpty()) {
            errorMsg.setText("Empty string!");
            errorMsg.setVisible(true);
            helpEdit.setVisible(false);
            }
        else {
        
            // error if search term not found
            if (DataHandler.getCar(vehicleSearch.getText()) == null) {
                errorMsg.setText("ID not found!");
                errorMsg.setVisible(true);
                helpEdit.setVisible(false);
                }
            else {
                // Return search result
                // Enable edit and delete buttons
                vehicleSave.setVisible(false);
                vehicleEdit.setVisible(true);
                vehicleDelete.setVisible(true);
                helpEdit.setVisible(true);
                String searchTerm = vehicleSearch.getText();
                Vehicle myCar = DataHandler.getCar(searchTerm);
                errorMsg.setVisible(false); // clear any prior search errors
                    vehicleID.setText(myCar.getVehiclePlate());
                    vehicleMake.setText(myCar.getVehicleMake());
                    vehicleModel.setText(myCar.getVehicleMake());
                    Integer year = myCar.getVehicleYear();
                    vehicleYear.setText(year.toString());
                    if (myCar.isServiceUpToDate()) {
                        vehicleServicedYes.setSelected(true);
                        vehicleServicedNo.setSelected(false);
                    }
                    else {
                        vehicleServicedYes.setSelected(false);
                        vehicleServicedNo.setSelected(true);
                    }
                    if (myCar.getVehicleCategory().equals("Passenger Vehicle")){
                        vehicleTypePassenger.setSelected(true);
                        vehicleTypeBus.setSelected(false);
                        vehicleAccessibleYes.setDisable(true);
                        vehicleAccessibleNo.setDisable(true);
                        // Re-cast as Passenger to get subclass methods
                        PassengerVehicle pv = (PassengerVehicle) myCar;
                        Integer capacity = pv.getPassengerSeats();
                        vehicleCapacity.setText(capacity.toString());
                        if (pv.isManualTransmission()) {
                            vehicleTransManual.setSelected(true);
                            vehicleTransAuto.setSelected(false);
                        }
                        else {
                            vehicleTransManual.setSelected(false);
                            vehicleTransAuto.setSelected(true);
                        }
                    }
                    else {
                        vehicleTypePassenger.setSelected(false);
                        vehicleTypeBus.setSelected(true);
                        vehicleTransManual.setDisable(true);
                        vehicleTransAuto.setDisable(true);
                        vehicleCapacity.setDisable(true);
                        // Re-cast as Bus to get subclass methods
                        Bus b = (Bus) myCar;
                        if (b.isWheelchairAccessible()) {
                            vehicleAccessibleYes.setSelected(true);
                            vehicleAccessibleNo.setSelected(false);
                        }
                        else {
                            vehicleAccessibleYes.setSelected(false);
                            vehicleAccessibleNo.setSelected(true);
                        }

                    }
        
            }
        }
    }
    
    //will save edits that user has made to record
    @FXML
    private void Save() throws IOException {
        // TODO
        return;
        
    }
    
    //will allow user to edit currently viewed record
    @FXML
    private void Edit() throws IOException {
        errorMsg.setText("You are editing!");
        helpEdit.setVisible(true);
        vehicleSave.setVisible(true);
        vehicleBack.setVisible(true);
        vehicleEdit.setVisible(false);
        vehicleDelete.setVisible(true);
        
        vehicleMake.setEditable(true);
        vehicleModel.setEditable(true);
        vehicleYear.setEditable(true);
        vehicleCapacity.setEditable(true);
        vehicleTransManual.setDisable(false);
        vehicleTransAuto.setDisable(false);
        vehicleServicedYes.setDisable(false);
        vehicleServicedNo.setDisable(false);
        vehicleAccessibleYes.setDisable(false);
        vehicleAccessibleNo.setDisable(false);
    }
        
    
    
    //will delete currently viewed record
    @FXML
    private void Delete() throws IOException {
        // TODO
        return;
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
