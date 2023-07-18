package main;


public class Owners {

    private Integer ownerID;
    private String username;
    private  String password;
    private String email;


    public Owners(){

    }
    public Owners(Integer ownerID, String username, String password, String email) {
        this.ownerID = ownerID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
