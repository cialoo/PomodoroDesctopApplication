import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    ImageIcon image;
    CardLayout cardLayout;
    JPanel cardPanel;
    JButton buttonSwitch;
    MainFrame() {

        this.setTitle("Pomodoro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        image = new ImageIcon("logoPomodoro.png");
        this.setIconImage(image.getImage());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new WatchPanel(), "WatchPanel");
        cardPanel.add(new ProgressPanel(), "ProgressPanel");
        cardPanel.setBounds(0,0,500,425);

        buttonSwitch = new JButton();
        buttonSwitch.addActionListener(this);
        buttonSwitch.setText("Switch");
        buttonSwitch.setBounds(200, 425, 100,25);
        buttonSwitch.setFocusable(false);

        this.add(cardPanel);
        this.add(buttonSwitch);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setVisible(true);;

    }

    void switchPanel() {

        cardLayout.next(cardPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonSwitch) {
            switchPanel();
        }
    }
}
