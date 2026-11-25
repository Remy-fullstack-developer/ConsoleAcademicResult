import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main - console application to manage students and their academic results.
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("  STUDENT ACADEMIC RESULTS SYSTEM  ");
        System.out.println("===================================");

        int count = readInt(input, "How many students do you want to register: ", 1, Integer.MAX_VALUE);
        Student[] students = new Student[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Student " + (i + 1) + " of " + count + " ---");
            students[i] = readStudent(input);
        }

        System.out.println("\n========== STUDENT RESULTS ==========");
        for (Student s : students) {
            s.displayInformation();
        }

        printSummary(students);
        input.close();
    }

    // Reads one student from the keyboard, re-asking on invalid input
    private static Student readStudent(Scanner input) {
        while (true) {
            try {
                System.out.print("Registration number: ");
                String reg = input.nextLine();
                System.out.print("Name: ");
                String name = input.nextLine();
                int age = readInt(input, "Age: ", 1, 120);
                double m1 = readMark(input, "Mark for module 1 (0-100): ");
                double m2 = readMark(input, "Mark for module 2 (0-100): ");
                double m3 = readMark(input, "Mark for module 3 (0-100): ");
                return new Student(reg, name, age, m1, m2, m3);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid data: " + e.getMessage() + " Please enter this student again.\n");
            }
        }
    }

    private static int readInt(Scanner input, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = input.nextInt();
                input.nextLine(); // clear the newline
                if (value >= min && value <= max) return value;
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readMark(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = input.nextDouble();
                input.nextLine();
                if (value >= 0 && value <= 100) return value;
                System.out.println("Marks must be between 0 and 100.");
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Please enter a number.");
            }
        }
    }

    // Class summary: pass/fail counts and class average
    private static void printSummary(Student[] students) {
        int passed = 0;
        double total = 0;
        for (Student s : students) {
            if (s.getStatus().equals("PASS")) passed++;
            total += s.calculateAverage();
        }
        System.out.println("============== SUMMARY ==============");
        System.out.println("Total students : " + students.length);
        System.out.println("Passed         : " + passed);
        System.out.println("Failed         : " + (students.length - passed));
        System.out.printf("Class average  : %.2f%n", total / students.length);
        System.out.println("=====================================");
    }
}
