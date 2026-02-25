import java.io.*;

public class VaultDriver {
    public static void main(String[] args) throws Exception {

        // Create VaultClient for user interaction
        VaultClient client = new VaultClient();

        // File paths and encryption shift
        String allVaultsFolder = System.getProperty("user.dir") + File.separator + "AllVaults" + File.separator;
        String usersFile = allVaultsFolder + "users.txt";
        int shift = 12; // IMPORTANT!

        // Example linked lists for Tips and Tricks
        PasswordExamples examples = new PasswordExamples();

        // Ensure AllVaults folder exists
        new File(allVaultsFolder).mkdirs();

        boolean running = true;
        while (running) {

            // Login loop 
            String[] creds = loginLoop(client, usersFile, shift); // refers to the function loginLoop below
            String username = creds[0];
            String vaultPassword = creds[1];

            // File path for user's vault file
            String filename = allVaultsFolder + username + "_vault.txt";
            Vault vault = new Vault(new User(username, vaultPassword));

            // Load existing credentials if file exists
            loadVaultIfExists(vault, filename, shift, client);

            // Main menu loop for logged-in user
            boolean inMenu = true;
            while (inMenu) {

                // Display menu and get choice call me a delegator
                String choice = showMenuAndGetChoice(client);

                // Handle user choice
                int action = handleMenuChoice(choice, vault, filename, shift, client, examples);

                /*
                 * action meanings:
                 * 0 = continue in menu
                 * 1 = logout
                 * 2 = exit program
                 */

                if (action == 1) { 
                    inMenu = false; 
                } else if (action == 2) { 
                    inMenu = false;
                    running = false; 
                } // else continue inMenu
            }
        }

        // Close resources
        client.close();
    }

    /*  
        * Calls for a new VaultClient object to handle 
        * all the user interactions and inputs in a seperate file! 
    */

    private static String[] loginLoop(VaultClient client, String usersFile, int shift) throws IOException {
        // Login loop variables
        boolean loggedIn = false;
        String username = null;
        String vaultPassword = null;

        // Ensure users parent folder exists (super defensive you see)
        ensureUsersDirExists(usersFile);

        while (!loggedIn) {
            // Prompt for credentials
            String[] inputs = promptForCredentials(client); // function below
            username = inputs[0];
            vaultPassword = inputs[1];

            // Encrypt input for comparison
            String encryptedUsername = Encryption.encrypt(username, shift);
            String encryptedPassword = Encryption.encrypt(vaultPassword, shift);

            // Look up stored password for this encrypted username (delegated)
            String storedPassword = findStoredPassword(encryptedUsername, usersFile);

            if (storedPassword != null) {
                // User exists -> verify
                if (verifyUser(encryptedPassword, storedPassword)) {
                    client.showMessage("Welcome back, " + username + "!");
                    loggedIn = true;
                } else {
                    client.showMessage("Incorrect password. Access denied.");
                    // loop continues prompting again
                }
            } else {
                // User not found -> register new user (delegated)
                registerNewUser(encryptedUsername, encryptedPassword, usersFile);
                client.showMessage("New user created!");
                loggedIn = true;
            }
        }

        return new String[] { username, vaultPassword };
    }

    /* 
     * Helper methods for loginLoop
     */

    // Prompt the user for username and vault password
    private static String[] promptForCredentials(VaultClient client) {
        String username = client.getUsername();
        String vaultPassword = client.getVaultPassword();
        return new String[] { username, vaultPassword };
    }

    // Ensure the parent folder for usersFile exists
    private static void ensureUsersDirExists(String usersFile) {
        File parent = new File(usersFile).getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
    }

    // Find stored encrypted password for an encrypted username; returns null if not found
    private static String findStoredPassword(String encryptedUsername, String usersFile) throws IOException {
        File file = new File(usersFile);
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2 && parts[0].equals(encryptedUsername)) {
                    return parts[1];
                }
            }
        }
        return null;
    }

    // Verify that the provided encrypted password matches the stored one
    private static boolean verifyUser(String encryptedInputPassword, String storedEncryptedPassword) {
        return encryptedInputPassword.equals(storedEncryptedPassword);
    }

    // Register a new user by appending encryptedUsername,encryptedPassword to usersFile
    private static void registerNewUser(String encryptedUsername, String encryptedPassword, String usersFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile, true))) {
            writer.write(encryptedUsername + "," + encryptedPassword);
            writer.newLine();
        }
    }

    // Load existing credentials if file exists
    private static void loadVaultIfExists(Vault vault, String filename, int shift, VaultClient client) throws IOException {
        File vaultFile = new File(filename);
        if (vaultFile.exists()) {
            vault.loadFromFile(filename, shift);
            client.showMessage("Loaded existing credentials.");
        }
    }

    // MENU HANDLING

    // Display menu and get user choice
    private static String showMenuAndGetChoice(VaultClient client) {
        // Display menu
        client.showMessage("\n--- Vault Menu ---");
        client.showMessage("1. Add new credential");
        client.showMessage("2. View credentials");
        client.showMessage("3. Log out");
        client.showMessage("4. Exit");
        client.showMessage("5. Tips for Your Passwords");

        // Get user choice
        System.out.println();
        System.out.print("Choose an option (1-5): ");
        return client.getMenuChoice();
    }

    // Handle menu choice. Returns: 0 = continue, 1 = logout, 2 = exit
    private static int handleMenuChoice(String choice, Vault vault, String filename, int shift, VaultClient client, PasswordExamples examples) throws IOException {
        if (choice.equals("1")) {
            return handleAddCredential(vault, filename, shift, client);

        } else if (choice.equals("2")) {
            return handleViewCredentials(vault, client);

        } else if (choice.equals("3")) {
            return handleLogout(client);

        } else if (choice.equals("4")) {
            return handleExit(client);

        } else if (choice.equals("5")) {
            return handleTips(client, examples);

        } else {
            // Invalid option ERROR HANDLING god bless this
            System.out.println();
            client.showMessage("Invalid option. Please choose 1-5.");
            return 0;
        }
    }

    // Menu choice METHODS

    // Handle "Add new credential" menu choice
    private static int handleAddCredential(Vault vault, String filename, int shift, VaultClient client) throws IOException {
        // Add new credential
        System.out.println();
        vault.addCredential(client.getCredentialInput());
        vault.saveToFile(filename, shift);
        System.out.println();
        client.showMessage("Credential added and saved.");
        client.waitForEnter(); // Pause until user presses Enter
        return 0; // continue in menu
    }

    // Handle "View credentials" menu choice
    private static int handleViewCredentials(Vault vault, VaultClient client) {
        // View credentials
        System.out.println();
        client.showMessage("Your stored credentials:");
        vault.displayCredentials();
        client.waitForEnter(); // Pause until user presses Enter
        return 0; // continue in menu
    }

    // Handle "Log out" menu choice
    private static int handleLogout(VaultClient client) {
        // Log out
        System.out.println();
        client.showMessage("Logging out...");
        System.out.println();
        return 1; // logout
    }

    // Handle "Exit" menu choice
    private static int handleExit(VaultClient client) {
        // Exit program
        System.out.println();
        client.showMessage("Exiting program. Goodbye! ◝(ᵔᵕᵔ)◜");
        return 2; // exit
    }

    // Handle "Tips for Your Passwords" menu choice
    private static int handleTips(VaultClient client, PasswordExamples examples) {
        // Tips and Tricks Screen!
        System.out.println();
        TipsAndTricksScreen.show(examples.easyHead, examples.medHead, examples.hardHead, client);
        client.waitForEnter(); // Pause until user presses Enter
        return 0; // continue in menu
    }

    // and boom that's the end of the file
}
