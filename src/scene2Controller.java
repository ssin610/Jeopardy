package assignment2ssin610.src;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;   
import javafx.stage.Stage;

public class scene2Controller {

    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("questionBoard.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }

    public void changeScreenToAskAQuestionButtonPushed(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("askAQuestion.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }

    public void changeScreenToViewWinnings(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("viewWinnings.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }

    public void onReset(ActionEvent event) throws IOException
    {
        Main.balance = 0;
        Main.answeredQuestions.clear();
    }

    public void onExit(ActionEvent event) throws IOException
    {
        Main.balance = 0;
        Main.answeredQuestions.clear();
    }
    
}
