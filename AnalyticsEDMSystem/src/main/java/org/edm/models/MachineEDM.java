package org.edm.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component("machineedm")
@Table(name = "machine")
public class MachineEDM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="machine_type")
    private String type;
    @Column(name="model")
    private String model;
    @Column(name="mark")
    private String mark;

    public  MachineEDM(){
    }
    public MachineEDM(String type, String model, String mark){
        this.type=type;
        this.model=model;
        this.mark=mark;
    }

    @Override
    public String toString() {
        return "MachineEDM{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
