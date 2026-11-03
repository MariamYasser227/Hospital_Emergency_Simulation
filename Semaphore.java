public class Semaphore {
    protected int value;

    public Semaphore(int init) {
        this.value = init;
    }

    public synchronized void acquire() throws InterruptedException{
        while(value <= 0){
            wait();
        }
        value--;

    }

    public synchronized boolean waitForTime(long time) {
        if (value > 0) {
            value--;
            return true;
        }

        long startTime = System.currentTimeMillis();
        long timeRemaining = time;
        long endTime = startTime + time;
        while (value <= 0 && timeRemaining > 0) {
            try {
                wait(timeRemaining);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            timeRemaining = endTime - System.currentTimeMillis();
        }

        if (value > 0) {
            value--;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void release() {
        value++;
        notifyAll();
    }
}