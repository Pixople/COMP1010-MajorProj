public class TipsAndTricksScreen {

    /*
     * This method displays password tips and examples to the user
     * It takes in the head "nodes" of the easy, medium, and hard example linked lists
     * It also takes in the VaultClient to display messages to the user
     * It calculates and displays the average length of passwords in each category
     * Finally, it provides general tips for creating strong passwords
     */

    // Public static void to show the tips and tricks screen
    public static void show(Example easy, Example med, Example hard, VaultClient client) {
        client.showMessage("\n--- Password Tips & Examples ---");

        // EASY EXAMPLE PASSWORDS
        client.showMessage("\nEasy Passwords:");
        Example curr = easy;

        // Loop through and display easy passwords
        while (curr != null) {
            client.showMessage("  " + curr.password + " (Length: " + curr.length + ")");
            curr = curr.next;
        }

        // Average length calculation
        int easyAvg = 0;
        if (getCount(easy) != 0) { // Make sure we don't divide by zero
            easyAvg = getTotalLength(easy) / getCount(easy);
        }
        client.showMessage("Average Length (Easy): " + easyAvg);

        // MEDIUM EXAMPLE PASSWORDS
        client.showMessage("\nMedium Passwords:");
        curr = med;

        // Loop through and display medium passwords
        while (curr != null) {
            client.showMessage("  " + curr.password + " (Length: " + curr.length + ")");
            curr = curr.next;
        }

        // Average length calculation
        int medAvg = 0;
        if (getCount(med) != 0) { // Make sure we don't divide by zero
            medAvg = getTotalLength(med) / getCount(med);
        }
        client.showMessage("Average Length (Medium): " + medAvg);

        // HARD EXAMPLE PASSWORDS
        client.showMessage("\nHard Passwords:");
        curr = hard;

        // Loop through and display hard passwords
        while (curr != null) {
            client.showMessage("  " + curr.password + " (Length: " + curr.length + ")");
            curr = curr.next;
        }

        // Average length calculation
        int hardAvg = 0;
        if (getCount(hard) != 0) {
            hardAvg = getTotalLength(hard) / getCount(hard);
        }
        client.showMessage("Average Length (Hard): " + hardAvg);

        // General Tips
        client.showMessage("\nTips:");
        client.showMessage("- Use a mix of letters, numbers, and special characters.");
        client.showMessage("- Avoid common words and personal information.");
        client.showMessage("- Longer passwords are generally stronger.");
        client.showMessage("- Consider using a passphrase or random string.");
        client.showMessage("- Never reuse passwords across important accounts.");
        client.showMessage("- Don't trust a suspicious site with your personal data.");
    }

    // Recursive method to calculate total length and count for average
    private static int getTotalLength(Example e) {
        if (e == null) {
            return 0;
        }
        return e.length + getTotalLength(e.next);
    }

    private static int getCount(Example e) {
        if (e == null) {
            return 0;
        }
        return 1 + getCount(e.next);
    }
}
