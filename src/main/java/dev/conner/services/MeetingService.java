package dev.conner.services;

import dev.conner.entities.Meeting;

import java.util.Set;

public interface MeetingService {

    Meeting createMeeting(Meeting meeting);

    Set<Meeting> getAllMeetings();

    Meeting updateMeeting(Meeting meeting);
}
