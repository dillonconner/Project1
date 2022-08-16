package dev.conner.entities;

import java.util.Objects;

public class complaint {

    enum ComplaintType {A, B, C, D} //add actual things
    enum ComplaintPriority {UNREVIEWED, HIGH, LOW, IGNORED}

    private int id;
    private ComplaintType cType;
    private String description;
    private ComplaintPriority cPriority;
    private long date;
    private int meetingId;

    public complaint() {}
    public complaint(int id, ComplaintType cType, String description, ComplaintPriority cPriority, long date, int meetingId) {
        this.id = id;
        this.cType = cType;
        this.description = description;
        this.cPriority = cPriority;
        this.date = date;
        this.meetingId = meetingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ComplaintType getcType() {
        return cType;
    }

    public void setcType(ComplaintType cType) {
        this.cType = cType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComplaintPriority getcPriority() {
        return cPriority;
    }

    public void setcPriority(ComplaintPriority cPriority) {
        this.cPriority = cPriority;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        complaint complaint = (complaint) o;
        return id == complaint.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "complaint{" +
                "id=" + id +
                ", cType=" + cType +
                ", description='" + description + '\'' +
                ", cPriority=" + cPriority +
                ", date=" + date +
                ", meetingId=" + meetingId +
                '}';
    }
}
