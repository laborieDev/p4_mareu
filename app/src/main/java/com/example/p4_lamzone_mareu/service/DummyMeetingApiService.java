package com.example.p4_lamzone_mareu.service;

import android.os.Build;

import com.example.p4_lamzone_mareu.di.DI;
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
    @Override
    public List<Meeting> createMeeting(Meeting meeting) {
        meetings.add(meeting);
        return meetings;
    }*/

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

    public MeetingRoom[] getMeetingRooms() {
        MeetingRoom[] meetingRoomsArray = new MeetingRoom[meetingsRooms.size()];
        int i = 0;

        for (MeetingRoom meetingRoom : meetingsRooms) {
            meetingRoomsArray[i] = meetingRoom;
            i++;
        }

        return meetingRoomsArray;
    }

    @Override
    public List<Meeting> filterMeetings(CharSequence charSequence, List<Meeting> meetings)
    {
        List<Meeting> meetingsFiltered = new ArrayList<>();

        String charString = charSequence.toString();
        if (charString.isEmpty()) {
            meetingsFiltered = meetings;
        } else {
            for (Meeting meeting : meetings) {
                String hourStartAt = meeting.getStringStartAt();

                if (
                    meeting.getSubject().toLowerCase().contains(charString.toLowerCase()) ||
                    meeting.getMeetingRoom().getName().toLowerCase().contains(charString.toLowerCase()) ||
                    hourStartAt.contains(charString)
                ) {
                    meetingsFiltered.add(meeting);
                }
            }
        }

        return meetingsFiltered;
    }
}
