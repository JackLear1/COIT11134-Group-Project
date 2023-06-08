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
    @FXML private TextField errorMsg;
    @FXML private Text helpEdit;
    @FXML private TextField vehicleSearch;
    @FXML private TextField vehicleID;
    @FXML private RadioButton vehicleTypePassenger;
    @FXML private RadioButton vehicleTypeBus;
    @FXML private TextField vehicleMake;
    @FXML private TextField vehicleModel;
    @FXML private TextField vehicleYear;
    @FXML private TextField vehicleCapacity;
    @FXML private ToggleGroup manualauto;
    @FXML private RadioButton vehicleTransManual;
    @FXML private RadioButton vehicleTransAuto;
    @FXML private ToggleGroup serviceyesno;
    @FXML private RadioButton vehicleServicedYes;
    @FXML private RadioButton vehicleServicedNo;
    @FXML private ToggleGroup accessibleyesno;
    @FXML private RadioButton vehicleAccessibleYes;
    @FXML private RadioButton vehicleAccessibleNo;
    @FXML private Button vehicleSave;
    @FXML private Button vehicleBack;
    @FXML private Button vehicleEdit;
    @FXML private Button vehicleDelete;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setButtonsAndMessages("start");
    }
    
    //will allow user to search through vehicle records
    @FXML
    private void Search() throws IOException {
        
        // error and quit if no search text
        if (vehicleSearch.getText().isEmpty()) {
            setButtonsAndMessages("needID");
            return;
        }
        // error and quit if search term not found
        if (DataHandler.getCar(vehicleSearch.getText()) == null) {
            setButtonsAndMessages("noID");
            return;
        }
        
        // Return search result
        setButtonsAndMessages("searchresult");
        setFormViewOnly();
        
        String searchTerm = vehicleSearch.getText();
        Vehicle myCar = DataHandler.getCar(searchTerm);
        vehicleID.setText(myCar.getVehiclePlate());
        vehicleMake.setText(myCar.getVehicleMake());
        vehicleModel.setText(myCar.getVehicleModel());
        Integer year = myCar.getVehicleYear();
        vehicleYear.setText(year.toString());
        if (myCar.isServiceUpToDate()) {
            vehicleServicedYes.setSelected(true);
            vehicleServicedNo.setSelected(false);
        } else {
            vehicleServicedYes.setSelected(false);
            vehicleServicedNo.setSelected(true);
        }
        if (myCar.getVehicleCategory().equals("Passenger Vehicle")) {
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
            } else {
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
            } else {
                vehicleAccessibleYes.setSelected(false);
                vehicleAccessibleNo.setSelected(true);
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

            int year = Integer.parseInt(vehicleYear.getText());
            Boolean serviced = Boolean.valueOf(vehicleServicedYes.getText());
            String category;

            if (vehicleTypePassenger.isSelected() == true) { category = "passenger"; }
            else { category = "bus"; }
            
            PassengerVehicle pv = null;
            Bus bus = null;
            Vehicle foundVehicle = null;
            
            for (Vehicle vehicle: App.vehicle){
                if (vehicle.getVehiclePlate().equals(vehicleID.getText())) { 
                    foundVehicle = vehicle;
                    break;
                }
            }
            
            // Make updates using appropriate subclass method
            if (vehicleTypePassenger.isSelected() == true) {
                int seats = Integer.parseInt(vehicleCapacity.getText());
                Boolean manual = Boolean.valueOf(vehicleTransManual.getText());
                pv = (PassengerVehicle) foundVehicle;
                pv.setPassengerVehicleMultiple(vehicleMake.getText(), vehicleModel.getText(), year, (Boolean) true, serviced, seats, manual);
            }
            
            if (vehicleTypeBus.isSelected() == true) {
                Boolean accessible = Boolean.valueOf(vehicleAccessibleYes.getText());
                bus = (Bus) foundVehicle;
                bus.setBusMultiple(vehicleMake.getText(), vehicleModel.getText(), year, (Boolean) true, serviced, accessible);
            }
            
            // Write to file
            try {
                DataHandler.writeData(App.vehicle, "Vehicle.ser");
                
                setButtonsAndMessages("editsuccess");
                setFormViewOnly();

                // Show success message
                Alert alertResult = new Alert(Alert.AlertType.INFORMATION);
                alertResult.setTitle("Success!");
                alertResult.setHeaderText("Vehicle " + vehicleID.getText() + " was edited.");
                alertResult.setContentText("You can search for another record, or use Back to return to the main menu.");
                alertResult.showAndWait();
            
            } catch (IOException ex) {
                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Error");
                deleteAlert.setHeaderText("Record could not be edited.");
                deleteAlert.setContentText("Please check that a correct vehicleID was entered. \n\nError message: \"" + ex + "\".");
                ex.printStackTrace();
            }
   
        }
        
        // Case if user hits cancel
        else { alert.close(); }       
    }
    
    //will allow user to edit currently viewed record
    @FXML
    private void Edit() throws IOException {
        setButtonsAndMessages("editing");        
        setFormEditable();
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
            
            Vehicle foundVehicle = null;
            for (Vehicle vehicle: App.vehicle){
                if (vehicle.getVehiclePlate().equals(vehicleID.getText())) { 
                        foundVehicle = vehicle; 
                        break;
                    }
                }
            if (foundVehicle != null) {
                App.vehicle.remove(foundVehicle);
            }
            
            // Write to file and return screen to view mode
            try {
                DataHandler.writeData(App.vehicle, "Vehicle.ser");
                setButtonsAndMessages("deletesuccess");
                setFormClear();
                
            } catch (IOException ex) {
                Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION);
                deleteAlert.setTitle("Error");
                deleteAlert.setHeaderText("Record could not be deleted.");
                deleteAlert.setContentText("Please check that a correct vehicleID was entered. \n\nError message: \"" + ex + "\".");
                ex.printStackTrace();
            } 
             
        }
        
        // Case if user hits cancel
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
    
    @FXML
    private void setFormViewOnly() {
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
    }
    
    @FXML
    private void setFormEditable() {
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
    
    @FXML 
    private void setFormClear() {
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
    
    @FXML
    private void setButtonsAndMessages(String str) {
        switch (str) {
                case "start":
                    errorMsg.setVisible(false);
                    helpEdit.setVisible(false);
                    vehicleEdit.setVisible(false);
                    vehicleDelete.setVisible(false);
                    vehicleSave.setVisible(false);
                    break;
                case "searchresult":
                    errorMsg.setText("Viewing:");
                    errorMsg.setStyle("-fx-border-color: green;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(true);
                    vehicleSave.setVisible(false);
                    vehicleBack.setVisible(true);
                    vehicleEdit.setVisible(true);
                    vehicleDelete.setVisible(true);
                    break;
                case "needID":
                    errorMsg.setText("Please enter an ID!");
                    errorMsg.setStyle("-fx-border-color: red;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(false);
                    break;
                case "noID":
                    errorMsg.setText("ID not found!");
                    errorMsg.setStyle("-fx-border-color: red;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(false);
                    break;
                case "editsuccess":
                    errorMsg.setText("Edits saved!");
                    errorMsg.setStyle("-fx-border-color: green;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(true);
                    vehicleSave.setVisible(false);
                    vehicleBack.setVisible(true);
                    vehicleEdit.setVisible(true);
                    vehicleDelete.setVisible(true);
                    break;
                case "editing":
                    errorMsg.setText("Editing:");
                    errorMsg.setStyle("-fx-border-color: green;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(true);
                    vehicleSave.setVisible(true);
                    vehicleBack.setVisible(true);
                    vehicleEdit.setVisible(false);
                    vehicleDelete.setVisible(true);
                    break;
                case "deletesuccess":
                    errorMsg.setText("Deleted!");
                    errorMsg.setStyle("-fx-border-color: green;");
                    errorMsg.setVisible(true);
                    helpEdit.setVisible(false);
                    vehicleSave.setVisible(false);
                    vehicleBack.setVisible(true);
                    vehicleEdit.setVisible(false);
                    vehicleDelete.setVisible(false);
                    break;
        }
    }
    
}
