import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Game of Focus minor application release
// For COMP 2005, Fall 2021
// Group 7
public class Focus extends JFrame implements Serializable {
    public Focus() {
      StartWindow newStartWindow = new StartWindow();
    }

  public static void main(String[] args) {

    /**
     * below is how i plan on saving and loading our files
     * still a work in progress, yet to incorporate into the game
     * system, but it works.
     * I am more concerned on getting our game to actually work right now
     */
    Focus object = new Focus();
    /*String filename = "file.ser";

    try {
      FileOutputStream file = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(file);

      out.writeObject(object);
      out.close();
      file.close();
      System.out.println("Object has been serialized");
    }

    catch(IOException ex) {
      System.out.println("IOException has been cuaght");
    }

    // to load a saved file
    Focus loadedObject = null;
    try {
      FileInputStream file  = new FileInputStream(filename);
      ObjectInputStream in = new ObjectInputStream(file);

      loadedObject = (Focus)in.readObject();
      in.close();
      file.close();
      System.out.println("Object has been deserialized.");
    }

    catch(IOException ex) {
      System.out.println("IOException is caught");
    }

    catch(ClassNotFoundException ex) {
      System.out.println("ClaasNotFoundException is caught");
    }*/

  }
}
