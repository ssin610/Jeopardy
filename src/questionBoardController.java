package assignment2ssin610.src;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class questionBoardController implements Initializable {
	int index = 0;
	int target = 13;

	@FXML
	GridPane button_grid;

	@FXML
	private TextArea linesTextArea;
	@FXML
	private TextArea categories;

	public void initialize(URL url, ResourceBundle rb) {

		File dir = new File("./assignment2ssin610/src/categories");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				index++;
				addButton();
				categories.appendText(child.getName() + "\n");
				
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
					index++;
					addButton();
					line = line.split("\\,")[0];
					linesTextArea.appendText(line + "\n");
				}
			}
		} else {
			// Handle the case where dir is not really a directory.
			// Checking dir.isDirectory() above would not be sufficient
			// to avoid race conditions with another process that deletes
			// directories.
			System.out.println("S");
		}
	}
	
	public void addButton(){
        Button sound_button = new Button("Button_" + index);
        button_grid.add(sound_button, index,2);
    }

}
