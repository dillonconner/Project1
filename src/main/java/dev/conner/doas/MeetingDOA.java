package dev.conner.doas;

import dev.conner.entities.Meeting;

import java.util.Set;

public interface MeetingDOA {

    Meeting createMeeting(Meeting meeting);

    Set<Meeting> getAllMeetings();

    Meeting updateMeeting(Meeting meeting);

}
