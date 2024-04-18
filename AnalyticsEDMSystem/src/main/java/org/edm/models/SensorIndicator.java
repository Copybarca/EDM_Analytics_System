package org.edm.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Date;

@Entity
@Component("sensorIndicator")
@Table(name = "sensor_indicators")
public class SensorIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sensors_id")
    private int sensors_id;
    @Column(name = "data")
    private Date data;
    @Column(name = "time")
    private Time time;
    @Column(name = "value")
    private int value;

    public SensorIndicator() {
    }

    public SensorIndicator(int sensors_id, Date data, Time time, int value) {
        this.sensors_id = sensors_id;
        this.data = data;
        this.time = time;
        this.value = value;
    }

    public void setSensors_id(int sensor_id) {
        this.sensors_id = sensor_id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getSensors_id() {
        return sensors_id;
    }

    public Date getData() {
        return data;
    }

    public Time getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }
}
