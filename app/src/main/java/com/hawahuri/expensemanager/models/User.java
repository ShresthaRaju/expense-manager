package com.hawahuri.expensemanager.models;

public class User {

    private String _id, firstName, familyName, email, password;

    public User(String firstName, String familyName, String email, String password) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
