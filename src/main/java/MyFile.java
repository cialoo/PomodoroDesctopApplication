import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyFile {
    private final String fileName;

    static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    public MyFile(String fileName) {
        this.fileName = fileName;
    }

    public void saveProgress(long progressTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(timeStamp + " , " + progressTime);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int loadProgress() {
        MyFile myFile = new MyFile(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
