import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "students.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            
            for (int i = 1; i <= 3; i++) {
                System.out.println("Enter details for Student " + i + ":");
                System.out.print("Roll Number: ");
                String roll = scanner.nextLine();
                
                System.out.print("Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Marks: ");
                String marks = scanner.nextLine();
                
                bw.write(roll + "," + name + "," + marks);
                bw.newLine();
            }
            bw.close();
            System.out.println("\nRecords successfully saved to " + filename + ".\n");
            
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }

        System.out.println("--- All Student Records ---");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    System.out.println("Roll No: " + data[0] + " | Name: " + data[1] + " | Marks: " + data[2]);
                }
            }
            br.close();
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }

        System.out.print("Enter Roll Number to search: ");
        String searchRoll = scanner.nextLine();
        boolean isFound = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].equalsIgnoreCase(searchRoll)) {
                    System.out.println("\nStudent Found!");
                    System.out.println("Roll Number : " + data[0]);
                    System.out.println("Name        : " + data[1]);
                    System.out.println("Marks       : " + data[2]);
                    isFound = true;
                    break;
                }
            }
            br.close();
            
            if (!isFound) {
                System.out.println("\nStudent with Roll Number " + searchRoll + " not found.");
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred while searching the file.");
        }

        scanner.close();
    }
}
