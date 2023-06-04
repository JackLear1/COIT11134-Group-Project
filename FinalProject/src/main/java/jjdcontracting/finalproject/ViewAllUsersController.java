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

public class ViewAllUsersController implements Initializable {
    
    // **** Set Up Tableview Variables **** //
    @FXML
    private TableView<User> displayUsersTable;
    @FXML
    private TableColumn<User, String> displayUsersName;
    @FXML
    private TableColumn<User, Integer> displayUsersID;
    @FXML
    private TableColumn<User, Integer> displayUsersExt;
    @FXML
    private TableColumn<User, String> displayUsersLicNo;
    @FXML
    private TableColumn<User, String> displayUsersLicExpiry;
    @FXML
    private TableColumn<User, Boolean> displayUsersManualBool;
    @FXML
    private TableColumn<User, Boolean> displayUsersBusBool;
    private ObservableList<User> usersListener;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create listener for array
        usersListener = FXCollections.observableArrayList(App.user);
        // Initialise Users Columns and map to class variables
        displayUsersName.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        displayUsersID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        displayUsersExt.setCellValueFactory(new PropertyValueFactory<>("staffExt"));
        displayUsersLicNo.setCellValueFactory(new PropertyValueFactory<>("licenseNumber"));
        displayUsersLicExpiry.setCellValueFactory(new PropertyValueFactory<>("licenseExpiry"));
        displayUsersManualBool.setCellValueFactory(new PropertyValueFactory<>("manualLicense"));
        displayUsersBusBool.setCellValueFactory(new PropertyValueFactory<>("busLicense"));
        // Set class source for TableView
        displayUsersTable.setItems(usersListener);
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
