package com.mycompany.p2ptradewebproject.persistence.entities;


public class UserVerificationEntity {
    private Integer id;
    private String name;
    private String surname;
    private String passportNumber;
    private String passportPhotoReference;
    private Boolean isBanned;


    public UserVerificationEntity() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportPhotoReference() {
        return passportPhotoReference;
    }

    public void setPassportPhotoReference(String passportPhotoReference) {
        this.passportPhotoReference = passportPhotoReference;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }


    @Override
    public String toString() {
        return "UserVerificationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportPhotoReference='" + passportPhotoReference + '\'' +
                ", isBanned=" + isBanned +
                '}';
    }
}
