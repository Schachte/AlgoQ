package com.algoq.algoq.models;

import javax.persistence.*;

/**
 * This is the model for those who decide to subscribe to daily algorithm problems
 */

@Entity
public class Subscriber {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String emailAddress;
    private String name;
    private String phone;
    private String ipAddress;

    public Subscriber() {}

    public Subscriber(String emailAddress, String name, String ipAddress) {
        super();
        this.emailAddress = emailAddress;
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
