import javax.swing.*;
import java.util.List;

public class WorkerThread extends SwingWorker<Void, Integer> {
    private DataModel model;
    private int finishedWorkLoad;
    private int fullWorkload;
    private boolean computationDone = false;

    public WorkerThread(DataModel model) {
        this.model = model;
        this.fullWorkload = model.getFullWorkload();
        this.finishedWorkLoad = 0;

    }

    public boolean computationIsDone() {
        return computationDone;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled() && finishedWorkLoad < fullWorkload) {
            finishedWorkLoad++;
            Thread.sleep(1000);
            publish(finishedWorkLoad);
        }

        if (fullWorkload - finishedWorkLoad == 0) {
            System.out.println("Worker done!");
        }
        computationDone = true;
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        int currentlyFinishedWorkload = chunks.get(chunks.size() - 1);
        model.setFinishedWorkload(currentlyFinishedWorkload);
    }


}
