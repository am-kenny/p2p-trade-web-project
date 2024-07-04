package com.mycompany.p2ptradewebproject.persistence.entities;

public class BankEntity {
    private Integer id;
    private String name;


    public BankEntity (Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public BankEntity (String name) {
        this.name = name;
    }


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


    @Override
    public String toString() {
        return "BankEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
