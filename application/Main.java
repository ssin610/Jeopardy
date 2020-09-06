package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import helper.TextFileReader;
import helper.TextFileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static int balance = 0;
    public static ArrayList<String> answeredQuestions = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/home.fxml"));
        primaryStage.setTitle("Jeopardy");
        primaryStage.setScene(new Scene(root, 1000, 671));
        primaryStage.show();
        TextFileReader reader = new TextFileReader();
        File b = new File("balance");
        if (b.exists()) {
            balance = Integer.valueOf(reader.read(b).get(0));
        }
        File a = new File("answeredQuestions");
        if (a.exists()) {
            answeredQuestions = (ArrayList<String>) reader.read(a);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void stop() throws IOException {
        TextFileWriter.write("balance", balance, null); // Save file
        TextFileWriter.write("answeredQuestions", null, answeredQuestions);
    }
}
