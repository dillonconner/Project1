package dev.conner.services;

import dev.conner.doas.MeetingDAO;
import dev.conner.entities.Meeting;

import java.util.Set;

public class MeetingServiceImpl implements MeetingService{

    private MeetingDAO meetingDAO;

    public MeetingServiceImpl(MeetingDAO meetingDAO){this.meetingDAO = meetingDAO;}

    @Override
    public Meeting createMeeting(Meeting meeting) {
        return this.meetingDAO.createMeeting(meeting);
    }

    @Override
    public Set<Meeting> getAllMeetings() {
        return this.meetingDAO.getAllMeetings();
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        return this.meetingDAO.updateMeeting(meeting);
    }
}
