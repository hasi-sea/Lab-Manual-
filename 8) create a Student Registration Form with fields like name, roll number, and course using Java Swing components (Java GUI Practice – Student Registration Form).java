import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("--- Student Registration Form ---");
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Roll Number: ");
            String rollNo = scanner.nextLine().trim();
            
            System.out.print("Enter Gender (M/F): ");
            String genderInput = scanner.nextLine().trim().toUpperCase();
            String gender = "";
            if (genderInput.equals("M")) {
                gender = "Male";
            } else if (genderInput.equals("F")) {
                gender = "Female";
            }
            
            System.out.println("Courses: 1. B.Tech AI&DS | 2. B.Tech CSE | 3. B.Tech IT | 4. B.E ECE");
            System.out.print("Select Course (1-4): ");
            String courseChoice = scanner.nextLine().trim();
            String course = "";
            if (courseChoice.equals("1")) course = "B.Tech AI&DS";
            else if (courseChoice.equals("2")) course = "B.Tech CSE";
            else if (courseChoice.equals("3")) course = "B.Tech IT";
            else if (courseChoice.equals("4")) course = "B.E ECE";
            
            System.out.print("Enter Hobbies (e.g., Reading, Sports) or leave blank: ");
            String hobbies = scanner.nextLine().trim();
            if (hobbies.isEmpty()) {
                hobbies = "None";
            }
            
            if (name.isEmpty() || rollNo.isEmpty() || gender.isEmpty() || course.isEmpty()) {
                System.out.println("\n[Validation Error] Please fill all mandatory fields correctly!");
                System.out.println("Clearing form to try again...\n");
                continue;
            }
            
            System.out.println("\n--- Submitted Student Registration Details ---");
            System.out.println("Name: " + name);
            System.out.println("Roll Number: " + rollNo);
            System.out.println("Gender: " + gender);
            System.out.println("Course: " + course);
            System.out.println("Hobbies: " + hobbies);
            
            System.out.print("\nDo you want to clear and register another? (Yes/No): ");
            String action = scanner.nextLine().trim().toLowerCase();
            if (!action.equals("yes") && !action.equals("y")) {
                System.out.println("Exiting Registration Form...");
                break;
            }
            System.out.println("\nClearing fields...\n");
        }
        
        scanner.close();
    }
}
