package ExecutionManager;

/**
 * Created by alex on 20.12.2016.
 */
public class MyRunnable implements Runnable{
    private int i;

    public MyRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id is " + i);
    }
}