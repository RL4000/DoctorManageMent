package boundary;

import entity.Consult;
import entity.User;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class ConsultDataIO {

    private static final String CONSULT_SAVE_FILE_NAME = "consults.dat";

    // ******************* Main methods *******************
    /**
     * Read list of consults from binary file
     *
     * @return
     */
    public static ArrayList<Consult> readData() {
        ArrayList<Consult> users = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(CONSULT_SAVE_FILE_NAME));
            users = (ArrayList<Consult>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    /**
     * Write list of consults to file
     *
     * @param users
     */
    public static void writeData(List<Consult> users) {
        try {
            FileOutputStream fos = new FileOutputStream(CONSULT_SAVE_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
