import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    ImageIcon image;
    JLabel label;
    JTextField textField;

    MyFrame() {

        this.setTitle("Pomodoro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        image = new ImageIcon("logoPomodoro.png");
        this.setIconImage(image.getImage());

        label = new JLabel();
        label.setText("Pomodoro");
        label.setFont(new Font("Consolas", Font.PLAIN, 35));
        label.setForeground(Color.GREEN);

        this.add(label);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setVisible(true);

    }

}
