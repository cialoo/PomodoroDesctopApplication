import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressPanel extends JPanel{

    JLabel label;
    JLabel labelTime;
    private JButton switchButton;
    private MainFrame mainFrame;
    long sumTime = 0;

    long seconds = 0;
    long minutes = 0;
    long hours = 0;
    String secondsStr = String.format("%02d", seconds);
    String minutesStr = String.format("%02d", minutes);
    String hoursStr = String.format("%02d", hours);
    ProgressPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.setBackground(Color.BLACK);
        this.setLayout(null);

        label = new JLabel();
        label.setBounds(0,0,500,100);
        label.setText("Progress");
        label.setFont(new Font("Consolas", Font.PLAIN, 35));
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        labelTime = new JLabel();
        labelTime.setBounds(0,200,500,100);
        labelTime.setFont(new Font("Consolas", Font.PLAIN, 35));
        labelTime.setForeground(Color.GREEN);
        labelTime.setHorizontalAlignment(JLabel.CENTER);
        labelTime.setVerticalAlignment(JLabel.CENTER);

        switchButton = new JButton();
        switchButton.setText("Watch");
        switchButton.setBounds(200, 425, 100,25);
        switchButton.setFocusable(false);

        switchButton.addActionListener(e -> mainFrame.switchToWatchPanel()); // to mi nie pasi ni hu hu

        this.add(switchButton);
        this.add(label);
        this.add(labelTime);
    }

    public void updatelabelTime(long value) {
        sumTime = value + sumTime;

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

}
