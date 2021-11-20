package com.example.p4_lamzone_mareu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

    public Meeting (
            long id,
            String subject,
            String meetingRoom,
            List<String> allAttendees,
            Date startAt
    ) {
        this.id = id;
        this.subject = subject;
        this.meetingRoom = meetingRoom;
        this.allAttendees = allAttendees;
        this.startAt = startAt;
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
