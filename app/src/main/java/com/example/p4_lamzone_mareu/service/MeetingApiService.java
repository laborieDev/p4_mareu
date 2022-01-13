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
    void createMeeting(Meeting meeting);

    List<Meeting> filterMeetings(CharSequence charSequence, List<Meeting> meetings);

    /**
     * Create a Meeting
     * @param position
     * @return {@link Meeting}
     */
    Meeting getMeeting(int position);

    MeetingRoom[] getMeetingRooms();
}
