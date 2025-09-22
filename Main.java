/**
 * MAD204 - LAB01
 * Main.java
 * Author: Nithin Amin (A00194432)
 * Date: 2025-09-21
 *
 * Description:
 * Console Gradebook & Utilities application.
 * - Add students (name + id)
 * - Enter grades (5 grades per student)
 * - Show list of students with averages and letter grades
 * - Utilities submenu demonstrating operator precedence, casting, and recursion
 *
 * Demonstrates:
 * - Classes/objects, methods (params/returns)
 * - Scanner input & validation with try/catch
 * - Conditionals, switch
 * - Loops: for, while, do-while, for-each
 * - Exceptions and recursion
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    // Storage for students
    private static ArrayList<Student> students = new ArrayList<>();

    /**
     * Program entry point.
     *
     * @param args not used
     */

    public static void main(String[] args) {
        System.out.println("Welcome to the Gradebook & Utilities App!");
        menuLoop();
        System.out.println("Exiting. Goodbye!");
        scanner.close();
    }

    /**
     * Main menu loop using while(true) as required.
     * Demonstrates switch and input parsing.
     */
    private static void menuLoop() {
        while (true) { // while → menu loop requirement
            printMainMenu();
            int choice = safeReadInt("Enter choice: ");
            switch (choice) {
                case 1:
                    addStudentFlow();
                    break;
                case 2:
                    enterGradesFlow();
                    break;
                case 3:
                    showAllStudentsFlow();
                    break;
                case 4:
                    utilitiesMenu();
                    break;
                case 5:
                    return; // Exit menu loop and program
                default:
                    System.out.println("Invalid choice. Please choose 1-5.");
            }
        }
    }

    /**
     * Prints the main menu options.
     */
    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1) Add student");
        System.out.println("2) Enter grades for a student");
        System.out.println("3) Show all students");
        System.out.println("4) Utilities");
        System.out.println("5) Exit");
    }

    /**
     * Flow to add student(s). Uses do-while to ask "Add another student? (y/n)" at least once.
     * Demonstrates do-while loop requirement.
     */
    private static void addStudentFlow() {
        boolean continueAdding;
        do {
            String name = safeReadString("Enter student name: ");
            int id = safeReadInt("Enter student id (integer): ");
            Student s = new Student(name, id);
            students.add(s);
            System.out.println("Added: " + s);

            // do-while prompt to add another student
            String yn = safeReadString("Add another student? (y/n): ");
            continueAdding = yn.equalsIgnoreCase("y");
        } while (continueAdding);
    }

    /**
     * Flow to enter grades for a chosen student.
     * Demonstrates for loop to input 5 grades.
     */
    private static void enterGradesFlow() {
        if (students.isEmpty()) {
            System.out.println("No students yet. Add students first.");
            return;
        }
        // Show students with index so user can choose
        System.out.println("Select a student by number:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d) %s (ID: %d)\n", i + 1, students.get(i).getName(), students.get(i).getId());
        }
        int pick = safeReadIntWithin("Choose student number: ", 1, students.size());
        Student s = students.get(pick - 1);

        System.out.printf("Entering 5 grades for %s (ID: %d)\n", s.getName(), s.getId());
        // for -> iterate 0..4 to input 5 grades
        for (int i = 0; i < 5; i++) {
            double grade = safeReadDouble(String.format("Grade %d (0-100): ", i + 1));
            try {
                s.setGrade(i, grade);
            } catch (Exception e) {
                // Shouldn't happen due to validation above, but catch to be safe
                System.out.println("Error setting grade: " + e.getMessage());
                i--; // retry same index
            }
        }
        System.out.println("Updated: " + s);
    }

    /**
     * Shows all students. Demonstrates for-each to print names from a snapshot list.
     */
    private static void showAllStudentsFlow() {
        if (students.isEmpty()) {
            System.out.println("No students to show.");
            return;
        }
        System.out.println("\n--- All Students ---");
        // for-each -> print student names from snapshot array/list
        List<Student> snapshot = new ArrayList<>(students); // snapshot example
        for (Student st : snapshot) {
            System.out.println(st.toString());
        }
    }

    /**
     * Utilities submenu with its own switch:
     * a) Operator demo
     * b) Type casting demo
     * c) Recursion countdown
     */
    private static void utilitiesMenu() {
        while (true) {
            System.out.println("\n--- Utilities ---");
            System.out.println("1) Operator precedence demo");
            System.out.println("2) Type casting demo");
            System.out.println("3) Recursion countdown");
            System.out.println("4) Back to main menu");
            int choice = safeReadInt("Choose utility: ");
            switch (choice) {
                case 1:
                    operatorDemo();
                    break;
                case 2:
                    castingDemo();
                    break;
                case 3:
                    recursionDemo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose 1-4.");
            }
        }
    }

    /**
     * Demonstrates operator precedence: 2 + 3 * 4 vs (2 + 3) * 4.
     * Also prints short explanation.
     */
    private static void operatorDemo() {
        int a = 2, b = 3, c = 4;
        int result1 = a + b * c;         // 2 + (3*4) = 14
        int result2 = (a + b) * c;       // (2+3)*4 = 20
        System.out.println("Operator demo:");
        System.out.printf("2 + 3 * 4 = %d (multiplication has higher precedence)\n", result1);
        System.out.printf("(2 + 3) * 4 = %d (parentheses change evaluation order)\n", result2);
        System.out.println("This demonstrates BODMAS/precedence: multiplication before addition unless parentheses are used.");
    }

    /**
     * Demonstrates safe widening (int->double) and narrowing (double->int) casting and truncation.
     */
    private static void castingDemo() {
        int i = 7;
        double widened = i; // implicit widening
        double d = 7.9;
        int narrowed = (int) d; // explicit narrowing - truncation
        System.out.println("Casting demo:");
        System.out.printf("int i = %d -> widened to double: %f\n", i, widened);
        System.out.printf("double d = %f -> narrowed to int (truncation): %d\n", d, narrowed);
        System.out.println("Note: narrowing discards fractional portion.");
    }

    /**
     * Demonstrates recursion by counting down from n to 0, then prints "Done!".
     * Guards against negative input.
     */
    private static void recursionDemo() {
        int n = safeReadInt("Enter non-negative integer for countdown: ");
        if (n < 0) {
            System.out.println("Negative number; cannot countdown. Please enter 0 or positive.");
            return;
        }
        System.out.println("Countdown:");
        countdown(n);
    }

    /**
     * Recursive method that prints n down to 0 and then "Done!".
     *
     * @param n starting integer >= 0
     */
    private static void countdown(int n) {
        // base case
        if (n < 0) {
            // guard - shouldn't be reached because the caller checks, but included for safety
            return;
        }
        System.out.println(n);
        if (n == 0) {
            System.out.println("Done!");
            return;
        }
        // recursive call
        countdown(n - 1);
    }

    // ----------------------------
    // Input helpers with validation and exception handling
    // ----------------------------

    /**
     * Safely reads an integer from Scanner, re-prompting on invalid input.
     *
     * @param prompt prompt message
     * @return parsed int
     */
    private static int safeReadInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = scanner.nextInt();
                scanner.nextLine(); // consume trailing newline
                return val;
            } catch (InputMismatchException ime) {
                System.out.println("Invalid integer. Try again.");
                scanner.nextLine(); // discard invalid token
            }
        }
    }

    /**
     * Safely reads an integer within min..max (inclusive).
     *
     * @param prompt prompt message
     * @param min    minimum allowed (inclusive)
     * @param max    maximum allowed (inclusive)
     * @return valid integer in range
     */
    private static int safeReadIntWithin(String prompt, int min, int max) {
        while (true) {
            int val = safeReadInt(prompt);
            if (val >= min && val <= max) {
                return val;
            }
            System.out.printf("Please enter a number between %d and %d.\n", min, max);
        }
    }

    /**
     * Safely reads a double value from Scanner, re-prompting on invalid input.
     *
     * @param prompt prompt message
     * @return parsed double
     */
    private static double safeReadDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (val < 0.0) {
                    System.out.println("Please enter a non-negative number.");
                    continue;
                }
                return val;
            } catch (InputMismatchException ime) {
                System.out.println("Invalid number. Try again.");
                scanner.nextLine(); // discard invalid token
            }
        }
    }

    /**
     * Safely reads a non-empty string line.
     *
     * @param prompt prompt message
     * @return entered string (trimmed)
     */
    private static String safeReadString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s != null) {
                s = s.trim();
            }
            if (s == null || s.isEmpty()) {
                System.out.println("Please enter non-empty text.");
            } else {
                return s;
            }
        }
    }
}
