package com.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Person {
 
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO)
    private Long id;
    private volatile String name;
    private String surname;
 /*@Version
 private  Integer version;
     */
    public Person() {}
 
    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
     
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", surname=" + surname + "]";
    }
}