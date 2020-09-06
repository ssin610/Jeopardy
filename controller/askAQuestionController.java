package controller;

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

import application.Main;
import helper.TextFileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class askAQuestionController implements Initializable {
    int index_y = 0;
    int index_x = 0;
    int counter2 = 0;
    @FXML
    GridPane button_grid;

    @FXML
    Button winnings;

    @FXML
    Button reset;

    @FXML
    Text resetText;

    public void initialize(URL url, ResourceBundle rb) {
        resetText.setVisible(false);
        reset.setVisible(false);
        winnings.setText("Winnings: $" + Integer.toString(Main.balance));
        File dir = new File("./categories");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                int counter = 0;
                index_y = 0;
                Text category = new Text(child.getName());
				category.setFont(Font.font("Agency FB", 45));
				category.setFill(Color.LIGHTSKYBLUE);
                button_grid.add(category, index_x, index_y);
				button_grid.setHalignment(category, HPos.CENTER);
                Future<List<String>> future;
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                TextFileReader reader = new TextFileReader();
                future = executorService.submit(new Callable<List<String>>() {
                    public List<String> call() throws Exception {
                        return reader.read(child);
                    }
                });

                List<String> lines = null;
                try {
                    lines = future.get();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                executorService.shutdownNow();

                for (String line : lines) {
                    
                    String question = line.split("\\,")[1];
                    String answer = line.split("\\,")[2];
                    answer = answer.trim(); // remove leading space from answer
                    line = line.split("\\,")[0];
                    if (!(Main.answeredQuestions.contains(question))) {
                        counter++;
                        index_y++;
                        addButton(line, question, answer);
                    }
                }
                if (counter == 0) {
                    Text complete = new Text("Category complete!");
                    complete.setFont(Font.font("Agency FB", 29));
                    complete.setFill(Color.LIGHTGREEN);
                    complete.setWrappingWidth(150);
                    button_grid.add(complete, index_x, 1);
                    button_grid.setHalignment(complete, HPos.CENTER);
                    counter2++;
                }
                index_x++;
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
            System.out.println("S");
        }
        if (counter2 == directoryListing.length) {
            resetText.setVisible(true);
            reset.setVisible(true);
        }
    }

    public void addButton(String text, String question, String answer){
        Button sound_button = new Button("$" + text);
        sound_button.setPrefSize(250, 80);
		sound_button.setFont(Font.font("Agency FB", 40));
		sound_button.setStyle("-fx-background-color: #ffc100; ");
		
        sound_button.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    questionController.text = question;
                    questionController.answer = answer;
                    questionController.value = Integer.valueOf(text);
                    changeScreenToQuestionButtonPushed(event);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        button_grid.add(sound_button, index_x,index_y);
        button_grid.setHalignment(sound_button, HPos.CENTER);
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

    public void changeScreenToQuestionButtonPushed(ActionEvent event) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("question.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }

    public void noCategoriesAvailable(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("reset.fxml"));
        Scene viewScene = new Scene(viewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(viewScene);
        window.show();
    }

}