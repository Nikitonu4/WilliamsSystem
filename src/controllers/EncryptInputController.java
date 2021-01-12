package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import crypto.Encryption;
import crypto.KeyGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EncryptInputController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputM;

    @FXML
    private Label paramC;

    @FXML
    private Label paramC1;

    @FXML
    private Label paramC2;

    @FXML
    private Label paramN;

    @FXML
    private Label paramS;

    @FXML
    private Label paramK;

    @FXML
    void decryptInputButton(ActionEvent event) {
        String mText = inputM.getText();
        long M = Long.parseLong(mText);
        KeyGenerator keyGen = new KeyGenerator(M);

        System.out.println(keyGen.toString());

        Encryption encryption = new Encryption(M, keyGen.getN(), keyGen.getS());
        encryption.toString();

        paramC.setText("C:  "+encryption.getC());
        paramC1.setText("C1:  "+encryption.getC1());
        paramC2.setText("C2:  "+encryption.getC2());
        paramN.setText("n:  "+keyGen.getN());
        paramS.setText("s:  "+keyGen.getS());
        paramK.setText("k:  "+keyGen.getK());

    }

    @FXML
    void initialize() {

    }
}


