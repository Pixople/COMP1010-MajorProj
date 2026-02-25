public class User {
    // Fields
    String username, password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Gettters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // To String method for easy display
    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password;
    }
}
