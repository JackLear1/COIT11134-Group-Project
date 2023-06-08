package jjdcontracting.finalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
// TODO Deslea here, I think unused, delete after testing
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.control.Alert;

public class DataHandler implements Serializable {

    // TODO: I have dropped working serialise IO methods here from my previous project
    // They aren't currently used and can be safely replaced if you have something better.
        // writeData now in use to save to file.
    
    // Method to write an object to a specified file
    // Modified from a tutorial at https://www.geeksforgeeks.org/serialization-in-java/
    public static void writeData(ArrayList streamArray, String filename) throws IOException {
        try ( FileOutputStream file = new FileOutputStream(filename);  ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(streamArray);
        } catch (IOException e) {
            // TODO/slug. Show error message on GUI.
            System.out.println("IOException is caught from writeData");
        }
    }

    // Method to read an object from a specified file
    // Modified from a tutorial at https://www.geeksforgeeks.org/serialization-in-java/
    
    // Returns content=null if no file, need to decide what to do here 
    // (include starting empty file so never null? Or handle the null downstream?)
    
    private static Object readData(String filename) throws IOException, ClassNotFoundException {
        Object contents;
        try ( FileInputStream file = new FileInputStream(filename);  ObjectInputStream in = new ObjectInputStream(file)) {
            contents = (Object) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            // TODO/slug. Show error message on GUI.
            System.out.println("Exception " +e+ "is caught from writeData");
            contents = null;
        }
        return contents;
    }

    // TODO: May also need a method to extract one item from an arraylist and convert it to something (eg ListView) for single-record display
    // If needed, it will be used by the ViewSingle[whatever] Controllers to display a record.
    
    // Jack here, added these loading methods below to load files at program start. could possibly condense to one method but I am currently focused on functionality over effeciency. could look at later if we have time.
    
    // Loads the SignOutRecords.ser file into the uses array
    
    public static void loadUses(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(fileName);
        if (f.exists() == true) {
            FileInputStream usesIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(usesIn);
            App.uses = (ArrayList<SignOutRecord>) in.readObject();
            in.close();
            usesIn.close();
        } else {
            System.out.println(fileName + " not found");
        }
    }
    // Loads the UserRecords.ser file into the users array
    public static void loadUsers(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(fileName);
        if (f.exists() == true) {
            FileInputStream userIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(userIn);
            App.user = (ArrayList<User>) in.readObject();
            in.close();
            userIn.close();
        } else {
            System.out.println(fileName + " not found");
        }
    }
    // Loads the Vehicle.ser file into the vehicle array
    public static void loadVehicles(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(fileName);
        if (f.exists() == true) {
            FileInputStream vehicleIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(vehicleIn);
            App.vehicle = (ArrayList<Vehicle>) in.readObject();
            in.close();
            vehicleIn.close();
        } else {
            System.out.println(fileName + " not found");
        }
    }
   
    // Methods to take a unique ID and return a matching object 
    public static Vehicle getCar(String id){
        for (Vehicle vehicle : App.vehicle)
            { if (vehicle.getVehiclePlate().equals(id)) { return vehicle;}}
        return null;
    }
    
    public static User getUser(String id){
        for (User user : App.user) 
            { if (user.getStaffID() == Integer.parseInt(id)) { return user; }}
        return null;
    }
    
    public static SignOutRecord getUse(String id){
        for (SignOutRecord use : App.uses) 
            { if (use.getSignOutID() == Integer.parseInt(id)) { return use; } }
        return null;
    }
    
}
