package org.analytics.model;

public class ErosionMachin {
    private int id;
    private String model;
    private String mark;
    private String type;
    private String serialNumber;

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ErosionMachin() {
    }
    public ErosionMachin(int id, String type,String model, String mark) {
        this.id = id;
        this.type = type;
        this.mark = mark;
        this.model = model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    public String getType() {
        return type;
    }
}
