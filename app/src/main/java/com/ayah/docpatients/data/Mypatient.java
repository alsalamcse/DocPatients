package com.ayah.docpatients.data;

import java.io.Serializable;

public class Mypatient implements Serializable {

    private String key;
    private String emaill;
    private String phone;
    private String first;
    private String last;
    private String id;
    private String owner;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mypatient{" +
                "key='" + key + '\'' +
                ", emaill='" + emaill + '\'' +
                ", phone='" + phone + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String email) {
        this.owner = owner;

    }
}



