package org.edm.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Component("employee")
@Scope("prototype")
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="name")
    private String name;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="surname")
    private  String surname;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="seniority")
    private String seniority;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="salary")
    private String salary;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")

    @Column(name="post")
    private String post;

    @Digits(integer = 10, fraction = 0)
    @DecimalMax(value = "120", message="Should be younger than 120's y.o")
    @DecimalMin(value = "16", message="Should be older than 16's y.o")
    @Column(name="age")
    private int age;

    public Employee() {}

    public Employee(String name, String surname, String seniority, String salary, String post, int age) {
        this.name = name;
        this.surname = surname;
        this.seniority = seniority;
        this.salary = salary;
        this.post = post;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSeniority() {
        return seniority;
    }

    public String getSalary() {
        return salary;
    }

    public String getPost() {
        return post;
    }

    public int getAge() {
        return age;
    }
}
