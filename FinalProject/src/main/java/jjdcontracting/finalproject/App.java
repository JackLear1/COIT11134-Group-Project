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

public class App extends Application implements Serializable {
          
    // Declare arrays of objects
    public static ArrayList<User> user = new ArrayList<>();
    public static ArrayList<SignOutRecord> uses = new ArrayList<>();
    public static ArrayList<Vehicle> vehicle = new ArrayList<>();
    
    // **** Data Conversion and I/O Methods ****
    
    // Method to write an object to a specified file
    // Modified from a tutorial at https://www.geeksforgeeks.org/serialization-in-java/
    
    public static void writeData(ArrayList streamArray, String filename) throws IOException {
        try (FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file)) {
                out.writeObject(streamArray);
            }
        catch(IOException e) {
            // TODO/slug. Show error message on GUI.
            System.out.println("IOException is caught from writeData");
            }
    }
    
    // Method to read an object from a specified file
    // Modified from a tutorial at https://www.geeksforgeeks.org/serialization-in-java/

    private static Object readData(String filename) throws IOException, ClassNotFoundException {
        Object contents;
        try (FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file)) {
            contents = (Object)in.readObject();
            }
        return contents;
    }
     
    // Method to read an arraylist and convert it to listview for display - called by FX Controllers
    
    public static <T> ListView<T> convert(ArrayList<T> arrayList) {
        ObservableList<T> observableList = FXCollections.observableArrayList(arrayList);
        ListView<T> listView = new ListView<>(observableList);
        return listView;
    }
       
    // **** Display Methods ****
    
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

    public static void main(String[] args) {
        launch();
    }

 
}