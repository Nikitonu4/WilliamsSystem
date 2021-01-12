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
    private int[] array = null;
    private boolean flag = true;
    final FileChooser fileChooser = new FileChooser();

    @FXML
    private BorderPane borderPaneFileEnc;

    @FXML
    private Text fileM;

    @FXML
    void encryptFileButton() throws IOException { //нажатие кнопки зашифровать

        //генерация ключей
        KeyGenerator keyGen = new KeyGenerator(array[0]);
        System.out.println(keyGen.toString());

        //шифрование
        Encryption encryption = new Encryption(array[0], keyGen.getN(), keyGen.getS());
        System.out.println(encryption.toString());

        //открываем файл для записи зашифрованных данных
        File file = fileChooser.showOpenDialog(borderPaneFileEnc.getScene().getWindow());
        FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
        //записываем данные в файл
        writer.write(encryption.getC() + "," + encryption.getC1() + "," + encryption.getC2() +
                "," + keyGen.getN() + "," + keyGen.getS() + "," + keyGen.getK()+
                "\n"+"(C, C1, C2, n, s, k)");
        writer.flush(); //закрываем поток
        openFile(file); //открываем файл
    }

    @FXML
    void fileChooseEnc() {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(borderPaneFileEnc.getScene().getWindow());
        if (file != null) {
            List<File> files = Arrays.asList(file);
            printLog(files);
        }
    }

    private void printLog(List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }

        for (File file : files) {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                array = in.lines().mapToInt(Integer::parseInt).toArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ((int) (Math.log10(array[0]) + 1) > 5) { //сколько цифр в числе
            flag = false;
            fileM.setText("Надо меньше 6 цифр!");
        } else {
            fileM.setText("Шифрование числа: " + array[0]);
        }
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
