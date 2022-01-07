package com.example.p4_lamzone_mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.p4_lamzone_mareu.di.DI;
import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.service.DummyMeetingGenerator;
import com.example.p4_lamzone_mareu.service.MeetingApiService;
import com.example.p4_lamzone_mareu.ui.list.MyMeetingRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

public class UnitTest {
    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting newMeeting = new Meeting(
                999,
                "Subject",
                DummyMeetingGenerator.DUMMY_MEETINGS_ROOMS.get(1),
                Arrays.asList("timothe@email.com", "anna@email.com"),
                DummyMeetingGenerator.getDate(16, 0)
        );

        service.createMeeting(newMeeting);
        List<Meeting> allMeetings = service.getMeetings();

        assertEquals(newMeeting, allMeetings.get(allMeetings.size() - 1));
    }

    @Test
    public void searchMeeting() {
        List<Meeting> meetings = DummyMeetingGenerator.DUMMY_MEETINGS;

        Meeting wantedMeeting = meetings.get(0);
        List<Meeting> filterMeetings = service.filterMeetings(
                wantedMeeting.getSubject(),
                meetings
        );

        assertTrue(filterMeetings.contains(wantedMeeting));
    }
}