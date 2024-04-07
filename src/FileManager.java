import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    }

    public static void write(int key0, int key1, int key2, int key3, int key4, int key5) {
        try {
            File outputFile = new File("output.txt");
            FileWriter fileWriter;
            if (outputFile.exists())
                fileWriter = new FileWriter("output.txt", true);
            else
                fileWriter = new FileWriter("output.txt");

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("0x" + Integer.toHexString(key0) + " 0x" + Integer.toHexString(key1) + " 0x"
                    + Integer.toHexString(key2) + " 0x" + Integer.toHexString(key3) + " 0x"
                    + Integer.toHexString(key4) + " 0x" + Integer.toHexString(key5));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
    }
}
