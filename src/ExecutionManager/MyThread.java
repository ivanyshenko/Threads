package ExecutionManager;

/**
 * Created by alex on 20.12.2016.
 */
public class MyThread extends Thread {
    private boolean isInterrupted;

    public MyThread(Runnable target) {
        super(target);
        this.isInterrupted = false;
    }

    @Override
    public synchronized void start() {
        if(!isInterrupted){
            super.start();
        }
    }

    @Override
    public boolean isInterrupted() {
        if (isInterrupted){
            return true;
        }else{
            return super.isInterrupted();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        isInterrupted = true;
    }
}
