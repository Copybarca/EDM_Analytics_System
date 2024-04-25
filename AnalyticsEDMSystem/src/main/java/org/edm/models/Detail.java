package org.edm.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.sql.Date;


@Entity
@Component("detail")
@Scope("prototype")
@Table(name = "products")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "machines_id")
    private int machines_id;
    @Column(name = "orders_id")
    private int orders_id;
    @Column(name = "date")
    private Date date;
    @Column(name = "quality")
    private int quality;

    public Detail() {
    }

    public Detail(int machines_id, int orders_id, Date date, int quality) {
        this.machines_id = machines_id;
        this.orders_id = orders_id;
        this.date = date;
        this.quality = quality;
    }

    public void setMachines_id(int machines_id) {
        this.machines_id = machines_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public int getMachines_id() {
        return machines_id;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public Date getDate() {
        return date;
    }

    public int getQuality() {
        return quality;
    }
}
