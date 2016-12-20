package ExecutionManager;

import java.util.List;

public class ContextImpl implements Context {

    private List<MyThread> threadPull;
    private volatile int failedCount;

    public ContextImpl(List<MyThread> threadPull) {
        this.threadPull = threadPull;
        failedCount = 0;
        Thread.UncaughtExceptionHandler eh = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                failedCount++;
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(eh);
    }

    @Override
    public int getCompletedTaskCount() {
        int sum = 0;
        for (Thread thread : threadPull) {
            if (thread.getState() == Thread.State.TERMINATED){
                sum++;
            }
        }
        return sum - failedCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        int sum = 0;
        for (Thread thread : threadPull) {
            if (thread.getState() == Thread.State.NEW){
                sum++;
            }
        }
        return sum;
    }

    @Override
    public void interrupt() {
        for (MyThread thread : threadPull) {
            synchronized (thread) {
                if (thread.getState() == Thread.State.NEW) {
                    thread.interrupt();
                }
            }
        }
    }

    @Override
    public boolean isFinished() {
        for (MyThread thread : threadPull) {
            if (thread.isAlive()){
                return false;
            }
        }
        return true;
    }
}
