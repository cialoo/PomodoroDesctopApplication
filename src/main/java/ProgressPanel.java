import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressPanel extends JPanel implements ActionListener{

    JLabel label;
    JLabel labelTime;
    JScrollPane scrollPane;
    private JButton switchButton;
    private MainFrame mainFrame;
    private JButton deleteButton;
    long sumTime = 0;

    long seconds = 0;
    long minutes = 0;
    long hours = 0;
    String secondsStr = String.format("%02d", seconds);
    String minutesStr = String.format("%02d", minutes);
    String hoursStr = String.format("%02d", hours);
    MyFile myFile = new MyFile("pomodoro_progress.txt");
    JTable table;
    ProgressPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.setBackground(Color.BLACK);
        this.setLayout(null);

        label = new JLabel();
        label.setBounds(0,0,500,100);
        label.setText("Today Progress");
        label.setFont(new Font("Consolas", Font.PLAIN, 35));
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        DefaultTableModel model = myFile.loadTableModelFromFile();

        table = new JTable(model);
        table.setEnabled(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100, 150, 300, 200);

        labelTime = new JLabel();
        labelTime.setBounds(0,50,500,100);
        labelTime.setFont(new Font("Consolas", Font.PLAIN, 35));
        labelTime.setForeground(Color.GREEN);
        labelTime.setHorizontalAlignment(JLabel.CENTER);
        labelTime.setVerticalAlignment(JLabel.CENTER);

        deleteButton = new JButton();
        deleteButton.addActionListener(this);
        deleteButton.setText("Clear Progress");
        deleteButton.setBounds(175, 400, 150,25);
        deleteButton.setFocusable(false);

        switchButton = new JButton();
        switchButton.setText("Watch");
        switchButton.setBounds(200, 425, 100,25);
        switchButton.setFocusable(false);

        switchButton.addActionListener(e -> mainFrame.switchToWatchPanel());

        this.add(scrollPane);
        this.add(deleteButton);
        this.add(switchButton);
        this.add(label);
        this.add(labelTime);
    }


    public void updatelabelTime(long value) {
        sumTime = value;

        if(sumTime > 0) {
            hours = (sumTime / 3600000);
            minutes = (sumTime / 60000) % 60;
            seconds = (sumTime / 1000) % 60;
            secondsStr = String.format("%02d", seconds);
            minutesStr = String.format("%02d", minutes);
            hoursStr = String.format("%02d", hours);
            labelTime.setText(hoursStr + ":" + minutesStr + ":" + secondsStr);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deleteButton) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to clear the progression?",
                    "Agree",
                    JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                myFile.deleteFile();
                labelTime.setText("00:00:00");
                String[][] emptyData = {
                        {" ", " "}
                };
                String[] columnNames = {"Data", "Czas"};
                DefaultTableModel emptyModel = new DefaultTableModel(emptyData, columnNames);
                table.setModel(emptyModel);
            }
        }
    }
}
