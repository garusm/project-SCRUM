package pl.coderslab.model;

public class Admin {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superadmin;
    private int enable;

    @Override
    public String toString() {
        return "Admin [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", email=" + email + ", superadmin=" + superadmin + ", enable=" + enable + "]";
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin(int id) {
        this.id = id;
    }

    public Admin(String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public int getEnable() {
        return enable;
    }
}
