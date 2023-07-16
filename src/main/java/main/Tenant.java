package main;

public class Tenant {
    private Integer tenantID;
private String username;
private  String password;
private String email;


    public Tenant() {
    }

    public Tenant(Integer tenantID,String username,String password,String email) {
        this.tenantID=tenantID;
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public Integer getTenantID() {
        return tenantID;
    }

    public void setTenantID(Integer tenantID) {
        this.tenantID = tenantID;
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
