/**
 * MAD204 - LAB01
 * Student.java
 * Author: Nithin Amin  (A00194432)
 * Date: 2025-09-21
 *
 * Description:
 * Model class representing a Student with id, name and 5 grades.
 * Provides methods to set a grade, compute the average, compute letter grade,
 * and a formatted toString output.
 */

import java.util.Arrays;

/**
 * Represents a student and their 5 grades.
 */
public class Student {
    /** Student full name */
    private String name;
    /** Student ID */
    private int id;
    /** Fixed-size array for grades (5 elements) */
    private double[] grades;

    /**
     * Constructs a new Student with a name and id. Initializes grades to 0.0.
     *
     * @param name the student's full name
     * @param id   the student's id number
     */
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new double[5]; // default values 0.0
    }

    /**
     * Set a grade at the specified index (0..4).
     *
     * @param index index in grades array (0-based)
     * @param value grade value (e.g., 0.0 - 100.0)
     * @throws IndexOutOfBoundsException if index not in [0..4]
     * @throws IllegalArgumentException  if value is negative
     */
    public void setGrade(int index, double value) {
        if (index < 0 || index >= grades.length) {
            throw new IndexOutOfBoundsException("Grade index must be 0..4");
        }
        if (value < 0.0) {
            throw new IllegalArgumentException("Grade cannot be negative");
        }
        grades[index] = value;
    }

    /**
     * Calculates the arithmetic mean (average) of the student's 5 grades.
     *
     * @return average as double
     */
    public double average() {
        double sum = 0.0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.length;
    }

    /**
     * Returns the letter grade based on the average:
     * A: >= 90
     * B: >= 80
     * C: >= 70
     * D: >= 60
     * F: otherwise
     *
     * @return char letter grade
     */
    public char letterGrade() {
        double avg = average();
        if (avg >= 90.0) {
            return 'A';
        } else if (avg >= 80.0) {
            return 'B';
        } else if (avg >= 70.0) {
            return 'C';
        } else if (avg >= 60.0) {
            return 'D';
        } else {
            return 'F';
        }
    }

    /**
     * Returns the student's ID.
     *
     * @return id int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's name.
     *
     * @return name String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the grades array to avoid exposing internal structure.
     *
     * @return double[] copy of grades
     */
    public double[] getGradesCopy() {
        return Arrays.copyOf(grades, grades.length);
    }

    /**
     * Returns a formatted string for the student:
     * "ID: 101, Name: Sam, Avg: 86.4 (B)"
     *
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Avg: %.2f (%c)", id, name, average(), letterGrade());
    }
}
