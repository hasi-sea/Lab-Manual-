import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Choose an operation (1-5): ");
            
            int choice = scanner.nextInt();
            
            if (choice == 5) {
                System.out.println("Exiting calculator...");
                break;
            }
            
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.\n");
                continue;
            }
            
            System.out.print("Enter Number 1: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Enter Number 2: ");
            double num2 = scanner.nextDouble();
            
            if (choice == 1) {
                System.out.println("Result: " + (num1 + num2));
            } else if (choice == 2) {
                System.out.println("Result: " + (num1 - num2));
            } else if (choice == 3) {
                System.out.println("Result: " + (num1 * num2));
            } else if (choice == 4) {
                if (num2 == 0) {
                    System.out.println("Result: Cannot divide by zero");
                } else {
                    System.out.println("Result: " + (num1 / num2));
                }
            }
            System.out.println();
        }
        
        scanner.close();
    }
}
