package controllers;

import crypto.Encryption;
import crypto.KeyGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.text.ParseException;

public class EncryptInputController {

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
    private Text error;

    @FXML
    void decryptInputButton() {
        String mText = inputM.getText();
        long M;
        M = Long.parseLong(mText); //шифруемое число
        KeyGenerator keyGen = new KeyGenerator(M);

        if (M < 0) {
            error.setText("Число должно быть положительным");
        } else {
            System.out.println(keyGen.toString());

            Encryption encryption = new Encryption(M, keyGen.getN(), keyGen.getS());
            System.out.println(encryption.toString());

            paramC.setText("C:  " + encryption.getC());
            paramC1.setText("C1:  " + encryption.getC1());
            paramC2.setText("C2:  " + encryption.getC2());
            paramN.setText("n:  " + keyGen.getN());
            paramS.setText("s:  " + keyGen.getS());
            paramK.setText("k:  " + keyGen.getK());
        }
    }

    @FXML
    void initialize() {

    }
}


