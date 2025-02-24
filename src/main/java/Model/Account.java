package Model;

public class Account {
    private int userID;
    private String email;
    private String role;
    private String userName;
    private String password;
    private String status;

    public Account() {
    }

    public Account(int userID, String email, String role, String userName, String password, String status) {
        this.userID = userID;
        this.email = email;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
