package com.theironyard;

/**
 * Created by michaeldelli-gatti on 6/8/16.
 */
public class Person {
    String id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ipAddress;

    public Person(String id, String firstName, String lastName, String email, String country, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }
}