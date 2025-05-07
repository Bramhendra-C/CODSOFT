/*
        STUDENT GRADE CALCULATOR
Enter the number of subjects: 4
Enter marks for subject 1 (out of 100): 85
Enter marks for subject 2 (out of 100): 75
Enter marks for subject 3 (out of 100): 90
Enter marks for subject 4 (out of 100): 99

--- Result Summary ---
Total Marks: 349 / 400
Average Percentage: 87.25%
Grade: A
*/
import java.util.*;

public class StudentResultCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("\t STUDENT GRADE CALCULATOR");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid input. Marks should be between 0 and 100.");
                System.out.print("Re-enter marks for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }

            totalMarks += marks[i];
        }
        double averagePercentage = (double) totalMarks / numSubjects;
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results
        System.out.println("\n--- Result Summary ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
