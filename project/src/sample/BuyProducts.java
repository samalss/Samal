package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuyProducts implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Products> shop_list;

    @FXML
    private TableColumn<Products, String> name_table_shop;

    @FXML
    private TableColumn<Products, Double> count_table_shop;

    @FXML
    private TableColumn<Products, Double> price_table_shop;

    @FXML
    private Button buy;

    @FXML
    protected TableView<Products> list_of_products;

    @FXML
    private TableColumn<Products, String> name_table;

    @FXML
    private TableColumn<Products, String> quality_table;

    @FXML
    private TableColumn<Products, String> date_table;

    @FXML
    private TableColumn<Products, Double> price_table;

    @FXML
    private TableColumn<Products, Double> count_table;


    @FXML
    private Label total_field;

    @FXML
    private Label total_text;

    @FXML
    private Button clearButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button vegetables_button;

    @FXML
    private Label search_label;

    @FXML
    private TextField search;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem my_profile;

    @FXML
    private MenuItem settings;

    @FXML
    private MenuItem korzina;

    @FXML
    private MenuItem sign_out_profile;

    @FXML
    private Button add_button;

    public BuyProducts() {
    }
protected static String totalPr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name_table.setCellValueFactory(new PropertyValueFactory<>("Name"));
        quality_table.setCellValueFactory(new PropertyValueFactory<>("Quality"));
        date_table.setCellValueFactory(new PropertyValueFactory<>("Date"));
        price_table.setCellValueFactory(new PropertyValueFactory<>("Price"));
        count_table.setCellValueFactory(new PropertyValueFactory<>("Count"));

        name_table_shop.setCellValueFactory(new PropertyValueFactory<>("Name"));
        count_table_shop.setCellValueFactory(new PropertyValueFactory<>("Count"));
        price_table_shop.setCellValueFactory(new PropertyValueFactory<>("Price"));
        PackageData packageData = new PackageData("getAllProducts");
        Main.connect(packageData);
        list_of_products.setItems(null);
        list_of_products.setItems(Main.productsObservableList);
    }


    @FXML
    void addProduct(ActionEvent event) {
        add_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/addProducts.fxml"));
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

    private ObservableList data = FXCollections.observableArrayList();
    protected static ObservableList<Products> cheque_list = FXCollections.observableArrayList();
    protected static ObservableList<Products> saved_list = FXCollections.observableArrayList();


    public void total_price(ObservableList datalist) {
        int totalPrice = 0;
        for (int i = 0; i < datalist.size(); i++) {
            totalPrice += shop_list.getItems().get(i).getPrice();
        }
        total_text.setText(String.valueOf(totalPrice));
        totalPr=total_text.getText();
    }

    @FXML
    void buy(ActionEvent event) {
        cheque_list = shop_list.getItems();
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
    public void save(ActionEvent event) {
        saved_list = shop_list.getItems();
    }

    @FXML
    void add(MouseEvent event) {
        try {
            int count = list_of_products.getSelectionModel().getSelectedItem().getCount();
            int price = list_of_products.getSelectionModel().getSelectedItem().getPrice();
            String name = list_of_products.getSelectionModel().getSelectedItem().getName();
            data.add(new Products(name, price, count));
            shop_list.setItems(data);

            cheque_list = shop_list.getItems();
            total_price(shop_list.getItems());
        } catch (NullPointerException e) {

        }
    }

    @FXML
    void clear(ActionEvent event) {
        ObservableList emp = FXCollections.observableArrayList();
        shop_list.setItems(emp);
        data = emp;
        total_price(shop_list.getItems());
    }


    @FXML
    void delete(MouseEvent event) {
        shop_list.getItems().remove(shop_list.getSelectionModel().getSelectedItem());
        total_price(shop_list.getItems());
    }

    @FXML
    void profile(ActionEvent event) {
        buy.getScene().getWindow().hide();
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
    void edit(ActionEvent event) {
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
    void basket(ActionEvent event) {
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

    @FXML
    private void search(KeyEvent event) {
        FilteredList filter = new FilteredList(list_of_products.getItems(), e -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super Products>) (Products product) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (product.getName().contains(newValue)) {
                    return true;
                } else if (product.getQuality().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(list_of_products.comparatorProperty());
        list_of_products.setItems(sort);
    }

    public void sort(ActionEvent event) {
        ArrayList arrayList = new ArrayList(list_of_products.getItems());
        Collections.sort(arrayList, new PrComparator());
        ObservableList<Products> list = FXCollections.observableArrayList(arrayList);
        list_of_products.setItems(list);
    }

}
