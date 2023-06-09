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

public class ViewAllCarUseRecordsController implements Initializable {

    // **** Set Up Tableview Variables **** //
    
    @FXML private TableView<SignOutRecord> displayUsesTable;
    @FXML private TableColumn<SignOutRecord, String> displaySignOutID;
    @FXML private TableColumn<SignOutRecord, String> displayStaffID;
    @FXML private TableColumn<SignOutRecord, String> displayvehicleID;
    @FXML private TableColumn<SignOutRecord, String> displayDateTimeOut;
    @FXML private TableColumn<SignOutRecord, Integer> displayDateTimeIn;
    @FXML private TableColumn<SignOutRecord, Boolean> displayPurpose;
    @FXML private ObservableList<SignOutRecord> usesListener;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        // Create listener for array
        usesListener = FXCollections.observableArrayList(App.uses);
        // Initialise Users Columns and map to class variables
        displaySignOutID.setCellValueFactory(new PropertyValueFactory<>("signOutID"));
        displayStaffID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        displayvehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        displayDateTimeOut.setCellValueFactory(new PropertyValueFactory<>("dateTimeOut"));
        displayDateTimeIn.setCellValueFactory(new PropertyValueFactory<>("dateTimeIn"));
        displayPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        // Set class source for TableView
        displayUsesTable.setItems(usesListener);
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
