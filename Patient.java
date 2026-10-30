public class Patient implements Runnable{
    private int id;
    private WaitingRoom waitingRoom;

    public Patient(int id, WaitingRoom waitingRoom){
        this.id = id;
        this.waitingRoom = waitingRoom;
    }
    @Override
    public void run(){
        try{
            System.out.println("Patient " + id + " arrived at the hospital.");
            waitingRoom.enterWaitingRoom(this);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
    public int getId() {
        return id;
    }
}