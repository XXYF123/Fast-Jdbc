package com.xxyf.tools;


public class Username {

    private long id;
    private String account;
    private String password;
    private String username;
    private long administrator;
    private java.sql.Timestamp refdata;


    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Username{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", administrator=" + administrator +
                ", refdata=" + refdata +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public long getAdministrator() {
        return administrator;
    }

    public void setAdministrator(long administrator) {
        this.administrator = administrator;
    }


    public java.sql.Timestamp getRefdata() {
        return refdata;
    }

    public void setRefdata(java.sql.Timestamp refdata) {
        this.refdata = refdata;
    }

}
