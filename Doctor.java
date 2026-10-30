import java.util.Random;

public class Doctor implements Runnable{
    private int id;
    private WaitingRoom waitingRoom;
    private TreatmentRoom treatmentRoom;
    private Random random = new Random();

    public Doctor(int id, WaitingRoom waitingRoom, TreatmentRoom treatmentRoom){
        this.id = id;
        this.waitingRoom = waitingRoom;
        this.treatmentRoom = treatmentRoom;
    }
    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            try{
                Patient patient = waitingRoom.takePatient();
                if (patient == null) {
                    continue;
                }
                System.out.println("Patient " + patient.getId() + " is assigned to Doctor " + id + ".");
                if(treatmentRoom.enterTreatmentRoom(3000)){
                    System.out.println("Patient " + patient.getId() + " begins treatment in a treatment room acquired by Doctor " + id + ".");
                    int treatmentTime = random.nextInt(5000);
                    Thread.sleep(treatmentTime);
                    System.out.println("Patient " + patient.getId() + " finished treatment.");
                    treatmentRoom.exitTreatmentRoom();
                    System.out.println("Patient " + patient.getId() + " left the hospital.");
                }else{
                    System.out.println("Doctor " + id + " could not acquire a treatment room in time. Patient " + patient.getId() + " goes back to the waiting room.");
                    waitingRoom.enterWaitingRoom(patient);
                }

            }catch(InterruptedException e){
                e.printStackTrace();
                break;
            }

        }

    }
}