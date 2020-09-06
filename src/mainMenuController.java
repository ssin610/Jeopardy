package assignment2ssin610.src;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;

public class mainMenuController implements Initializable {

    @FXML
    Button winnings;

    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("questionBoard.fxml"));
        Scene viewScene = new Scene(viewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    public void changeScreenToAskAQuestionButtonPushed(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("askAQuestion.fxml"));
        Scene viewScene = new Scene(viewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    public void changeScreenToViewWinnings(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("viewWinnings.fxml"));
        Scene viewScene = new Scene(viewParent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
        window.show();
    }

    public void onReset(ActionEvent event) throws IOException {
        Main.balance = 0;
        Main.answeredQuestions.clear();
        File b = new File("balance");
        b.delete();
        File a = new File("answeredQuestions");
        a.delete();
    }

    public void onExit(ActionEvent event) throws IOException {
        Platform.exit();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        winnings.setText("Winnings: $" + Integer.toString(Main.balance));
    }
    
}
