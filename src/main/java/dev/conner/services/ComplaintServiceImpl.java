package dev.conner.services;

import dev.conner.doas.ComplaintDOA;
import dev.conner.entities.Complaint;

import java.util.Set;

public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDOA complaintDOA;

    public ComplaintServiceImpl(ComplaintDOA complaintDOA) {this.complaintDOA = complaintDOA;}

    @Override
    public Complaint createComplaint(Complaint complaint) {
        if(complaint.getDescription().length() == 0){
            throw new RuntimeException("Complaint Description must not be empty");
        }

        complaint.setcPriority(Complaint.ComplaintPriority.UNREVIEWED); //complaint start unreviewed
        complaint.setDate(System.currentTimeMillis() / 1000L);  // set time of complaint submission
        return this.complaintDOA.createComplaint(complaint);
    }

    @Override
    public boolean updateComplaintPriority(int id, Complaint.ComplaintPriority priority) {
        return this.complaintDOA.updateComplaintPriority(id, priority);
    }

    @Override
    public Set<Complaint> getAllComplaints() {
        return null;
    }
}
