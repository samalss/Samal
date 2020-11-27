package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Basket extends BuyProducts implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label shoppingBasketLabel;

    @FXML
    private Button buy;

    @FXML
    private Label total;

    @FXML
    private TableView<Products> products;

    @FXML
    private TableColumn<Products, String> name_table;

    @FXML
    private TableColumn<Products, Double> weight_table;

    @FXML
    private TableColumn<Products, Double> price_table;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem my_profile;

    @FXML
    private MenuItem settings;

    @FXML
    private MenuItem buy_products;

    @FXML
    private MenuItem sign_out_profile;

    @FXML
    void buy(ActionEvent event) {
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
    void buy_button(ActionEvent event) {
        cheque_list = null;
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
    void profile(ActionEvent event) {
        menu.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/profile.fxml"));

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
    void settings(ActionEvent event) {

    }

    @FXML
    void sign_out_menu(ActionEvent event) {
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
    public void total_price(ObservableList datalist) {
        int totalPrice = 0;
        for (int i = 0; i < datalist.size(); i++) {
            totalPrice += products.getItems().get(i).getPrice();
        }
        total.setText("  " + String.valueOf(totalPrice));
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        name_table.setCellValueFactory(new PropertyValueFactory("Name"));
        weight_table.setCellValueFactory(new PropertyValueFactory("Count"));
        price_table.setCellValueFactory(new PropertyValueFactory("Price"));
        products.setItems(saved_list);
        total_price(saved_list);
    }
}
