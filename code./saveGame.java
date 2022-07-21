import java.lang.String;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class saveGame extends Focus {
    public saveGame() {

    }

    public static void saveToFile(ObjectOutputStream out) throws IOException {
        Focus object = new Focus();
        out.writeObject(object);
        out.close();
    }

    public static Focus readFile() throws IOException {
        Focus loadedFocus = null;
        String filename = "file.ser";
        try {
            FileInputStream file  = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            loadedFocus = (Focus)in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized.");
        }
        catch(IOException ex) {
            System.out.println("IOException is caught");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("ClaasNotFoundException is caught");
            ex.printStackTrace();
        }
        System.out.println("Loaded previous game");
        return loadedFocus;
    }
}