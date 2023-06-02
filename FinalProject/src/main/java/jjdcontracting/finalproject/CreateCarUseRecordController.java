package jjdcontracting.finalproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CreateCarUseRecordController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    //saves the values inputed by user
    @FXML
    private void Save() throws IOException {
        
        //when the user presses the save button, this will get the values from each text field
        
        //String bookingID = this./*textfieldname*/.getText();
        //String staffID = this./*textfieldname*/.getText();
        //String vehicleID = this./*textfieldname*/.getText();
        //String purpose = this./*textfieldname*/.getText();
        
        //adds the information to the arraylist
        
        //SignOutRecord record = new SignOutRecord();
        //App./*userArray*/.add(record);
        
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
