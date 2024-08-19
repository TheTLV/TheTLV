package DataLayer;

import BussinessLayer.Entity.Event;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager extends ArrayList<Event> {

    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public static void saveToFile(String fName, ArrayList<Event> events) {
        try {

            File file = new File(fName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream fo = new ObjectOutputStream(f);

            for (Event e : events) {
                fo.writeObject(e);
            }
            System.out.println("Saved.");
            fo.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile(String fName, ArrayList<Event> events) {

        if (events.size() > 0) {
            events.clear();
        }
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Event e;
            while ((e = (Event) (fo.readObject())) != null) {
                events.add(e);
            }
            fo.close();
            fi.close();
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
