package com.example.city_sight;

public class User {
    private String name;
    private String surname;
    public User(){}

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
