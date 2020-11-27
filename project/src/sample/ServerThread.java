package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServerThread extends Thread {
    private Socket socket;
    static String FirstName;
    static String LastName;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try{
            DBManager manager = new DBManager();
            manager.connect();

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            PackageData packageData = null;

            while ((packageData = (PackageData) inputStream.readObject()) != null) {
                    if (packageData.getOperationType().equals("ADDUser")) {/// "ADD", Student
                        User userFromClient = packageData.getUser();
                        manager.addUser(userFromClient);
                    } else if (packageData.getOperationType().equals("ADDProduct")) {/// "ADD", Student
                        Products productFromClient = packageData.getProduct();
                        manager.AddProducts(productFromClient);
                    } else if (packageData.getOperationType().equals("getAllProducts")) {
                        ArrayList<Products> infoForClient = manager.getAllProducts();
                        PackageData toClient = new PackageData(infoForClient);
                        outputStream.writeObject(toClient);
                    } else if (packageData.getOperationType().equals("LogIn")) {
                        outputStream.writeObject(packageData);
                    }
                    else if (packageData.getOperationType().equals("Edit")) {
                        outputStream.writeObject(packageData);
                    }
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch(EOFException e) {
            //eof - no error in this case
        }
        catch(IOException e) {
            //something went wrong
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
