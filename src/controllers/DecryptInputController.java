package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import crypto.Decryption;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DecryptInputController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputC;

    @FXML
    private TextField inputC1;

    @FXML
    private TextField inputC2;

    @FXML
    private TextField inputN;

    @FXML
    private TextField inputS;

    @FXML
    private TextField inputK;

    @FXML
    private Label mComplete;

    @FXML
    void decryptInputButton(ActionEvent event) {
        long C = Long.parseLong(inputC.getText());
        long C1 = Long.parseLong(inputC1.getText());
        long C2 = Long.parseLong(inputC2.getText());
        long n = Long.parseLong(inputN.getText());
        long s = Long.parseLong(inputS.getText());
        long k = Long.parseLong(inputK.getText());
        Decryption decryption = new Decryption(C, C1, C2, n, s, k);

        mComplete.setText("Расшифрованное число: " + decryption.getM());
    }

    @FXML
    void initialize() {

    }
}
