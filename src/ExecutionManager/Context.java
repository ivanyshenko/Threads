package ExecutionManager;

/**
 * Метод getCompletedTaskCount() возвращает количество тасков, которые на текущий момент успешно выполнились.
 * Метод getFailedTaskCount() возвращает количество тасков, при выполнении которых произошел Exception.
 * Метод interrupt() отменяет выполнения тасков, которые еще не начали выполняться.
 * Метод getInterruptedTaskCount() возвращает количество тасков, которые не были выполены из-за отмены (вызовом предыдущего метода).
 * Метод isFinished() вернет true, если все таски были выполнены или отменены, false в противном случае.
 */


public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void interrupt();

    boolean isFinished();
}
