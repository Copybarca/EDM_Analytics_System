package org.edm.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensors")
@Component("sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int  id;
    @Column(name = "machines_id")
    @Digits(integer = 10, fraction = 0)
    @DecimalMin(value = "0", message="Should be greater then 0")
    private int machines_id;
    @Column(name = "title")
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    private String title;

    public Sensor() {

    }

    public Sensor(int machines_id, String title) {
        this.machines_id = machines_id;
        this.title = title;
    }

    public void setMachines_id(int machines_id) {
        this.machines_id = machines_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getMachines_id() {
        return machines_id;
    }

    public String getTitle() {
        return title;
    }
}
