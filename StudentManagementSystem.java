/*
C:\techincal training>javac StudentManagementSystem.java

C:\techincal training>java StudentManagementSystem
We couldn?t load student data: students.txt (The system cannot find the file specified)

=== Welcome to the Student Management System ===
Please choose an option:
1. Add a new student
2. Edit a student's information
3. Search for a student by ID
4. Display all students
5. Exit
Your choice: 1

Let's add a new student!
Enter Student ID: 12
Enter Name: Brami
Enter Age: 19
Enter Major: male
Student added successfully! ?

=== Welcome to the Student Management System ===
Please choose an option:
1. Add a new student
2. Edit a student's information
3. Search for a student by ID
4. Display all students
5. Exit
Your choice: 4

Here are all the students in the system:
ID: 12, Name: Brami, Age: 19, Major: male

=== Welcome to the Student Management System ===
Please choose an option:
1. Add a new student
2. Edit a student's information
3. Search for a student by ID
4. Display all students
5. Exit
Your choice: 3

Enter the Student ID to search: 12

Student found: ID: 12, Name: Brami, Age: 19, Major: male

=== Welcome to the Student Management System ===
Please choose an option:
1. Add a new student
2. Edit a student's information
3. Search for a student by ID
4. Display all students
5. Exit
Your choice: 3

Enter the Student ID to search: 1
No student found with that ID.

=== Welcome to the Student Management System ===
Please choose an option:
1. Add a new student
2. Edit a student's information
3. Search for a student by ID
4. Display all students
5. Exit
Your choice: 5

All changes have been saved. Your data is safe!
Thank you for using the system. Goodbye!

*/
import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.txt"; // File to store student data
    private static List<Student> students = new ArrayList<>();

    // Main method to run the system
    public static void main(String[] args) {
        loadStudents();  // Load existing students from the file at the start
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Display the menu
            System.out.println("\n=== Welcome to the Student Management System ===");
            System.out.println("Please choose an option:");
            System.out.println("1. Add a new student");
            System.out.println("2. Edit a student's information");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    addStudent(scanner); // Add a new student
                    break;
                case 2:
                    editStudent(scanner); // Edit an existing student
                    break;
                case 3:
                    searchStudent(scanner); // Search for a student by ID
                    break;
                case 4:
                    displayAllStudents(); // Display all students
                    break;
                case 5:
                    saveStudents(); // Save all students to the file before exiting
                    System.out.println("Thank you for using the system. Goodbye!");
                    return;
                default:
                    System.out.println("Oops! That’s not a valid option. Try again.");
            }
        }
    }

    // Add a new student
    private static void addStudent(Scanner scanner) {
        System.out.println("\nLet's add a new student!");

        // Input student details
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Major: ");
        String major = scanner.nextLine();

        // Validate the input before adding
        if (isValidStudentData(studentId, name, age, major)) {
            students.add(new Student(studentId, name, age, major));
            System.out.println("Student added successfully! ");
        } else {
            System.out.println("Hmm, something went wrong. Please ensure all fields are filled correctly.");
        }
    }

    // Edit a student's information
    private static void editStudent(Scanner scanner) {
        System.out.print("\nEnter the Student ID you want to edit: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        
        if (student != null) {
            System.out.println("\nEditing student: " + student.displayInfo());
            System.out.print("Enter new Name: ");
            student.setName(scanner.nextLine());
            System.out.print("Enter new Age: ");
            student.setAge(Integer.parseInt(scanner.nextLine()));
            System.out.print("Enter new Major: ");
            student.setMajor(scanner.nextLine());
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Sorry, we couldn’t find a student with that ID.");
        }
    }

    // Search for a student by ID
    private static void searchStudent(Scanner scanner) {
        System.out.print("\nEnter the Student ID to search: ");
        String studentId = scanner.nextLine();
        Student student = findStudentById(studentId);
        
        if (student != null) {
            System.out.println("\nStudent found: " + student.displayInfo());
        } else {
            System.out.println("No student found with that ID.");
        }
    }

    // Display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available in the system yet.");
        } else {
            System.out.println("\nHere are all the students in the system:");
            for (Student student : students) {
                System.out.println(student.displayInfo());
            }
        }
    }

    // Validate the student data before adding or editing
    private static boolean isValidStudentData(String studentId, String name, int age, String major) {
        return !(studentId.isEmpty() || name.isEmpty() || age <= 0 || major.isEmpty());
    }

    // Find a student by their ID
    private static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Load students from the file at the beginning of the program
    private static void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("We couldn’t load student data: " + e.getMessage());
        }
    }

    // Save students to the file when exiting the program
    private static void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(String.format("%s,%s,%d,%s%n", student.getStudentId(), student.getName(), student.getAge(), student.getMajor()));
            }
            System.out.println("\nAll changes have been saved. Your data is safe!");
        } catch (IOException e) {
            System.out.println("Oops! We couldn’t save your data: " + e.getMessage());
        }
    }

    // Student class to hold student data
    public static class Student {
        private String studentId;
        private String name;
        private int age;
        private String major;

        // Constructor
        public Student(String studentId, String name, int age, String major) {
            this.studentId = studentId;
            this.name = name;
            this.age = age;
            this.major = major;
        }

        // Getters and setters
        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        // Display student information
        public String displayInfo() {
            return String.format("ID: %s, Name: %s, Age: %d, Major: %s", studentId, name, age, major);
        }
    }
}
