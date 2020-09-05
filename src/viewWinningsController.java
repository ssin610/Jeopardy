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

public class viewWinningsController implements Initializable {

    @FXML
    Text winnings;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Integer balance = new Integer(Main.balance);
        winnings.setText("Your current winnings are $" + balance.toString());

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
