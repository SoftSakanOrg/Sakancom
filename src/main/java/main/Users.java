package main;

public class Users {
    private Integer usersID;
    private String username;
    private  String password;
    private String email;

    private String contactNum;

    public Users(){}

    public Users(Integer usersID, String username, String password, String email,String contactNum) {
        this.usersID = usersID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contactNum = contactNum;
    }


    public Integer getUsersID() {
        return usersID;
    }

    public void setUsersID(Integer usersID) {
        this.usersID = usersID;
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

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
