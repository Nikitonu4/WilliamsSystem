package controllers;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import crypto.Encryption;
import crypto.KeyGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class EncryptFileController {
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPaneFileEnc;

    @FXML
    private Text fileM;

    @FXML
    void decryptFileButton(ActionEvent event) {

    }

    @FXML
    void fileChooseEnc(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(borderPaneFileEnc.getScene().getWindow());
        if (file != null) {
//            openFile(file);
            List<File> files = Arrays.asList(file);
            printLog(files);
        }

    }

    private void printLog(List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            System.out.println(file.getAbsolutePath() + "\n");
        }
        int[] array = null;

        for (File file : files) {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                array = in.lines().mapToInt(Integer::parseInt).toArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileM.setText("Шифрование числа: "+array[0]);
        encryptFile(array[0]);

//        for (File file : files) {
//            System.out.println(file.);
//        }

//        for(File file : files){
//            fileM.setText("Число для шифрования: " + file.toString());
//        }
    }

    private void encryptFile(int M){
        KeyGenerator keyGen = new KeyGenerator(M);

        System.out.println(keyGen.toString());

        Encryption encryption = new Encryption(M, keyGen.getN(), keyGen.getS());
        encryption.toString();
    }

    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

    }
}
