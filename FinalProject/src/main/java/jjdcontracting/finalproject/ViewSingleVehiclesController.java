package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class ViewSingleVehiclesController implements Initializable {

    // Declare fields from the FXML
    @FXML
    private TextField errorMsg;
    @FXML
    private Text helpEdit;
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
        errorMsg.setVisible(false);
        helpEdit.setVisible(false);
        vehicleEdit.setVisible(false);
        vehicleDelete.setVisible(false);
        vehicleSave.setVisible(false);
    }
    
    //will allow user to search through vehicle records
    @FXML
    private void Search() throws IOException {
        
        // set fields to view only
        
        errorMsg.setText("Viewing:");
        errorMsg.setVisible(true);
        helpEdit.setVisible(false);
        vehicleSave.setVisible(false);
        vehicleBack.setVisible(true);
        vehicleEdit.setVisible(true);
        vehicleDelete.setVisible(true);
        
        vehicleID.setEditable(false);
        vehicleMake.setEditable(false);
        vehicleTypePassenger.setDisable(true);
        vehicleTypeBus.setDisable(true);
        vehicleModel.setEditable(false);
        vehicleYear.setEditable(false);
        vehicleCapacity.setEditable(false);
        vehicleTransManual.setDisable(true);
        vehicleTransAuto.setDisable(true);
        vehicleServicedYes.setDisable(true);
        vehicleServicedNo.setDisable(true);
        vehicleAccessibleYes.setDisable(true);
        vehicleAccessibleNo.setDisable(true);
        
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
                    vehicleModel.setText(myCar.getVehicleModel());
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
        Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Edit Confirmation");
            alert.setHeaderText("Are you sure you want to edit?");
            alert.setContentText("This action can't be reversed.");
                
            ButtonType buttonTypeOK = new ButtonType("OK");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            
            Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == buttonTypeOK) {

            String serviced;
            String manual;
            String category;
            if (vehicleServicedYes.isSelected() == true) { serviced = "true"; }
            else {serviced = "false"; }
            if (vehicleTransManual.isSelected() == true) { manual = "true"; }
            else {manual = "false"; }
            if (vehicleTypePassenger.isSelected() == true) { category = "passenger"; }
            else { category = "bus"; }
            String[] myArray = {vehicleMake.getText(), vehicleModel.getText(), vehicleYear.getText(), "true", serviced, category, vehicleCapacity.getText(), manual};
            
            DataHandler.editEntry("vehicle", vehicleID.getText(), myArray);
            
        errorMsg.setText("Edits Saved!");
        errorMsg.setVisible(true);
        helpEdit.setVisible(false);
        vehicleSave.setVisible(false);
        vehicleBack.setVisible(true);
        vehicleEdit.setVisible(true);
        vehicleDelete.setVisible(true);            
        }
        else { alert.close(); }       
    }
    
    //will allow user to edit currently viewed record
    @FXML
    private void Edit() throws IOException {
        errorMsg.setText("Editing:");
        errorMsg.setVisible(true);
        helpEdit.setVisible(true);
        vehicleSave.setVisible(true);
        vehicleBack.setVisible(true);
        vehicleEdit.setVisible(false);
        vehicleDelete.setVisible(true);
        
        
        //ID and type not editable
        vehicleID.setEditable(false);
        vehicleTypePassenger.setDisable(true);
        vehicleTypeBus.setDisable(true);
        
        // Other fields editable
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
        Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Delete Confirmation?");
            alert.setHeaderText("Are you sure you want to delete?");
            alert.setContentText("This action can't be reversed.");
                
            ButtonType buttonTypeOK = new ButtonType("OK");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
            
            Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == buttonTypeOK) { 
            DataHandler.delEntry("vehicle", vehicleID.getText());
            
        errorMsg.setText("Deleted!");
        errorMsg.setVisible(true);
        helpEdit.setVisible(false);
        vehicleSave.setVisible(false);
        vehicleBack.setVisible(true);
        vehicleEdit.setVisible(false);
        vehicleDelete.setVisible(false);
        vehicleID.clear();
        vehicleTypePassenger.setSelected(false);
        vehicleTypeBus.setSelected(false);
        vehicleMake.clear();
        vehicleModel.clear();
        vehicleYear.clear();
        vehicleCapacity.clear();
        vehicleTransManual.setSelected(false);
        vehicleTransAuto.setSelected(false);
        vehicleServicedYes.setSelected(false);
        vehicleServicedNo.setSelected(false);
        vehicleAccessibleYes.setSelected(false);
        vehicleAccessibleNo.setSelected(false);            
        }
        else { alert.close(); }       
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
