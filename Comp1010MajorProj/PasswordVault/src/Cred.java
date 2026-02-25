public class Cred {
    // Fields
    private String site;
    private String username;
    private String password;

    // Constructor which inistalises all the fields
    public Cred(String site, String username, String password) {
        this.site = site;
        this.username = username;
        this.password = password;
    }

    // Getters which are designed to return all fields without allowing for change as they are private
    public String getSite() {
        return site;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters which allow for the fields to be changed if needed
    public void setSite(String site) {
        this.site = site;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // To String method for easy display
    @Override
    public String toString() {
        return "Site: " + site + ", Username: " + username + ", Password: " + password;
    }

}
