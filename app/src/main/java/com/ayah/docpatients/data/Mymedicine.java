package com.ayah.docpatients.data;

public class Mymedicine {

    private String key;
    private String name;
    private String disease;
    private String method;
    private String timesInDay;
    private String notes;
    private String owner;



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

    @Override
    public String toString() {
        return "Mymedicine{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", method='" + method + '\'' +
                ", timesInDay=" + timesInDay +
                ", notes='" + notes + '\'' +
                '}';
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String email) {
        this.owner = owner;


    }
}





