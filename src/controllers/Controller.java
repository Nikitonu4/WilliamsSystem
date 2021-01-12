package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    void decryptFile() {
        newWindow("decryptFile");
    }

    @FXML
    void decryptInput() {
        newWindow("decryptInput");
    }

    @FXML
    void encryptFile() {
        newWindow("encryptFile");
    }

    @FXML
    void encryptInput() {
        newWindow("encryptInput");
    }

    @FXML
    void initialize() {
    }

    protected void newWindow(String windowName) { //открытие модального окна
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
