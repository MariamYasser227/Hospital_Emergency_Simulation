public class TreatmentRoom{
    private int treatmentRooms = 2;
    private Semaphore availableRooms;

    public TreatmentRoom(){
        this.availableRooms = new Semaphore(treatmentRooms);
    }
    public boolean enterTreatmentRoom(long time){
        return availableRooms.waitForTime(time);
    }
    public void exitTreatmentRoom(){
         availableRooms.release();
    }

}