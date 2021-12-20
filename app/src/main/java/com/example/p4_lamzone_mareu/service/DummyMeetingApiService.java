package com.example.p4_lamzone_mareu.service;

import android.os.Build;

import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.model.MeetingRoom;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();
    private List<MeetingRoom> meetingsRooms = DummyMeetingGenerator.DUMMY_MEETINGS_ROOMS;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        Collections.sort(meetings);

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
    public List<Meeting> createMeeting(Meeting meeting) {
        meetings.add(meeting);
        return meetings;
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

    public String getStringStartAt(Meeting meeting) {
        Date meetingStartAtDate = meeting.getStartAt();
        DecimalFormat df = new DecimalFormat("00");

        return meetingStartAtDate.getHours() + ":" + df.format(meetingStartAtDate.getMinutes());
    }

    public MeetingRoom[] getMeetingRooms() {
        MeetingRoom[] meetingRoomsArray = new MeetingRoom[meetingsRooms.size()];
        int i = 0;

        for (MeetingRoom meetingRoom : meetingsRooms) {
            meetingRoomsArray[i] = meetingRoom;
            i++;
        }

        return meetingRoomsArray;
    }
}
