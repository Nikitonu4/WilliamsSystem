import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();


//        double decryptionText = decryption(send, keys[2], keys[3], keys[4]);
//        System.out.println("РАСШИФРОВАННОЕ СООБЩЕНИЕ: " + decryptionText);
//        System.out.println("OK");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
