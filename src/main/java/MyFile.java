import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public long loadProgress() {
        MyFile myFile = new MyFile(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            long sum = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDateTime dateTime = parseDateTime(parts[0].trim());
                    if (isToday(dateTime)) {
                        sum += Long.parseLong(parts[1].trim());
                    }
                }
            }

            return sum;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public DefaultTableModel loadTableModelFromFile() {
        Map<LocalDate, Long> dailySums = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDateTime dateTime = parseDateTime(parts[0].trim());
                    LocalDate date = dateTime.toLocalDate();
                    long timeValue = Long.parseLong(parts[1].trim());

                    dailySums.put(date, dailySums.getOrDefault(date, 0L) + timeValue);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Object[][] data = dailySums.entrySet().stream()
                .map(entry -> new Object[]{entry.getKey().toString(), formatTime(entry.getValue())})
                .toArray(Object[][]::new);
        String[] columnNames = {"Data", "Czas"};
        return new DefaultTableModel(data, columnNames);
    }

    private String formatTime(long millis) {
        long hours = millis / 3600000;
        long minutes = (millis % 3600000) / 60000;
        long seconds = ((millis % 3600000) % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void deleteFile() {
        File file = new File(fileName);

        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("File does not exist: " + fileName);
        }
    }

    public boolean doesFileExist() {
        File file = new File(fileName);
        return file.exists() && !file.isDirectory();
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    private boolean isToday(LocalDateTime dateTime) {
        LocalDate today = LocalDate.now();
        return today.equals(dateTime.toLocalDate());
    }
}