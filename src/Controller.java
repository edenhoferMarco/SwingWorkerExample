import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        progressBar.setMaximum(model.getFullWorkload());
        progressBar.setValue(0);

        this.view.getStartButton().addActionListener(new StartListener());
        this.model.addPropertyChangeListener(new PropertyChangeHandler());
    }

    private class StartListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getStartButton().setEnabled(false);
            view.getProgressBar().setValue(0);
            workerThread = new WorkerThread(model);
            workerThread.execute();
        }
    }

    private class PropertyChangeHandler implements PropertyChangeListener {

        @Override
        // is called each time the DataModel calls the firePropertyChange() method.
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case DataModel.COMPLETE_WORKLOAD_ID:
                    view.getProgressBar().setValue((int) evt.getNewValue());
                    if (model.getFullWorkload() - model.getFinishedWorkload() == 0) {
                        view.getStartButton().setEnabled(true);
                        workerThread = null;
                    }
                    break;
                default:
                    System.out.println("Undefined property changed");
            }
        }
    }

    private class WindowCloseListener extends WindowAdapter implements WindowListener {

        @Override
        // is called if the user clicks on the close button.
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
