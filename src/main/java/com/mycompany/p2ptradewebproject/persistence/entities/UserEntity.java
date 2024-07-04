package com.mycompany.p2ptradewebproject.persistence.entities;

public class UserEntity {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private UserVerificationEntity userVerificationEntity;


    public UserEntity(Integer id, String username, String email, String password, UserVerificationEntity userVerificationEntity) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userVerificationEntity = userVerificationEntity;
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    public UserVerificationEntity getUserVerificationEntity() {
        return userVerificationEntity;
    }

    public void setUserVerificationEntity(UserVerificationEntity userVerificationEntity) {
        this.userVerificationEntity = userVerificationEntity;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userVerificationEntity=" + userVerificationEntity +
                '}';
    }
}
