package com.ayah.docpatients.data;

import java.io.Serializable;

public class Mymedicine implements Serializable {

    private String key;
    private String name;
    private String disease;
    private String method;
    private String timesInDay;
    private String notes;
    private String docUid;
    private String uidPatient;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTimesInDay() {
        return timesInDay;
    }

    public void setTimesInDay(String timesInDay) {
        this.timesInDay = timesInDay;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUidPatient() {
        return uidPatient;
    }

    public void setUidPatient(String uidPatient) {
        this.uidPatient = uidPatient;
    }

    public String getDocUid() {
        return docUid;
    }

    public void setDocUid(String email) {
        this.docUid = docUid;
    }


    @Override
    public String toString() {
        return "Mymedicine{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", method='" + method + '\'' +
                ", timesInDay='" + timesInDay + '\'' +
                ", notes='" + notes + '\'' +
                ", docUid='" + docUid + '\'' +
                ", uidPatient='" + uidPatient + '\'' +
                '}';
    }
}





