public class DataModel {
    private int workload;

    public DataModel(int workload) {
        setWorkload(workload);
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getWorkload() {
        return workload;
    }
}
