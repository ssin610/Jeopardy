package assignment2ssin610.src;

import java.net.URL;
import java.util.ResourceBundle;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
    Button submit; 
    
    @FXML
	Button goBack; 

    static String text;
    static String answer;
    static int value;

    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        grid.add(new Text(text), 0,1);
        goBack.setDisable(true);

    }

    public void submitButtonPushed(ActionEvent event) throws IOException {
        Main.answeredQuestions.add(text);
        if (userAnswer.getText().equals(answer)){
            Main.balance += value;
            System.out.println(Main.balance);
            
        }
        else {
            Main.balance -= value;
            System.out.println(answer);
        }
        submit.setDisable(true);
        goBack.setDisable(false);
    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }
    
}
