import java.util.Scanner;

public class VaultClient {

    /*
        * User authentication and vault management
         * 1. Check if user exists in users.txt
         * 2. If exists, verify password
         * 3. If not, create new user and store encrypted credentials
         * 4. Use Caesar cipher for encryption/decryption
         * 5. Each user has a separate vault file for their credentials -> important!
         * 6. Vault file is named <username>_vault.txt, stored in encrypted form
         * 7. Allow users to add new credentials to their vault
         * 8. Save and load vault data with encryption -> Display stored credentials in decrypted form
         * 9. Use a shift of 3 for the Caesar cipher
    */

    // Scanner for user input
    private Scanner scanner;

    // Constructor
    public VaultClient() {
        scanner = new Scanner(System.in); // Initialise the scanner
    }

    // Get the username for the user
    public String getUsername() {
        System.out.print("Enter your username: ");
        return scanner.nextLine();
    }

    // Get the vault password for the user
    public String getVaultPassword() {
        System.out.print("Enter your vault password: ");
        return scanner.nextLine();
    }

    // Get menu choice from user
    public String getMenuChoice() {
        return scanner.nextLine().trim();
    }

    // Get the credential input from the user
    public Cred getCredentialInput() {
        System.out.print("Enter site: ");
        String site = scanner.nextLine();
        System.out.print("Enter username: ");
        String credUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String credPassword = scanner.nextLine();
        return new Cred(site, credUsername, credPassword);
    }

    // Show a message to the user
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    // Wait for user to press Enter to continue
    public void waitForEnter() {
        System.out.println();
        System.out.print("Press [Enter] to continue...");
        scanner.nextLine();
    }

    // Close the scanner
    public void close() {
        scanner.close();
    }
}
