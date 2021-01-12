package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import crypto.KeyGenerator;
import crypto.MathsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void decryptFile(ActionEvent event) {
    }

    @FXML
    void decryptInput(ActionEvent event) {
        newWindow("decryptInput");
    }

    @FXML
    void encryptFile(ActionEvent event) {
        newWindow("encryptFile");
    }

    @FXML
    void encryptInput(ActionEvent event) {
        newWindow("encryptInput");
    }

    @FXML
    void initialize() {
    }

    protected void newWindow(String windowName) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/" + windowName + ".fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
