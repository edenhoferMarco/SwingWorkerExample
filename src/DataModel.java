import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class DataModel {
    public static final String COMPLETE_WORKLOAD_ID = "COMPLETE_WORKLOAD";

    private int fullWorkload;
    private int finishedWorkload = 0;
    private SwingPropertyChangeSupport propertyChangeNotifier;

    public DataModel(int fullWorkload) {
        propertyChangeNotifier = new SwingPropertyChangeSupport(this);
        this.fullWorkload = fullWorkload;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeNotifier.addPropertyChangeListener(propertyChangeListener);
    }


    public void setFinishedWorkload(int finishedWorkload) {
        int oldVal = this.finishedWorkload;

        this.finishedWorkload = finishedWorkload;
        propertyChangeNotifier.firePropertyChange(COMPLETE_WORKLOAD_ID, oldVal, this.finishedWorkload);
    }

    public int getFinishedWorkload() {
        return finishedWorkload;
    }

    public int getFullWorkload() {
        return fullWorkload;
    }
}
