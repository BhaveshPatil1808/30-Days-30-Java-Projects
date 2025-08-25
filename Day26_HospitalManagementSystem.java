import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + " | Name: " + name + " | Age: " + age + " | Disease: " + disease;
    }
}

class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + " | Name: " + name + " | Specialization: " + specialization;
    }
}

public class Day26_HospitalManagementSystem {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== 🏥 Hospital Management System =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. View Doctors");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewPatients();
                case 3 -> addDoctor();
                case 4 -> viewDoctors();
                case 5 -> System.out.println("👋 Exiting Hospital System...");
                default -> System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void addPatient() {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();

        patients.add(new Patient(id, name, age, disease));
        System.out.println("✅ Patient added successfully!");
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("⚠️ No patients found.");
            return;
        }
        System.out.println("\n📋 Patient List:");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    private static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Doctor Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        doctors.add(new Doctor(id, name, specialization));
        System.out.println("✅ Doctor added successfully!");
    }

    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("⚠️ No doctors found.");
            return;
        }
        System.out.println("\n📋 Doctor List:");
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }
}
