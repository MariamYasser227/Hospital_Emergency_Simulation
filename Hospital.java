import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Waiting Room Capacity: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter Number of Doctors: ");
        int doctorCount = scanner.nextInt();

        System.out.print("Enter Number of Patients: ");
        int patientCount = scanner.nextInt();

        WaitingRoom waitingRoom = new WaitingRoom(capacity);
        TreatmentRoom treatmentRoom = new TreatmentRoom();

        for (int i = 1; i <= doctorCount; i++) {
            Doctor doctor = new Doctor(i, waitingRoom, treatmentRoom);
            Thread doctorThread = new Thread(doctor, "Doctor-" + i);
            doctorThread.start();
        }

        for (int i = 1; i <= patientCount; i++) {
            Patient patient = new Patient(i, waitingRoom);
            Thread patientThread = new Thread(patient, "Patient-" + i);
            patientThread.start();

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        scanner.close();
    }
}