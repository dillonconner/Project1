package dev.conner.services;

import dev.conner.doas.ComplaintDOA;
import dev.conner.entities.Complaint;

import java.util.Set;

public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDOA complaintDOA;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        if(complaint.getDescription().length() == 0){
            throw new RuntimeException("Complaint Description must not be empty");
        }

        complaint.setcPriority(Complaint.ComplaintPriority.UNREVIEWED); //complaint start unreviewed
        complaint.setDate(System.currentTimeMillis() / 1000L);  // set time of complaint submission
        //complaint.setMeetingId(-1); // dont need this; defaults in sql
        return this.complaintDOA.createComplaint(complaint);
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return null;
    }

    @Override
    public Set<Complaint> getAllComplaints() {
        return null;
    }
}
