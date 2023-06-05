/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Deslea-Dev-VM
 */
public class DataHandler implements Serializable {

    // TODO: I have dropped working serialise IO methods here from my previous project
    // They aren't currently used and can be safely replaced if you have something better.
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
    private static Object readData(String filename) throws IOException, ClassNotFoundException {
        Object contents;
        try ( FileInputStream file = new FileInputStream(filename);  ObjectInputStream in = new ObjectInputStream(file)) {
            contents = (Object) in.readObject();
        }
        return contents;
    }

    // TODO: May also need a method to extract one item from an arraylist and convert it to something (eg ListView) for single-record display
    // If needed, it will be used by the ViewSingle[whatever] Controllers to display a record.
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

    public static <T> ListView<T> convert(ArrayList<T> arrayList) {
        ObservableList<T> observableList = FXCollections.observableArrayList(arrayList);
        ListView<T> listView = new ListView<>(observableList);
        return listView;
    }

}
