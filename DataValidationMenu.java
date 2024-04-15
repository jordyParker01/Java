import java.util.Scanner;

public class DataValidationMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Choose an option:");
            System.out.println("1. Validate last name");
            System.out.println("2. Validate email address");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    validateLastName(scanner);
                    break;
                case 2:
                    validateEmail(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void validateLastName(Scanner scanner) {
        System.out.print("Enter a last name: ");
        String lastName = scanner.next();

        if (verifyLastName(lastName)) {
            System.out.println("Last name is valid.");
        } else {
            System.out.println("Last name contains invalid characters.");
        }
    }

    private static void validateEmail(Scanner scanner) {
        System.out.print("Enter an email address: ");
        String email = scanner.next();

        if (verifyEmail(email)) {
            System.out.println("Email address is valid.");
        } else {
            System.out.println("Invalid email address.");
        }
    }

    private static boolean verifyLastName(String lname) {
        lname = lname.trim();
        if (lname == null || lname.equals("")) {
            return false; // Empty or null string
        }
        // Use regex to check if the string contains only letters (uppercase or lowercase)
        return lname.matches("[a-zA-Z]*");
    }

    private static boolean verifyEmail(String email) {
        email = email.trim();
        if (email == null || email.equals("")) {
            return false; // Empty or null email
        }
        // Use a simplified regex for email validation (not perfect, but decent)
        String regex = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
        return email.matches(regex);
    }
}
