import javax.swing.*;
import java.util.List;

public class WorkerThread extends SwingWorker<Void, Integer> {
    private int workLoad;
    private int workLoadMax;
    private JProgressBar progressBar;
    private JButton startButton;
    private boolean computationDone = false;

    public WorkerThread(int workLoad, JProgressBar progressBar, JButton startButton) {
        this.workLoad = workLoad;
        this.workLoadMax = workLoad;
        this.progressBar = progressBar;
        this.startButton = startButton;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled() && workLoad > 0) {
            workLoad--;
            Thread.sleep(1000);
            publish(workLoad);
        }

        if (workLoad == 0) {
            System.out.println("Worker done!");
            startButton.setEnabled(true);
        }
        computationDone = true;
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int currentWorkload = chunks.get(chunks.size() - 1);
        progressBar.setValue(workLoadMax - currentWorkload);
    }

    public boolean computationIsDone() {
        return computationDone;
    }
}
