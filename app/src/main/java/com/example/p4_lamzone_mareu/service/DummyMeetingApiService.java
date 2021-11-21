package com.example.p4_lamzone_mareu.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.p4_lamzone_mareu.model.Meeting;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Meeting> getMeetings() {
        return getMeetingsOrderDate(Meeting::getStartAt);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Meeting> getMeetingsOrderDate(Function<Meeting, Date> orderDate) {
        return meetings.stream()
                .sorted(Comparator.comparing(orderDate))
                .collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Meeting> getMeetingsOrderString(Function<Meeting, String> orderString) {
        return meetings.stream()
                .sorted(Comparator.comparing(orderString))
                .collect(Collectors.toList());
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

    public String getStringStartAt(Meeting meeting) {
        Date meetingStartAtDate = meeting.getStartAt();
        DecimalFormat df = new DecimalFormat("00");

        return meetingStartAtDate.getHours() + ":" + df.format(meetingStartAtDate.getMinutes());
    }
}
