package dev.conner.services;

import dev.conner.doas.MeetingDOA;
import dev.conner.entities.Meeting;

import java.util.Set;

public class MeetingServiceImpl implements MeetingService{

    private MeetingDOA meetingDOA;

    public MeetingServiceImpl(MeetingDOA meetingDOA){this.meetingDOA = meetingDOA;}

    @Override
    public Meeting createMeeting(Meeting meeting) {
        return this.meetingDOA.createMeeting(meeting);
    }

    @Override
    public Set<Meeting> getAllMeetings() {
        return this.meetingDOA.getAllMeetings();
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        return this.meetingDOA.updateMeeting(meeting);
    }
}
