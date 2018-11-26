import javax.swing.*;
import java.awt.event.*;

public class Controller {
    private View view;
    private DataModel model;
    private WorkerThread workerThread;

    public Controller(View view, DataModel model) {
        JProgressBar progressBar;

        this.view = view;
        this.model = model;

        view.addWindowListener(new WindowCloseListener());

        progressBar = this.view.getProgressBar();
        progressBar.setMaximum(model.getWorkload());
        progressBar.setValue(0);
        this.view.getStartButton().addActionListener(new StartListener());
    }

    private class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getStartButton().setEnabled(false);
            workerThread = new WorkerThread(model.getWorkload(), view.getProgressBar(), view.getStartButton());
            workerThread.execute();
        }


    }

    private class WindowCloseListener extends WindowAdapter implements WindowListener {
        @Override
        public void windowClosing(WindowEvent e) {
            if (workerThread != null && !workerThread.isDone()) {
                workerThread.cancel(false);
                while(!workerThread.computationIsDone()) {
                    // Wait until thread terminates...
                    System.out.println("Waiting for termination...");
                }
                System.out.println("Worker Terminated!");
            }
        }
    }
}
