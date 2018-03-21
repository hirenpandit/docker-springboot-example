package com.example.springdocker.entity;

import javax.persistence.*;

@Table(name="user")
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
