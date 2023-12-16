import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel {

    JLabel label;
    ProgressPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        label = new JLabel();
        label.setBounds(0,0,500,100);
        label.setText("Progress");
        label.setFont(new Font("Consolas", Font.PLAIN, 35));
        label.setForeground(Color.GREEN);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        this.add(label);
    }

}
