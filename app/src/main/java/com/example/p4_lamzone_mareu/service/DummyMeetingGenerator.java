package com.example.p4_lamzone_mareu.service;

import com.example.p4_lamzone_mareu.model.Meeting;
import com.example.p4_lamzone_mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<MeetingRoom> DUMMY_MEETINGS_ROOMS = Arrays.asList(
            new MeetingRoom(1, "#edd9d0", "Peach"),
            new MeetingRoom(2, "#f1948a", "Mario"),
            new MeetingRoom(3, "#aeceb8", "Luigi")
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(
                    1, "Réunion A",
                    DUMMY_MEETINGS_ROOMS.get(0),
                    Arrays.asList("anthony@email.com", "timothe@email.com", "anna@email.com"),
                    getDate(14, 0)
            ),

            new Meeting(
                    3, "Réunion C",
                    DUMMY_MEETINGS_ROOMS.get(2),
                    Arrays.asList("anna@email.com", "anthony@email.com"),
                    getDate(23, 0)
            ),
            new Meeting(
                    2, "Réunion B",
                    DUMMY_MEETINGS_ROOMS.get(1),
                    Arrays.asList("timothe@email.com", "anna@email.com"),
                    getDate(16, 0)
            )
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static Date getDate(int hours, int min) {
        return new Date (2022, 1, 1, hours, min);
    }
}
