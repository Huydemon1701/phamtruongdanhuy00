package LibraryManagementSystem;
import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void saveLibrary(Library library, String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(new ArrayList<>(library.getAllBooks()));
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Book> loadLibrary(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        ArrayList<Book> list = (ArrayList<Book>) ois.readObject();
        ois.close();
        return list;
    }
}

