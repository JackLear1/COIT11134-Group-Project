package jjdcontracting.finalproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class App extends Application {
          
    // Declare arrays of objects
    public static ArrayList<User> user = new ArrayList<>();
    public static ArrayList<SignOutRecord> uses = new ArrayList<>();
    public static ArrayList<Vehicle> vehicle = new ArrayList<>();
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainMenu"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Methods to load files at program start
        DataHandler.loadUses("SignOutRecords.ser");
        DataHandler.loadVehicles("Vehicle.ser");
        DataHandler.loadUsers("UserRecords.ser");
        launch();
    }

 
}