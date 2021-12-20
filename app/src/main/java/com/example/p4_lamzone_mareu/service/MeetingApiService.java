package com.example.p4_lamzone_mareu.service;

import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.model.MeetingRoom;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Delete a Meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a Meeting
     * @param meeting
     */
    List<Meeting> createMeeting(Meeting meeting);

    /**
     * Create a Meeting
     * @param position
     * @return {@link Meeting}
     */
    Meeting getMeeting(int position);

    /**
     * Save a Meeting
     * @param meeting
     */
    void saveMeeting(Meeting meeting);

    String getStringStartAt(Meeting meeting);

    MeetingRoom[] getMeetingRooms();
}
