package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import application.Main;
import helper.TextFileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class askAQuestionController implements Initializable {

    @FXML
    GridPane grid;

    @FXML
    Button winnings;

    @FXML
    Button reset;

    @FXML
    Text resetText;

    // track the x and y positions within the gridpane
    int index_y = 0;
    int index_x = 0;

    // track the progress of the game
    int completedCategoryCounter = 0;

    public void initialize(URL url, ResourceBundle rb) {
        resetText.setVisible(false);
        reset.setVisible(false);
        winnings.setText("Winnings: $" + Integer.toString(Main.getWinnings()));
        File dir = new File("categories"); // get location of categories folder
        File[] categoryFolder = dir.listFiles();
        if (categoryFolder != null) {
            for (File category : categoryFolder) { // iterate through each category
                int validQuestionCounter = 0; // track the unanswered questions
                index_y = 0;
                Text categoryName = new Text(category.getName().toUpperCase());
                categoryName.setFont(Font.font("Agency FB", 45));
                categoryName.setFill(Color.LIGHTSKYBLUE);
                grid.add(categoryName, index_x, index_y);
                GridPane.setHalignment(categoryName, HPos.CENTER);
                TextFileReader reader = new TextFileReader();
                List<String> lines = reader.read(category);
                for (String line : lines) { // go through every line of the category file and parse the results, forming
                                            // the respective fields
                    String question = line.split("\\,")[1];
                    String answer = line.split("\\,")[2];
                    answer = answer.trim();
                    line = line.split("\\,")[0];
                    if (!(Main.getAnsweredQuestions().contains(question))) { // if the question has not been answered
                                                                             // yet
                        validQuestionCounter++;
                        index_y++;
                        addButton(line, question, answer); // add it to the board
                    }
                }
                if (validQuestionCounter == 0) {
                    Text complete = new Text("Category complete!");
                    complete.setFont(Font.font("Agency FB", 29));
                    complete.setFill(Color.LIGHTGREEN);
                    complete.setWrappingWidth(150);
                    grid.add(complete, index_x, 1);
                    GridPane.setHalignment(complete, HPos.CENTER);
                    completedCategoryCounter++;
                }
                index_x++;
            }
        } else {
            // the case when the category folder is not found
            System.out.println("Category folder not found");
        }

        if (completedCategoryCounter == categoryFolder.length) { // when the game has been completed
            resetText.setVisible(true);
            reset.setVisible(true);
        }
    }

    public void addButton(String text, String question, String answer) {
        Button button = new Button("$" + text);
        button.setPrefSize(250, 80);
        button.setFont(Font.font("Agency FB", 40));
        button.setStyle("-fx-background-color: #ffc100; ");
        button.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { // when the player selections a question
                try {
                    questionController.setQuestion(question);
                    questionController.setAnswer(answer);
                    questionController.setValue(Integer.valueOf(text));
                    onQuestionButtonPushed(event); // send the event to the buttons event handler
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        grid.add(button, index_x, index_y);
        GridPane.setHalignment(button, HPos.CENTER);
    }

    public void onMainMenuPushed(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("view/mainMenu.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    public void onQuestionButtonPushed(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("view/question.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }

    // called when there are no categories available
    public void onResetPushed(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("view/reset.fxml"));
        Scene viewScene = new Scene(viewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.show();
    }
}