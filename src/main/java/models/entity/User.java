package models.entity;

/**
 * Created by Miha on 03.09.2017.
 */
public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private int auth_lvl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuth_lvl() {
        return auth_lvl;
    }

    public void setAuth_lvl(int auth_lvl) {
        this.auth_lvl = auth_lvl;
    }
}
