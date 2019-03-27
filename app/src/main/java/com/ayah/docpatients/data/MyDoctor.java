package com.ayah.docpatients.data;

public class MyDoctor {

    private String key;
    private String email;
    private String first;
    private String last;
    private String phone;
    private String id;
    private String owner;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getEmail(String email1) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst(String first1) {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast(String last1) {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone(String phone1) {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId(String id1) {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "DoctorActivity{" +
                "key='" + key + '\'' +
                ", email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setOwner(String email) {

        this.owner = owner;

    }
}

