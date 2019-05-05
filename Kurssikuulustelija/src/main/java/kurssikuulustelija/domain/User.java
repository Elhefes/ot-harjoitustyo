package kurssikuulustelija.domain;

/**
 *
 * @author henripal
 */
public class User {

    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "#" + this.id + " Username: " + this.username + " Password: " + this.password;
    }

}
