package assignment2ssin610.src;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class questionController implements Initializable {

    @FXML
	GridPane grid;

    static String text;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        grid.add(new Text(text), 0,1);

    }
    
}
