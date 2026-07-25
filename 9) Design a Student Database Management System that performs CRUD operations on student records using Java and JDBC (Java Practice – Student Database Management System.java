import java.util.ArrayList;
import java.util.List;

class Student {
    int rollNo;
    String name;
    String department;
    double marks;

    public Student(int rollNo, String name, String department, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> studentDatabase = new ArrayList<>();

        System.out.println("Connecting to simulated database...");
        System.out.println("Connected successfully.\n");

        studentDatabase.add(new Student(101, "Alice", "AI&DS", 88.5));
        studentDatabase.add(new Student(102, "Bob", "AI&DS", 92.0));
        System.out.println("-> Inserted 2 student records successfully.");

        int targetRollNo = 101;
        double newMarks = 95.0;
        for (Student s : studentDatabase) {
            if (s.rollNo == targetRollNo) {
                s.marks = newMarks;
                break;
            }
        }
        System.out.println("-> Updated marks for Roll Number " + targetRollNo + ".");

        System.out.println("\n--- Search Result for Roll No 101 ---");
        boolean found = false;
        for (Student s : studentDatabase) {
            if (s.rollNo == 101) {
                System.out.println("Roll No: " + s.rollNo + 
                                   " | Name: " + s.name + 
                                   " | Dept: " + s.department + 
                                   " | Marks: " + s.marks);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }

        System.out.println("\n--- All Student Records ---");
        for (Student s : studentDatabase) {
            System.out.println("Roll No: " + s.rollNo + 
                               " | Name: " + s.name + 
                               " | Dept: " + s.department + 
                               " | Marks: " + s.marks);
        }
        
        System.out.println("\nDatabase connection closed.");
    }
}
