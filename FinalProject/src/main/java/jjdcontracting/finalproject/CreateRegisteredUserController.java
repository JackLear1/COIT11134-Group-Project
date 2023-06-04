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

public class CreateRegisteredUserController implements Initializable {
    
    //captures the values inputed by user
      
    @FXML
    private TextField createuser_Name;
    @FXML
    private TextField createuser_ID;
    @FXML
    private TextField createuser_Ext;
    @FXML
    private TextField createuser_LicNo;
    @FXML
    private TextField createuser_LicExpiry;
    @FXML
    private RadioButton createuser_ManualLicYes;
    @FXML
    private RadioButton createuser_ManualLicNo;
    @FXML
    private RadioButton createuser_BusLicYes;
    @FXML
    private RadioButton createuser_BusLicNo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void Save() throws IOException {
        
        try {
            
            //when the user presses the save button, this will get the values from each text field

            String userName = createuser_Name.getText();
            int userID = Integer.parseInt(createuser_ID.getText());
            int userExt = Integer.parseInt(createuser_Ext.getText());
            String userLicenseNo = createuser_LicNo.getText();
            String userLicenseExpiry = createuser_LicExpiry.getText();
            Boolean userLicensedManual = createuser_ManualLicYes.isSelected();
            Boolean userLicensedBus = createuser_BusLicYes.isSelected();

            //TODO Validations? Probably only need to check for critical blanks. Parseint errors caught already in catch below.

            User newuser = new User(userName, userID, userExt, userLicenseNo, userLicenseExpiry); 
            if(userLicensedManual == true) { newuser.setManualLicense(true);}
            if (userLicensedBus == true) { newuser.setBusLicense(true);}
            App.user.add(newuser);
            
            // test verify 
            System.out.print(newuser.getStaffName());
            System.out.print(newuser.getStaffID());
            System.out.print(newuser.getStaffExt());
            System.out.print(newuser.getLicenseNumber());
            System.out.print(newuser.getLicenseExpiry());
            System.out.print(newuser.isManualLicense());
            System.out.print(newuser.isBusLicense());
            
            
            
            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Your record was added.");
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
        createuser_Name.clear();
        createuser_ID.clear();
        createuser_Ext.clear();
        createuser_LicNo.clear();
        createuser_LicExpiry.clear();
        createuser_ManualLicYes.setSelected(false);
        createuser_ManualLicNo.setSelected(true);
        createuser_BusLicYes.setSelected(false);
        createuser_BusLicNo.setSelected(true);
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
