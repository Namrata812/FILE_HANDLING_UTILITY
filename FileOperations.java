import java.io.*;
import java.util.Scanner;

public class FileOperations {
    public static void main(String[] args) {
        /*
        A Scanner is used to get input from the user.
        The file path is obtained from the user.
        A menu is displayed, and the user's choice is processed.
         */
        Scanner scanner = new Scanner(System.in);
        String filePath = getFilePath(scanner);
        //String filePath = "demo.txt";

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    readFile(filePath);
                    break;
                case 2:
                    writeToFile(filePath, scanner);
                    break;
                case 3:
                    modifyFile(filePath, scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //  getFilePath method to take filename/filepath from user
    public static String getFilePath(Scanner scanner) {
        System.out.print("Enter file path: ");
        return scanner.nextLine();
    }

    // printMenu method used to displays the menu options.
    public static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Read file");
        System.out.println("2. Write to file");
        System.out.println("3. Modify file");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // readFile method used to read and prints the content of the file.
    public static void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // writeToFile method used to add the user's text to the end of the file.
    public static void writeToFile(String filePath, Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            System.out.print("Enter text to append: ");
            String text = scanner.nextLine();
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // modifyFile method used to replaces all occurrences of a specific text with another text in the file.
    public static void modifyFile(String filePath, Scanner scanner) {
        System.out.print("Enter text to search for: ");
        String searchText = scanner.nextLine();
        System.out.print("Enter text to replace with: ");
        String replaceText = scanner.nextLine();

        try {
            String tempFile = filePath + ".temp";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace(searchText, replaceText);
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            new File(filePath).delete();
            new File(tempFile).renameTo(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
