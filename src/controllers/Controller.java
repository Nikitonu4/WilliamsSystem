package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import crypto.KeyGenerator;
import crypto.MathsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void decrypt(ActionEvent event) {

    }

    @FXML
    void encrypt(ActionEvent event) {

        System.out.println("Введите число для шифра: ");
        Scanner sc = new Scanner(System.in);
        long M = sc.nextLong();
        KeyGenerator keyGen = new KeyGenerator(M);
        System.out.println(keyGen.toString());

//        long[] send = encryption(M, keys[2], keys[3]);
//        System.out.println("Зашифровано! Криптограмма: " + send[0]);
    }

    @FXML
    void initialize() {
    }
}
