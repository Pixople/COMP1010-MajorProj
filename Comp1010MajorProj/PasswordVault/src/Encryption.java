import java.util.ArrayList;

public class Encryption {

    // Encrypts every character (letters, digits, special chars) by shifting its ASCII value
    public static String encrypt(String password, int shift) {
        
        /*
         * Creates an ArrayList to store the encrypted characters
         * Each character in the original password is shifted by the specified amount
         * Handles wrap-around for printable ASCII characters (32 to 126) (thanks google)
         * Wraps around using modulo arithmetic
         * Finally, converts the ArrayList back to a String and returns it
         */

        int range = 95; // Printable ASCII range from 32 to 126
        shift = shift % range;
        ArrayList<Character> encryptedChars = new ArrayList<>();

        /*
         * For each character in the password:
         * If it's a printable ASCII character (32 to 126), shift it
         * Wrap around using modulo arithmetic
         * Non-printable characters are left unchanged
         */
        
        for (char c : password.toCharArray()) {
            if (c >= 32 && c <= 126) {
                c = (char) (((c - 32 + shift + range) % range) + 32);
            }
            // Non-printable chars are left unchanged
            encryptedChars.add(c);
        }
        String encrypted = "";
        for (char ch : encryptedChars) {
            encrypted += ch;
        }
        return encrypted;
    }

    // Decrypts by shifting back in the printable ASCII range
    public static String decrypt(String encryptedPassword, int shift) {
        int range = 95;
        return encrypt(encryptedPassword, range - (shift % range));
    }
}
