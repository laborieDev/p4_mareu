package com.example.p4_lamzone_mareu.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model Object a Meeting Room
 */
public class MeetingRoom implements Serializable {

    /** Identifier */
    private long id;

    /** Hex Color */
    private String color;

    /** Metting Room Name */
    private String name;

    public MeetingRoom(
            long id,
            String color,
            String name
    ) {
        this.id = id;
        this.color = color;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingRoom meeting = (MeetingRoom) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
