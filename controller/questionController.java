package controller;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;   
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class questionController implements Initializable {

    @FXML
	GridPane grid;

    @FXML
    TextField userAnswer;   

    @FXML
    Text question;  
    
    @FXML
    Text correct; 

    @FXML
    Text incorrect; 
    
    @FXML
    Button submit; 
    
    @FXML
    Button menuButton; 
    
    @FXML
	Button winnings;

    static String text;
    static String answer;
    static int value;

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        correct.setVisible(false);
        incorrect.setVisible(false);
        winnings.setText("Winnings: $" + Integer.toString(Main.balance));
        question.setText(text);
        menuButton.setVisible(false);
        //ProcessBuilder pb = new ProcessBuilder("festival", "--tts");
       // Process p = null;
       // try {
        //    p = pb.start();
       // } catch (IOException e) {
            // TODO Auto-generated catch block
       //     e.printStackTrace();
      //  }
      //  finally {
            
     //   }
        //OutputStream in = p.getOutputStream();
        //PrintWriter stdin = new PrintWriter(in);
       // stdin.println(question);
       // stdin.close();
        
    }

    public void submitButtonPushed(ActionEvent event) throws IOException {
        Main.answeredQuestions.add(text);
        if (userAnswer.getText().equalsIgnoreCase(answer)){
            Main.balance += value;
            correct.setText("Correct! $" + value + " has been added to your winnings!");
            question.setVisible(false);
            correct.setVisible(true);
        }
        else {
            Main.balance -= value;
            incorrect.setText("Incorrect. The correct answer was: " + answer);
            question.setVisible(false);
            incorrect.setVisible(true);

        }
        winnings.setText("Winnings: $" + Integer.toString(Main.balance));
        submit.setVisible(false);
        menuButton.setVisible(true);
    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }
    
}
