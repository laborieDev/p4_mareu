package com.example.p4_lamzone_mareu.model;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Model Object a Meeting
 */
public class Meeting implements Serializable {

    /** Identifier */
    private long id;

    /** Full subject */
    private String subject;

    /** MeetingRoom */
    private String meetingRoom;

    /** All Attendees */
    private List<String> allAttendees;

    /** Start at */
    private Date startAt;

    private boolean isImportant;

    public Meeting (
            long id,
            String subject,
            String meetingRoom,
            List<String> allAttendees,
            Date startAt,
            boolean isImportant
    ) {
        this.id = id;
        this.subject = subject;
        this.meetingRoom = meetingRoom;
        this.allAttendees = allAttendees;
        this.startAt = startAt;
        this.isImportant = isImportant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public List<String> getAllAttendees() {
        return allAttendees;
    }

    public void addAttendee(String attendeeEmail) {
        allAttendees.add(attendeeEmail);
    }

    public void removeAttendee(String attendeeEmail) {
        allAttendees.remove(attendeeEmail);
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(boolean isImportant){
        this.isImportant = isImportant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
