package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.DbDoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile extends BuyProducts {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem buyProducts;

    @FXML
    private MenuItem settings;

    @FXML
    private MenuItem Basket;

    @FXML
    private MenuItem sign_out;

    @FXML
    private Label name;

    @FXML
    private Label card;

    @FXML
    private Button buy;

    @FXML
    private Button edit;

    @FXML
    private Label surname;

    @FXML
    private TableView<Products> products;

    @FXML
    private TableColumn<Products, String> name_table;

    @FXML
    private TableColumn<Products, Double> weight_table;

    @FXML
    private TableColumn<Products, Double> price_table;

    @FXML
    void BasketWindow(ActionEvent event) {
        menu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/basket.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void SettingsWindow(ActionEvent event) {
        menu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/edit.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void Sign_out_menu(ActionEvent event) {
        menu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/signin.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void buyProductsWindow(ActionEvent event) {
        menu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/buyproducts.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void editButton(ActionEvent event) {
        edit.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/edit.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void buyButton(ActionEvent event) {
        cheque_list = products.getItems();
        buy.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fillingout.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void delete(MouseEvent event) {
        products.getItems().remove(products.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(DBManager.staticName);
        surname.setText(DBManager.surname);
        name_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        weight_table.setCellValueFactory(new PropertyValueFactory<>("Count"));
        price_table.setCellValueFactory(new PropertyValueFactory<>("Price"));
        products.setItems(saved_list);
    }
}
