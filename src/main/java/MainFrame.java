import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    ImageIcon image;

    private WatchPanel watchPanel;
    private ProgressPanel progressPanel;

    MainFrame() {

        this.setTitle("Pomodoro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        image = new ImageIcon("logoPomodoro.png");
        this.setIconImage(image.getImage());

        watchPanel = new WatchPanel(this);
        progressPanel = new ProgressPanel(this);

        this.setLayout(new CardLayout());

        this.add(watchPanel, "WatchPanel");
        this.add(progressPanel, "ProgressPanel");

        this.setResizable(false);
        this.setVisible(true);;

    }


    public void switchToProgressPanel() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "ProgressPanel");
    }

    public void switchToWatchPanel() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "WatchPanel");
    }

    public WatchPanel getWatchPanel() {
        return watchPanel;
    }

    public ProgressPanel getProgressPanel() {
        return progressPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
