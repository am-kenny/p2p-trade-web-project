package com.mycompany.p2ptradewebproject.persistence.entities;

public class UserEntity {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer userVerificationId;


    public UserEntity(Integer id, String username, String email, String password, Integer userVerificationId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userVerificationId = userVerificationId;
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserEntity() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getUserVerificationId() {
        return userVerificationId;
    }

    public void setUserVerificationId(Integer userVerificationId) {
        this.userVerificationId = userVerificationId;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userVerificationId=" + userVerificationId +
                '}';
    }
}
