import java.util.*;

class AttendanceManagementSystem {
    private static class Student {
        int id;
        String name;
        int totalClasses;
        int attendedClasses;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
            this.totalClasses = 0;
            this.attendedClasses = 0;
        }

        void markAttendance(boolean present) {
            totalClasses++;
            if (present) {
                attendedClasses++;
            }
        }

        double getAttendancePercentage() {
            if (totalClasses == 0) return 0.0;
            return (attendedClasses / (double) totalClasses) * 100;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Attendance: " + String.format("%.2f", getAttendancePercentage()) + "%";
        }
    }

    private final Map<Integer, Student> students = new HashMap<>();

    void addStudent(int id, String name) {
        if (students.containsKey(id)) {
            System.out.println("Student with ID " + id + " already exists.");
        } else {
            students.put(id, new Student(id, name));
            System.out.println("Student added successfully.");
        }
    }

    void markAttendance(int id, boolean present) {
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            student.markAttendance(present);
            System.out.println("Attendance marked for " + student.name + ".");
        }
    }

    void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        AttendanceManagementSystem system = new AttendanceManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Attendance Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Display All Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    system.addStudent(id, name);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int attendanceId = scanner.nextInt();
                    System.out.print("Is the student present? (true/false): ");
                    boolean present = scanner.nextBoolean();
                    system.markAttendance(attendanceId, present);
                    break;
                case 3:
                    system.displayAllStudents();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
