public class WaitingRoom{
    private Semaphore availableSpaces;
    private Semaphore numberOfPatients;
    private Object lock = new Object();
    private Patient[] queue;
    private int maxSpaces;
    private int in = 0;
    private int out = 0;

    public WaitingRoom(int MaxSpaces){
        this.maxSpaces = MaxSpaces;
        this.queue = new Patient[MaxSpaces];
        this.availableSpaces = new Semaphore(MaxSpaces);
        this.numberOfPatients = new Semaphore(0);
    }
    public void enterWaitingRoom(Patient patient) throws InterruptedException{
        if (!availableSpaces.waitForTime(0)) {
            System.out.println("The waiting room is full");
            System.out.println("Patient " + patient.getId() + " is waiting outside");
            System.out.println();
            availableSpaces.acquire();
        }
        synchronized (lock){
            queue[in] = patient;
            in = (in + 1) % maxSpaces;
        }
        System.out.println("Patient " + patient.getId() + " entered the waiting room");
        System.out.println();
        numberOfPatients.release();
    }

    public Patient takePatient() throws InterruptedException{
        numberOfPatients.acquire();
        Patient patient;

        synchronized (lock){
            patient = queue[out] ;
            queue[out] = null;
            out = (out + 1) % maxSpaces;
        }
        availableSpaces.release();
        return patient;
    }

}