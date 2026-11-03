public class Patient implements Runnable{
    private int id;
    private WaitingRoom waitingRoom;
    private boolean isTreated = false;

    public Patient(int id, WaitingRoom waitingRoom){
        this.id = id;
        this.waitingRoom = waitingRoom;

    }
    public synchronized void waitUntilTreated() throws InterruptedException {
        while (!isTreated) {
            wait();
        }
    }
    public synchronized void markAsTreated() {
        this.isTreated = true;
        notifyAll();
    }
    @Override
    public void run(){
        try{
            System.out.println("Patient " + id + " arrived at the hospital.");
            System.out.println();
            waitingRoom.enterWaitingRoom(this);
            waitUntilTreated();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
    public int getId() {
        return id;
    }
}