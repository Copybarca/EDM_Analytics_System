package org.edm.models;

import jakarta.persistence.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
@Component("client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name ="name")
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    private String name;

    @Column(name = "surname")
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    private String surname;
    @Column(name = "email")
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    private String email;
    @Column(name = "telephone")
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    private String telephone;

    public Client() {

    }

    public Client(String name, String surname, String email, String telephone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

}
