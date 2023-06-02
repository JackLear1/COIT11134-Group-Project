package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ViewSingleCarUsesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    //will allow user to search through car use records
    private void Search() throws IOException {
        
    }
    
    //will save edits that user has made to record
    private void Save() throws IOException {
        
    }
    
    //will allow user to edit currently viewed record
    private void Edit() throws IOException {
        
    }
    
    //will delete currently viewed record
    private void Delete() throws IOException {
        
    }
    
    //will take user back to MainMenu
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
