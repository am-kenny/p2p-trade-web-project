package com.mycompany.p2ptradewebproject.entities;

public class CurrencyEntity {
    private Integer id;
    private String name;
    private String code;

    public CurrencyEntity (Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public CurrencyEntity (String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
