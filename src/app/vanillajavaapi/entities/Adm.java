package app.vanillajavaapi.entities;

public class Adm {

    private static int incrementerID = 0;

    private int id;
    private String name;
    private String email;
    private String password;

    public Adm(String name, String email, String password) {
        this.id = ++incrementerID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
