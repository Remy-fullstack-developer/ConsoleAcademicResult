/**
 * Student - represents a university student and their academic results.
 */
public class Student {

    // Private attributes
    private String registrationNumber;
    private String name;
    private int age;
    private double mark1;
    private double mark2;
    private double mark3;

    // Parameterized constructor (uses setters so validation applies everywhere)
    public Student(String registrationNumber, String name, int age,
                   double mark1, double mark2, double mark3) {
        setRegistrationNumber(registrationNumber);
        setName(name);
        setAge(age);
        setMark1(mark1);
        setMark2(mark2);
        setMark3(mark3);
    }

    // Getters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMark1() { return mark1; }
    public double getMark2() { return mark2; }
    public double getMark3() { return mark3; }

    // Setters (with basic validation)
    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be empty.");
        }
        this.registrationNumber = registrationNumber.trim();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0.");
        }
        this.age = age;
    }

    public void setMark1(double mark1) { this.mark1 = checkMark(mark1); }
    public void setMark2(double mark2) { this.mark2 = checkMark(mark2); }
    public void setMark3(double mark3) { this.mark3 = checkMark(mark3); }

    private double checkMark(double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        return mark;
    }

    // Returns the average of the three module marks
    public double calculateAverage() {
        return (mark1 + mark2 + mark3) / 3;
    }

    // "PASS" if average >= 50, otherwise "FAIL"
    public String getStatus() {
        return calculateAverage() >= 50 ? "PASS" : "FAIL";
    }

    // Displays all student information, including average and status
    public void displayInformation() {
        System.out.println("-----------------------------------");
        System.out.println("Registration Number : " + registrationNumber);
        System.out.println("Name                : " + name);
        System.out.println("Age                 : " + age);
        System.out.printf("Module 1 Mark       : %.2f%n", mark1);
        System.out.printf("Module 2 Mark       : %.2f%n", mark2);
        System.out.printf("Module 3 Mark       : %.2f%n", mark3);
        System.out.printf("Average             : %.2f%n", calculateAverage());
        System.out.println("Status              : " + getStatus());
        System.out.println("-----------------------------------");
    }
}
