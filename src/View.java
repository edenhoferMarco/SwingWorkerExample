import javax.swing.*;

public class View extends JFrame {

    private JProgressBar progressBar;
    private JButton startButton;

    public View() {
        setTitle("Swing Worker Example");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(200, 100);

        startButton = new JButton("Start");
        startButton.setEnabled(true);
        startButton.setBounds(5, 5, 120, 30);
        add(startButton);

        progressBar = new JProgressBar();
        progressBar.setBounds(5,50, 190, 20);
        add(progressBar);

        setVisible(true);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

}
