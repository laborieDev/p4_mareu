package com.example.p4_lamzone_mareu.service;

import com.example.p4_lamzone_mareu.model.Meeting;

import java.util.List;

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
    void createMeeting(Meeting meeting);

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

}
