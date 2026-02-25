import org.junit.Test;
import static org.junit.Assert.*;

public class EncryptionTest {

    // Test ENCRYPTION and DECRYPTION with simple case
    @Test
    public void testEncryptDecryptSimple() {
        String original = "Password123";
        int shift = 3;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Test ENCRYPTION and DECRYPTION with mixed case
    @Test
    public void testEncryptDecryptWithSymbols() {
        String original = "Hello!@#";
        int shift = 5;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with empty string
    @Test
    public void testEncryptDecryptEmptyString() {
        String original = "";
        int shift = 10;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with negative shift
    @Test
    public void testEncryptDecryptNegativeShift() {
        String original = "Negative";
        int shift = -3;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with large shift
    @Test
    public void testEncryptDecryptLargeShift() {
        String original = "LargeShift";
        int shift = 222;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with all printable ASCII characters
    @Test
    public void testEncryptDecryptAllPrintableASCII() {
        StringBuilder sb = new StringBuilder();
        for (char c = 32; c <= 126; c++) {
            sb.append(c);
        }
        String original = sb.toString();
        int shift = 20;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with only special characters
    @Test
    public void testEncryptDecryptSpecialCharacters() {
        String original = "!@#$%^&*()_+-=[]{}|;':\",.<>/?";
        int shift = 7;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with only numbers
    @Test
    public void testEncryptDecryptNumbers() {
        String original = "0123456789";
        int shift = 15;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with a single character
    @Test
    public void testEncryptDecryptSingleCharacter() {
        String original = "A";
        int shift = 1;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }

    // Edge case: Test ENCRYPTION and DECRYPTION with a very long string
    @Test
    public void testEncryptDecryptVeryLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ((i % 95) + 32)); // Cycle through printable ASCII
        }
        String original = sb.toString();
        int shift = 42;
        String encrypted = Encryption.encrypt(original, shift);
        String decrypted = Encryption.decrypt(encrypted, shift);
        assertEquals(original, decrypted);
    }
}
