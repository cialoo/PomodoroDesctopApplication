import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    ImageIcon image;
    JLabel label;
    JButton buttonStart;
    JButton buttonStop;
    JButton buttonSet;
    JTextField textField;
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    String secondsStr = String.format("%02d", seconds);
    String minutesStr = String.format("%02d", minutes);
    String hoursStr = String.format("%02d", hours);
    JLabel labelWatch;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            secondsStr = String.format("%02d", seconds);
            minutesStr = String.format("%02d", minutes);
            hoursStr = String.format("%02d", hours);
            labelWatch.setText(hoursStr + ":" + minutesStr + ":" + secondsStr);
        }
    });
    MyFrame() {

        this.setTitle("Pomodoro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        image = new ImageIcon("logoPomodoro.png");
        this.setIconImage(image.getImage());

        label = new JLabel();
        label.setBounds(0,0,500,100);
        label.setText("Pomodoro");
        label.setFont(new Font("Consolas", Font.PLAIN, 35));
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        textField = new JTextField();
        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
        textField.setBackground(Color.GREEN);
        textField.setForeground(Color.BLACK);
        textField.setBounds(100,100,300,50);

        buttonSet = new JButton();
        buttonSet.addActionListener(this);
        buttonSet.setText("Set");
        buttonSet.setBounds(200, 150, 100,25);

        labelWatch = new JLabel();
        labelWatch.setText(hoursStr + ":" + minutesStr + ":" + secondsStr);
        labelWatch.setBounds(100, 200, 300,100);
        labelWatch.setFont(new Font("Consolas", Font.PLAIN, 35));
        labelWatch.setForeground(Color.GREEN);
        labelWatch.setHorizontalAlignment(JLabel.CENTER);
        labelWatch.setVerticalAlignment(JLabel.CENTER);

        buttonStart = new JButton();
        buttonStart.addActionListener(this);
        buttonStart.setText("Start");
        buttonStart.setBounds(200, 400, 100,25);

        buttonStop = new JButton();
        buttonStop.addActionListener(this);
        buttonStop.setText("Stop");
        buttonStop.setBounds(200, 425, 100,25);

        this.add(buttonSet);
        this.add(textField);
        this.add(labelWatch);
        this.add(label);
        this.add(buttonStart);
        this.add(buttonStop);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonStart) {
            start();
        } else if (e.getSource()==buttonStop) {
            stop();
        } else if (e.getSource()==buttonSet) {
            elapsedTime = 1000*Integer.parseInt(textField.getText());
        }
    }

    void start() {

        timer.start();

    }

    void stop() {

        timer.stop();

    }

}
