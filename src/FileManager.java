import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void read(String fileName, FEALData data) {
        boolean isPlainText = true;
        String line = null;
        int i = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null && i < data.getPAIRS()) {
                if (line.length() < 12)
                    continue;
                if (isPlainText) {
                    data.getPlaintext()[i] = line.substring(12, 28);
                } else {
                    data.getCiphertext()[i] = line.substring(12, 28);
                    i++;
                }
                isPlainText = !isPlainText;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            ex.printStackTrace();
        }

        for (i = 0; i < data.getPAIRS(); i++) {
            System.out.println(data.getPlaintext()[i]);
            System.out.println(data.getCiphertext()[i]);
        }
    }

    public static void write(FEALData data) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < data.getPAIRS(); i++) {
                bufferedWriter.write(data.getPlaintext()[i]);
                bufferedWriter.newLine();
                bufferedWriter.write(data.getCiphertext()[i]);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Results written to output.txt");
        } catch (IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
    }
}
