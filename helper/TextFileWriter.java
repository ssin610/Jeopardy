package src.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TextFileWriter {

    public static void write(String filename, Integer balance, ArrayList<String> answeredQuestions) throws IOException {
        File file = new File(filename);

        // Checks if file does not exist. If it does not, it creates it
        if (!file.exists()) {
            FileWriter fWriter = new FileWriter(file);
            PrintWriter pWriter = new PrintWriter(fWriter);
        }

        int limit = 5; // int set to 5
        boolean on = false; // boolean false

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) { // sets the output where to write
                    if (balance != null) {
                        writer.write("" + balance); // writes
                    }
                    else {
                        for (String string : answeredQuestions) {
                            writer.write(string + "\n");
                        }
                    }
        }

        catch (IOException e) {

        }
	}
}
