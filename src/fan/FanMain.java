package fan;

public class FanMain {
    public static void main(String[] args) {
        FanController theFanController = new FanController();
        Thread theFanThread = new Thread(theFanController);
        theFanThread.start();       
    }
}