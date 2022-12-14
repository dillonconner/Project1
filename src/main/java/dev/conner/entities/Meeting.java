package dev.conner.entities;

import java.util.Objects;

public class Meeting {

    private int id;
    private long date;
    private String address;

    public Meeting() {}

    public Meeting(int id, long date, String address) {
        this.id = id;
        this.date = date;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "meeting{" +
                "id=" + id +
                ", date=" + date +
                ", address='" + address + '\'' +
                '}';
    }
}
