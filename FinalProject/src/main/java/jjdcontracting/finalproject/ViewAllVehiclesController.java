package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAllVehiclesController implements Initializable {
    
    // **** Set Up Tableview Variables **** //
    
    // For simplicity, variables specific to sub-types are not shown.
    // The user can get these from the individual vehicle view.
    
    @FXML private TableView<Vehicle> displayVehiclesTable;
    @FXML private TableColumn<Vehicle, String> displayVehiclesCategory;
    @FXML private TableColumn<Vehicle, String> displayVehiclesID;
    @FXML private TableColumn<Vehicle, String> displayVehiclesMake;
    @FXML private TableColumn<Vehicle, String> displayVehiclesModel;
    @FXML private TableColumn<Vehicle, Integer> displayVehiclesYear;
    @FXML private TableColumn<Vehicle, Boolean> displayVehiclesServicedBool;
    @FXML private ObservableList<Vehicle> vehiclesListener;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create listener for array
        vehiclesListener = FXCollections.observableArrayList(App.vehicle);
        // Initialise Users Columns and map to class variables
        displayVehiclesCategory.setCellValueFactory(new PropertyValueFactory<>("vehicleCategory"));
        displayVehiclesID.setCellValueFactory(new PropertyValueFactory<>("vehiclePlate"));
        displayVehiclesMake.setCellValueFactory(new PropertyValueFactory<>("vehicleMake"));
        displayVehiclesModel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        displayVehiclesYear.setCellValueFactory(new PropertyValueFactory<>("vehicleYear"));
        displayVehiclesServicedBool.setCellValueFactory(new PropertyValueFactory<>("serviceUpToDate"));
        // Set class source for TableView
        displayVehiclesTable.setItems(vehiclesListener);
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
