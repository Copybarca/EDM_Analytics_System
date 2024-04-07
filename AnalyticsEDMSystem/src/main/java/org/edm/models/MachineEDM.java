package org.edm.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.validation.constraints.Size;

@Entity
@Component("machineedm")
@Scope("prototype")
@Table(name = "machines")
public class MachineEDM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="title")
    private String title;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="firm")
    private String firm;
    @Size(min = 1,max = 45, message = "In range of 1 to 45 characters")
    @Column(name="type")
    private String type;
    @Size(min = 1,max = 30, message = "In range of 1 to 30 characters")
    @Column(name="serial_number")
    private String serialNumber;

    public  MachineEDM(){}

    public MachineEDM(String title, String firm, String type, String serialNumber) {
        this.title = title;
        this.firm = firm;
        this.type = type;
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "MachineEDM{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firm='" + firm + '\'' +
                ", type='" + type + '\'' +
                ", serial_number=" + serialNumber +
                '}';
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFirm() {
        return firm;
    }

    public String getType() {
        return type;
    }
}
