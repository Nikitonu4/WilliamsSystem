package controllers;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

import crypto.Decryption;
import crypto.Encryption;
import crypto.KeyGenerator;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class DecryptFileController {
    private Desktop desktop = Desktop.getDesktop();
    private long[] keys = new long[6];
    private boolean flag = true;
    final FileChooser fileChooser = new FileChooser();

    @FXML
    private BorderPane borderPaneFileEnc;

    @FXML
    private Text fileM;

    @FXML
    void decryptFileButton() throws IOException { //нажатие кнопки расшифровать

        Decryption decryption = new Decryption(keys[0], keys[1], keys[2], keys[3], keys[4], keys[5]);
        fileM.setText(decryption.toString());

        //открываем файл для записи расшифрованного числа
        File file = fileChooser.showOpenDialog(borderPaneFileEnc.getScene().getWindow());
        FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
        //записываем расшифрованное число в файл
        writer.write(""+decryption.getM());
        writer.flush(); //закрываем поток
        openFile(file); //открываем файл
    }

    @FXML
    void fileChooseDec() { //открытие файла с зашифрованными данными
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
                String[] str = in.readLine().split(",");
                for (int i = 0; i < 6; i++) {
                    keys[i] = Long.parseLong(str[i]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return keys;
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
