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

public class CreateNewVehicleController implements Initializable {

    //captures the values inputed by user
    @FXML
    private TextField createvehicle_Plate;
    @FXML
    private TextField createvehicle_Make;
    @FXML
    private TextField createvehicle_Model;
    @FXML
    private TextField createvehicle_Year;
    @FXML
    private TextField createvehicle_Seats;
    @FXML
    private RadioButton createvehicle_Passenger;
    @FXML
    private RadioButton createvehicle_Bus;
    @FXML
    private RadioButton createvehicle_Manual;
    @FXML
    private RadioButton createvehicle_Auto;
    @FXML
    private RadioButton createvehicle_ServicingYes;
    @FXML
    private RadioButton createvehicle_ServicingNo;
    @FXML
    private RadioButton createvehicle_WheelchairYes;
    @FXML
    private RadioButton createvehicle_WheelchairNo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void Save() throws IOException {
        //when the user presses the save button, this will get the values from each text field
        
        try {
            String vehicleID = createvehicle_Plate.getText();
            String vehicleMake = createvehicle_Make.getText();
            String vehicleModel = createvehicle_Model.getText();
            int vehicleYear = Integer.parseInt(createvehicle_Year.getText());
            int vehicleSeats = Integer.parseInt(createvehicle_Seats.getText());
            Boolean vehicleTypePass = createvehicle_Passenger.isSelected();
            Boolean vehicleTransManual = createvehicle_Manual.isSelected();
            Boolean vehicleServicingYes = createvehicle_ServicingYes.isSelected();
            Boolean vehicleWheelchairYes = createvehicle_WheelchairYes.isSelected();

            //TODO Validations? Probably only need to check for critical blanks. Parseint errors caught already in catch below.

            if (vehicleTypePass == true) {
                PassengerVehicle newvehicle = new PassengerVehicle(vehicleID, vehicleMake, vehicleModel, vehicleYear, "Passenger Vehicle", true, vehicleSeats, vehicleTransManual);
                newvehicle.setServiceUpToDate(vehicleServicingYes);
                App.vehicle.add(newvehicle);
            }
            
            else { 
                Bus newvehicle = new Bus(vehicleID, vehicleMake, vehicleModel, vehicleYear, "Bus", true, vehicleWheelchairYes); 
                newvehicle.setServiceUpToDate(vehicleServicingYes);
                App.vehicle.add(newvehicle);
            }
            
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Your record was added, and the new vehicle has been recorded as Available. You can edit this in single vehicle view.");
            alert.setContentText("You can enter another record, or use Back to return to the main menu.");
            alert.showAndWait();
            ClearForm();
            
            }
        catch(Exception e) {
            	
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("ERROR");
		alert.setHeaderText("Your record could not be added.");
		alert.setContentText("Check that all fields are completed, with numbers where noted. \n\nError message: \""+e+"\".");
		alert.showAndWait();
            }
    }
    
    @FXML
    private void ClearForm() {
        createvehicle_Plate.clear();
        createvehicle_Make.clear();
        createvehicle_Model.clear();
        createvehicle_Year.clear();
        createvehicle_Seats.clear();
        createvehicle_Passenger.setSelected(true);
        createvehicle_Bus.setSelected(false);
        createvehicle_Manual.setSelected(true);
        createvehicle_Auto.setSelected(false);
        createvehicle_ServicingYes.setSelected(false);
        createvehicle_ServicingNo.setSelected(true);
        createvehicle_WheelchairYes.setSelected(false);
        createvehicle_WheelchairNo.setSelected(true);
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
