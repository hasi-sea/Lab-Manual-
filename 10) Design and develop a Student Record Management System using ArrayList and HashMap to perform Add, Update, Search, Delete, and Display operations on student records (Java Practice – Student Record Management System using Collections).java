import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Student {
    int rollNo;
    String name;
    double percentage;

    public Student(int rollNo, String name, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
    }

    public String toString() {
        return "Roll No: " + rollNo + " | Name: " + name + " | Percentage: " + percentage + "%";
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentList = new ArrayList<>();
        HashMap<Integer, Student> studentMap = new HashMap<>();

        Student s1 = new Student(101, "Hashim", 91.5);
        Student s2 = new Student(102, "Aabith Ismail", 88.0);
        Student s3 = new Student(103, "Daanish", 92.5);

        studentList.add(s1);
        studentMap.put(s1.rollNo, s1);

        studentList.add(s2);
        studentMap.put(s2.rollNo, s2);

        studentList.add(s3);
        studentMap.put(s3.rollNo, s3);

        System.out.println("--- All Student Records (ArrayList) ---");
        for (Student s : studentList) {
            System.out.println(s);
        }

        int searchRoll = 102; 
        System.out.println("\nSearching for Roll Number in HashMap: " + searchRoll);
        
        System.out.println("--- Search Result ---");
        if (studentMap.containsKey(searchRoll)) {
            System.out.println("Record Found: " + studentMap.get(searchRoll));
        } else {
            System.out.println("Student with Roll Number " + searchRoll + " not found.");
        }

        System.out.println("\nRemoving Student Roll No 101 from the ArrayList...");
        studentList.remove(s1);

        System.out.println("\n--- Updated Student Records (ArrayList) ---");
        for (Student s : studentList) {
            System.out.println(s);
        }

        System.out.println("\n--- All Entries in HashMap ---");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " => Value: [" + entry.getValue() + "]");
        }
    }
}
