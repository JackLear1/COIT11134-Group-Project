package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MainMenuController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    //will take user to CreateCarUseRecord screen
    private void CreateCarUseBtn() throws IOException {
        App.setRoot("CreateCarUseRecord");
    }
    
    //will take user to CreateNewVehicle screen
    private void CreateNewVehicleBtn() throws IOException {
        App.setRoot("CreateNewVehicle");
    }
    
    //will take user to CreateRegisteredUser screen
    private void CreateRegisteredUserBtn() throws IOException {
        App.setRoot("CreateRegisteredUser");
    }
    
    //will take user to ViewAllCarUseRecords screen
    private void ViewAllCarUseRecordsBtn() throws IOException {
        App.setRoot("ViewAllCarUseRecords");
    }
    
    //will take user to ViewAllUsers screen
    private void ViewAllUsersBtn() throws IOException {
        App.setRoot("ViewAllUsers");
    }
    
    //will take user to ViewAllVehicles screen
    private void ViewAllVehiclesBtn() throws IOException {
        App.setRoot("ViewAllVehicles");
    }
    
    //will take user to ViewSingleCarUse screen
    private void ViewSingleCarUseBtn() throws IOException {
        App.setRoot("ViewSingleCarUse");
    }
    
    //will take user to ViewSingleUser screen
    private void ViewSingleUserBtn() throws IOException {
        App.setRoot("ViewSingleUser");
    }
    
    //will take user to ViewSingleVehicle screen
    private void ViewSingleVehicleBtn() throws IOException {
        App.setRoot("ViewSingleVehicle");
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
