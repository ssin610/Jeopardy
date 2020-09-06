package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;   
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class questionController implements Initializable {

	@FXML
	GridPane grid;

	@FXML
	TextField userAnswer;   

	@FXML
	Text questionText;  

	@FXML
	Text correctText; 

	@FXML
	Text incorrectText; 

	@FXML
	Text promptText;

	@FXML
	Button submitButton; 

	@FXML
	Button menuButton; 

	@FXML
	Button winnings;

	// store information about the particular question
	private static String question;
	private static String answer;
	private static int value;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// show and hide appropriate fxml elements
		correctText.setVisible(false);
		incorrectText.setVisible(false);
		winnings.setText("Winnings: $" + Integer.toString(Main.getWinnings()));
		questionText.setText(question);
		Main.addAnsweredQuestion(question);
		menuButton.setVisible(false);
		// use festival to ask the question
		toFestival("Your question is: " + question);
	}

	public static void setQuestion(String questionString) {
		question = questionString;
	}

	public static void setAnswer(String answerString) {
		answer = answerString;
	}

	public static void setValue(int valueInt) {
		value = valueInt;
	}

	public void onSubmitButtonPushed(ActionEvent event) throws IOException {
		promptText.setVisible(false);
		if (userAnswer.getText().equalsIgnoreCase(answer)){
			// increase the winnings and show/hide the appropriate elements
			Main.setWinnings(value);
			correctText.setText("Correct! $" + value + " has been added to your winnings!");
			questionText.setVisible(false);
			correctText.setVisible(true);
			// use festival to tell the player they got the question correct
			toFestival("Correct!");
		}
		else {
			// increase the winnings and show/hide the appropriate elements
			Main.setWinnings(-value);
			incorrectText.setText("Incorrect. The correct answer was: " + answer);
			questionText.setVisible(false);
			incorrectText.setVisible(true);
			// use festival to tell the player the correct answer
			toFestival("Incorrect. The correct answer was: " + answer);
		}
		winnings.setText("Winnings: $" + Integer.toString(Main.getWinnings()));
		submitButton.setVisible(false);
		menuButton.setVisible(true);
	}

	public void onMainMenuPushed(ActionEvent event) throws IOException
	{
		Parent viewParent = FXMLLoader.load(getClass().getResource("view/mainMenu.fxml"));
		Scene viewScene = new Scene(viewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(viewScene);
		window.show();
	}

	public void toFestival(String input) {
		ProcessBuilder pb = new ProcessBuilder("festival", "--tts");
		Process p = null;
		try {
			p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		OutputStream in = p.getOutputStream();
		PrintWriter stdin = new PrintWriter(in);
		stdin.println(input);
		stdin.close();
	}
}