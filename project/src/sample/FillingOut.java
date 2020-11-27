package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FillingOut extends SignIn {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField phonenumber_field;

    @FXML
    private TextField card_field;

    @FXML
    private TextField address_field;


    @FXML
    private Label price_label;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField email_field;

    @FXML
    private Button buy_button;

    @FXML
    private Button back_button;


    boolean correctInput1 = false;
    boolean correctInput2 = false;
    boolean correctInput3 = false;

    protected String card;
    protected String address;
    protected String number;
    protected String email;

    @FXML
    void buy(ActionEvent event) {

        if (correctInput1 && correctInput2 && correctInput3 && !address_field.getText().isEmpty() && !card_field.getText().isEmpty() && !email_field.getText().isEmpty() && !phonenumber_field.getText().isEmpty()) {

            FXMLLoader loader = new FXMLLoader();
            buy_button.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("/sample/Cheque.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } else if (!correctInput1 || !correctInput2 || !correctInput3) {
            errorLabel.setText("INCORRECT INPUT");
        } else {
            errorLabel.setText("ALL FIELDS MUST BE ENTERED");
        }
    }

    @FXML
    void back(ActionEvent event) {
        back_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        if (admin) {
            loader.setLocation(getClass().getResource("/sample/adminsample.fxml"));
        } else {
            loader.setLocation(getClass().getResource("/sample/buyproducts.fxml"));
        }
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
    void initialize() {
        price_label.setText(BuyProducts.totalPr);

        card_field.setOnAction(event -> {
            String txt = card_field.getText();
            String result = "";

            try {
                long isNum = Long.parseLong(card_field.getText());
                if (isNum > 0) {
                    correctInput1 = true;
                    for (int i = 0, j = 1; i < 16; i++, j++) {
                        result += txt.charAt(i);
                        if (j % 4 == 0) result += " ";
                    }
                }
            } catch (Exception e) {
                correctInput1 = false;
                card_field.setText("");
                errorLabel.setText("INCORRECT INPUT");
            }
            card_field.setText(result);
        });


        phonenumber_field.setOnAction(event -> {
            String result = "";
            try {
                long isNum = Long.parseLong(phonenumber_field.getText());
                if (isNum > 0) {
                    correctInput2 = true;
                    for (int i = 0; i < 11; i++) {
                        result+=phonenumber_field.getText().charAt(i);
                        if (i % 3 == 0 && i != 9) result+=" ";
                    }
                    phonenumber_field.setText(result);
                }
            } catch (Exception e) {
                correctInput2 = false;
                phonenumber_field.setText("");
                errorLabel.setText("INCORRECT INPUT");
            }
        });

        email_field.setOnAction(event -> {
            String email = email_field.getText();
            if (email.contains("@")) correctInput3 = true;
            else {
                errorLabel.setText("INCORRECT INPUT");
                email_field.setText("");
            }
        });
    }
}
