package jjdcontracting.finalproject;

//******  Group Project  ******//
// COIT1134 Object Oriented Programming
// Deslea Judd, Student 12211138
// Jack Lear, Student 12207908
// Jenna Katt, Student 12127332
// Term 1 2023

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.IOException;

//TODO Deslea here, I think all unused/ported to other classes, OK to delete after testing
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.ListView;

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