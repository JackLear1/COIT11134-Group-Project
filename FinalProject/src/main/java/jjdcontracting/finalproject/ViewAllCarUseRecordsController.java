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
    
    // TODO: These will not work correctly yet. Once what's to go in these 
    // columns is agreed, you can refactor the displayTBA1 below etc and change 
    // the type in the declarations below as appropriate. Then edit initialize() 
    // below.
    
    @FXML
    private TableView<SignOutRecord> displayUsesTable;
    @FXML
    private TableColumn<SignOutRecord, String> displayTBA1;
    @FXML
    private TableColumn<SignOutRecord, String> displayTBA2;
    @FXML
    private TableColumn<SignOutRecord, String> displayTBA3;
    @FXML
    private TableColumn<SignOutRecord, String> displayTBA4;
    @FXML
    private TableColumn<SignOutRecord, Integer> displayTBA5;
    @FXML
    private TableColumn<SignOutRecord, Boolean> displayTBA6;
    @FXML
    private ObservableList<SignOutRecord> usesListener;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        // TODO: Also needs to be fixed. Refactoring the displayTBAs should fix everything in the code except the marrying up to actual class attributes - do this manually below.
        // TODO: Also need to open the fxml in Scene Builder to change the titles that appear on the columns in the scene.
        
        // Create listener for array
        usesListener = FXCollections.observableArrayList(App.uses);
        // Initialise Users Columns and map to class variables
        displayTBA1.setCellValueFactory(new PropertyValueFactory<>("classAttribute1"));
        displayTBA2.setCellValueFactory(new PropertyValueFactory<>("classAttribute2"));
        displayTBA3.setCellValueFactory(new PropertyValueFactory<>("classAttribute3"));
        displayTBA4.setCellValueFactory(new PropertyValueFactory<>("classAttribute4"));
        displayTBA5.setCellValueFactory(new PropertyValueFactory<>("classAttribute5"));
        displayTBA6.setCellValueFactory(new PropertyValueFactory<>("classAttribute6"));
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
