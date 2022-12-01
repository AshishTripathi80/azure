package org.nagarro.product.catalouge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long pinCode;
    private String code;

    public Availability() {
    }

    public Availability(int id, long pinCode, String code) {
        this.id = id;
        this.pinCode = pinCode;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "id=" + id +
                ", pinCode=" + pinCode +
                ", code='" + code + '\'' +
                '}';
    }
}

