package ExecutionManager;

import java.util.ArrayList;
import java.util.List;

public class ExecutionManagerImpl implements ExecutionManager {

    private List<MyThread> threadPull;

    public ExecutionManagerImpl() {
        this.threadPull = new ArrayList<>();
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        for (Runnable task : tasks) {
            MyThread thread = new MyThread(task);
            threadPull.add(thread);
        }

        Thread executorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Thread thread : threadPull) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    thread.start();
                }
            }
        });
        executorThread.start();

        return new ContextImpl(threadPull);
    }

    public static void main(String[] args) throws Exception {
        ExecutionManagerImpl manager = new ExecutionManagerImpl();
        Runnable[] runnables = new Runnable[20];
        for (int i = 0; i < 20; i++){
            if (i%2 == 0){
                runnables[i] = new MyRunnable(i);
            } else {
                runnables[i] = () -> { int j = 1/0; };
            }

        }
        Context context = manager.execute(null, runnables);
        Thread.sleep(100);
        context.interrupt();
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
        System.out.println("Completed: " + context.getCompletedTaskCount());
        System.out.println("Failed: " + context.getFailedTaskCount());
        Thread.sleep(2000);
        System.out.println("Is finished: " + context.isFinished());
    }

}
