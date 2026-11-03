import java.util.ArrayList;
import java.util.List;
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

        System.out.println("\nPatients arriving:");
        for (int i = 1; i <= patientCount; i++) {
            System.out.print("P" + i + " ");
        }
        System.out.println("\n");

        WaitingRoom waitingRoom = new WaitingRoom(capacity);
        TreatmentRoom treatmentRoom = new TreatmentRoom();

        for (int i = 1; i <= doctorCount; i++) {
            Doctor doctor = new Doctor(i, waitingRoom, treatmentRoom);
            Thread doctorThread = new Thread(doctor, "Doctor-" + i);
            doctorThread.setDaemon(true);
            doctorThread.start();
        }

        List<Thread> patientThreads = new ArrayList<>();

        for (int i = 1; i <= patientCount; i++) {
            Patient patient = new Patient(i, waitingRoom);
            Thread patientThread = new Thread(patient, "Patient-" + i);
            patientThreads.add(patientThread);
            patientThread.start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        for (Thread t : patientThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All patients have been treated.");
        System.out.println("Simulation completed.");

        scanner.close();
    }
}