import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class VaultTest {

    // Test ADDING a normal credential
    @Test
    public void testAddCredentialNormal() {
        Vault vault = new Vault();
        Cred cred = new Cred("site", "user", "pass");
        vault.addCredential(cred);
        assertEquals(1, vault.getCredentials().size());
        assertEquals("site", vault.getCredentials().get(0).getSite());
    }

    // Edge case: Test ADDING a credential with empty strings
    @Test
    public void testAddCredentialEmptyStrings() {
        Vault vault = new Vault();
        Cred cred = new Cred("", "", "");
        vault.addCredential(cred);
        assertEquals(1, vault.getCredentials().size());
        assertEquals("", vault.getCredentials().get(0).getSite());
    }

    // Edge case: Test ADDING a credential with null values (should handle gracefully god bless)
    @Test
    public void testAddCredentialNulls() {
        Vault vault = new Vault();
        Cred cred = new Cred(null, null, null);
        vault.addCredential(cred);
        assertEquals(1, vault.getCredentials().size());
        assertNull(vault.getCredentials().get(0).getSite());
    }

    // Test SAVING and LOADING credentials with special characters
    @Test
    public void testSaveLoadSpecialChars() throws Exception {
        Vault vault = new Vault();
        int shift = 12;
        String filename = "test_vault_special.txt";
        vault.addCredential(new Cred("!@#$%^", "user!@#", "pa$$word"));
        vault.saveToFile(filename, shift);

        Vault loadedVault = new Vault();
        loadedVault.loadFromFile(filename, shift);

        assertEquals(1, loadedVault.getCredentials().size());
        assertEquals("!@#$%^", loadedVault.getCredentials().get(0).getSite());
        new File(filename).delete();
    }

    // Edge case: Test SAVING and LOADING credentials with long strings
    @Test
    public void testSaveLoadLongStrings() throws Exception {
        Vault vault = new Vault();
        int shift = 12;
        String filename = "test_vault_long.txt";
        String longSite = "s".repeat(1000);
        String longUser = "u".repeat(1000);
        String longPass = "p".repeat(1000);
        vault.addCredential(new Cred(longSite, longUser, longPass));
        vault.saveToFile(filename, shift);

        Vault loadedVault = new Vault();
        loadedVault.loadFromFile(filename, shift);

        assertEquals(1, loadedVault.getCredentials().size());
        assertEquals(longSite, loadedVault.getCredentials().get(0).getSite());
        new File(filename).delete();
    }

    // Edge case: Test LOADING from a non-existent file (should not throw)
    @Test
    public void testLoadNonExistentFile() throws Exception {
        Vault vault = new Vault();
        vault.loadFromFile("does_not_exist.txt", 12);
        assertEquals(0, vault.getCredentials().size());
    }

    // Edge case: Test SAVING and LOADING credentials with numbers and border characters
    @Test
    public void testSaveLoadNumbersAndBorders() throws Exception {
        Vault vault = new Vault();
        int shift = 12;
        String filename = "test_vault_numbers.txt";
        vault.addCredential(new Cred("1234567890", "user0", "pass0"));
        vault.addCredential(new Cred("~`", "|", "\\"));
        vault.saveToFile(filename, shift);

        Vault loadedVault = new Vault();
        loadedVault.loadFromFile(filename, shift);

        assertEquals(2, loadedVault.getCredentials().size());
        assertEquals("1234567890", loadedVault.getCredentials().get(0).getSite());
        assertEquals("~`", loadedVault.getCredentials().get(1).getSite());
        new File(filename).delete();
    }

    // Test ADDING multiple credentials and checking order
    @Test
    public void testAddMultipleCredentialsOrder() {
        Vault vault = new Vault();
        vault.addCredential(new Cred("site1", "user1", "pass1"));
        vault.addCredential(new Cred("site2", "user2", "pass2"));
        vault.addCredential(new Cred("site3", "user3", "pass3"));
        assertEquals("site1", vault.getCredentials().get(0).getSite());
        assertEquals("site3", vault.getCredentials().get(2).getSite());
    }

    // Edge case: Test DISPLAYING credentials with empty vault (should not throw)
    @Test
    public void testDisplayCredentialsEmptyVault() {
        Vault vault = new Vault();
        vault.displayCredentials();
        assertEquals(0, vault.getCredentials().size());
    }

    // Edge case: Test ADDING max capacity with borderline large number
    @Test
    public void testMaxCapacityBorderline() {
        Vault vault = new Vault();
        int max = 1000; // Borderline for reasonable test
        for (int i = 0; i < max; i++) {
            vault.addCredential(new Cred("site" + i, "user" + i, "pass" + i));
        }
        assertEquals(max, vault.getCredentials().size());
        assertEquals("site999", vault.getCredentials().get(999).getSite());
    }
}
