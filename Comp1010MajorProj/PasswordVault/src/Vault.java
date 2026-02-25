import java.io.*;
import java.util.*;

public class Vault {

    // List to store credentials
    private ArrayList<Cred> credentials = new ArrayList<>();

    // Reference to the User object associated with this vault
    private User user;

    // Constructor that takes a User object
    public Vault(User user) {
        this.user = user;
    }

    // Default constructor (if needed elsewhere)
    public Vault() {}

    // Method to add a new credential
    public void addCredential(Cred cred) {
        credentials.add(cred);
    }

    // Method to save to file with encryption
    public void saveToFile(String filename, int shift) throws IOException {

        // try so that the writer is closed automatically
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // Simple For Loop to go through each credential and encrypt each field before writing to file
            for (Cred cred : credentials) {

                // Encrypt each field
                String encryptedPass = Encryption.encrypt(cred.getPassword(), shift);
                String encryptedUser = Encryption.encrypt(cred.getUsername(), shift);
                String encryptedSite = Encryption.encrypt(cred.getSite(), shift);
                
                // Write encrypted fields to file, separated by commas
                writer.write(encryptedSite + "," + encryptedUser + "," + encryptedPass);
                writer.newLine();
            }
        }
    }

    // Method to load from file with decryption
    public void loadFromFile(String filename, int shift) throws IOException { // IO Exception is for file handling

        // Clear existing credentials before loading new ones
        credentials.clear();

        // Check if file exists
        File file = new File(filename);
        if (!file.exists()) {
            // If file doesn't exist, do nothing (leave credentials empty)
            return;
        }

        // try so that the reader is closed automatically
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String site = Encryption.decrypt(parts[0], shift);
                    String username = Encryption.decrypt(parts[1], shift);
                    String password = Encryption.decrypt(parts[2], shift);
                    credentials.add(new Cred(site, username, password));
                }
            }
        }
    }

    // Method to display all credentials
    public void displayCredentials() {
        for (Cred cred : credentials) {
            System.out.println(cred);
        }
    }

    // Getter for credentials list -> for Vault Assistant
    public ArrayList<Cred> getCredentials() {
        return credentials;
    }

    // Getter for the associated User object
    public User getUser() {
        return user;
    }

    // Setter for the associated User object (optional)
    public void setUser(User user) {
        this.user = user;
    }
}
