package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import helper.TextFileReader;
import helper.TextFileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static int winnings = 0;
    private static ArrayList<String> answeredQuestions = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/home.fxml"));
        primaryStage.setTitle("Jeopardy");
        primaryStage.setScene(new Scene(root, 1000, 671));
        primaryStage.setResizable(false);
        primaryStage.show();
        TextFileReader reader = new TextFileReader();
        File w = new File("winnings");
        if (w.exists()) {
            winnings = Integer.valueOf(reader.read(w).get(0)); // if winnings have previously been saved, read from this
                                                               // file
        }
        File a = new File("answeredQuestions");
        if (a.exists()) {
            answeredQuestions = (ArrayList<String>) reader.read(a); // if answered questions have previously been saved,
                                                                    // read from this file
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws IOException {
        TextFileWriter.write("winnings", winnings, null); // save fields in files
        TextFileWriter.write("answeredQuestions", null, answeredQuestions);
    }

    public static int getWinnings() {
        return winnings;
    }

    public static ArrayList<String> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public static void setWinnings(int value) {
        if (value == 0) {
            winnings = 0;
        }
        winnings += value;
    }

    public static void addAnsweredQuestion(String question) {
        if (question == null) {
            answeredQuestions.clear();
        } else {
            answeredQuestions.add(question);
        }
    }
}