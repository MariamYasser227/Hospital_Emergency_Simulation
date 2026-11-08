# Hospital Simulation

## Overview

This project simulates a hospital environment using Java multithreading and synchronization concepts. Multiple patients arrive at the hospital and wait in a limited waiting room until a doctor becomes available. Doctors continuously treat patients while managing access to shared resources safely.

The project demonstrates synchronization between multiple threads and implements a custom semaphore to coordinate access to shared resources.

---

## Features

- Multi-threaded simulation using Java Threads.
- Configurable number of:
  - Patients
  - Doctors
  - Waiting room capacity
- Limited waiting room implemented as a circular queue.
- Multiple doctors working concurrently.
- Treatment room with synchronized access.
- Custom semaphore implementation.
- Proper synchronization using `wait()`, `notifyAll()`, and `synchronized`.

---

## Project Structure

- **Hospital.java**
  - Starts the simulation.
  - Reads user input.
  - Creates doctor and patient threads.

- **Patient.java**
  - Represents a patient.
  - Enters the waiting room and waits until treatment is completed.

- **Doctor.java**
  - Continuously receives patients from the waiting room.
  - Treats patients and marks treatment as completed.

- **WaitingRoom.java**
  - Manages patient arrival.
  - Implements a bounded circular queue.
  - Synchronizes patient insertion and removal.

- **TreatmentRoom.java**
  - Controls patient treatment.
  - Ensures synchronized access during treatment.

- **Semaphore.java**
  - Custom implementation of a counting semaphore.
  - Provides synchronization using `acquire()` and `release()`.

---

## Synchronization

The project uses:

- Java Threads
- `synchronized`
- `wait()`
- `notifyAll()`
- Custom Counting Semaphore

to avoid race conditions and coordinate communication between patients and doctors.

---

## Simulation Flow

1. The user enters:
   - Waiting room capacity
   - Number of doctors
   - Number of patients

2. Doctor threads start and wait for patients.

3. Patient threads arrive one by one.

4. Patients enter the waiting room if space is available.

5. Doctors take patients from the waiting room.

6. Patients are treated inside the treatment room.

7. After treatment is completed, patient threads terminate.

8. When all patient threads finish, the simulation ends.

---

## How to Run

1. Compile all Java files.

```bash
javac *.java
```

2. Run the application.

```bash
java Hospital
```

3. Enter:
   - Waiting room capacity
   - Number of doctors
   - Number of patients

The simulation will display the interaction between patients and doctors until all patients have been treated.

---

## Concepts Covered

- Multithreading
- Producer–Consumer Pattern
- Thread Synchronization
- Custom Semaphore
- Circular Queue
- Shared Resources
- Concurrency Control
