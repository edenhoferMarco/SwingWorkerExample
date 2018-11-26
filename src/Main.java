public class Main {
    public static void main(String[] args) {
        // Setup after the MVP (Model-View-Presenter) Design Pattern.
        new Controller(new View(), new DataModel(5));
    }
}
