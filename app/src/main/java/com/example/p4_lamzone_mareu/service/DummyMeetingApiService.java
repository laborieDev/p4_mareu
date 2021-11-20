package com.example.p4_lamzone_mareu.service;

import com.example.p4_lamzone_mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public Meeting getMeeting(int position)
    {
        return meetings.get(position);
    }

    @Override
    public void saveMeeting(Meeting meeting) {
        int position = meetings.indexOf(meeting);
        meetings.set(position, meeting);
    }
}
