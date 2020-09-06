package src.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import src.helper.TextFileReader;
import src.helper.TextFileWriter;
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
        Parent root = FXMLLoader.load(getClass().getResource("./home.fxml"));
        primaryStage.setTitle("Jeopardy");
        primaryStage.setScene(new Scene(root, 1000, 671));
        primaryStage.show();
        TextFileReader reader = new TextFileReader();
        File b = new File("balance");
        if (b.exists()){
            balance = Integer.valueOf(reader.read(b).get(0));
        }
        File a = new File("answeredQuestions");
        if (a.exists()){
            answeredQuestions = (ArrayList<String>) reader.read(a);
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void stop() throws IOException {
        System.out.println("Stage is closing");
        TextFileWriter.write("balance", balance, null);   // Save file
        TextFileWriter.write("answeredQuestions", null, answeredQuestions);
    }
}
