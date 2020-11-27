package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {
    static ObservableList<Products> productsObservableList;
static boolean isUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
        primaryStage.setTitle("Online Fruits & Vagetables");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }

    public static void connect(PackageData pd) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            if (pd.getOperationType().equals("ADDUser")) {
                outputStream.writeObject(pd);/// ADD, Student
            } else if (pd.getOperationType().equals("ADDProduct")) {
                outputStream.writeObject(pd);/// ADD, Product
            } else if (pd.getOperationType().equals("getAllProducts")) {
                DBManager manager = new DBManager();
                ArrayList<Products> arrayListFromServer = manager.getAllProducts();
                productsObservableList = FXCollections.observableArrayList(arrayListFromServer);
            } else if (pd.getOperationType().equalsIgnoreCase("LogIn")) {
                DBManager manager = new DBManager();
                User user = pd.getUser();
                isUser   = manager.Authorization(user.getUserName(), user.getPassword());
            }else if (pd.getOperationType().equalsIgnoreCase("Edit")) {
                DBManager manager = new DBManager();
                manager.Edit(pd.getName(), pd.getSurname(), pd.getPassword());
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("No connection");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
