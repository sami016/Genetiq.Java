package uk.co.samholder.genetiq.runner.genetic.parallel;

/**
 * Lock created for running several asynchronous tasks which block another thread from executing until all are completed.
 * 
 * @author Sam Holder
 */
public class MultiplexLock {
    
    // The number of users currently preventing.
    private volatile int count = 0;
    
    private final Object taskLock = new Object();
    private final Object mainLock = new Object();
    
    /**
     * Registers a task to start blocking access until completion.
     * Should be called before awaitCompletion is called.
     */
    public void beginTask() {
        synchronized (taskLock) {
            count++;
        }
    }
    
    /**
     * Notify that a task has completed execution.
     * Must only be called once by a task that has registered.
     */
    public void completeTask() {
        synchronized (taskLock) {
            count--;
            if (isOpen()) {
                open();
            }
        }
    }
    
    /**
     * Checks whether the lock is currently open.
     * @return open
     */
    private boolean isOpen() {
        return count == 0;
    }
    
    /**
     * Notify all waiting threads that the lock is now open.
     */
    private void open() {
        synchronized (mainLock) {
            mainLock.notifyAll();
        }
    }
    
    /**
     * Awaits execution until all dependent tasks have notified of completion.
     */
    public void awaitCompletion() {
        synchronized (mainLock) {
            while (!isOpen()) {
                try {
                    mainLock.wait();
                } catch (InterruptedException ex) {}
            }
        }
    }
}
