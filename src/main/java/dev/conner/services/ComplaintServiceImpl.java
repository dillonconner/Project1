package dev.conner.services;

import dev.conner.doas.ComplaintDAO;
import dev.conner.entities.Complaint;

import java.util.Set;

public class ComplaintServiceImpl implements ComplaintService{

    private ComplaintDAO complaintDAO;

    public ComplaintServiceImpl(ComplaintDAO complaintDAO) {this.complaintDAO = complaintDAO;}

    @Override
    public Complaint createComplaint(Complaint complaint) {
        if(complaint.getDescription().length() == 0){
            throw new RuntimeException("Complaint Description must not be empty");
        }

        complaint.setcPriority(Complaint.ComplaintPriority.UNREVIEWED); //complaint start unreviewed
        complaint.setDate(System.currentTimeMillis() / 1000L);  // set time of complaint submission
        return this.complaintDAO.createComplaint(complaint);
    }

    @Override
    public boolean updateComplaintPriority(int id, Complaint.ComplaintPriority priority) {
        return this.complaintDAO.updateComplaintPriority(id, priority);
    }

    @Override
    public boolean updateComplaintMeeting(int id, int meetingId) {
        return this.complaintDAO.updateComplaintMeeting(id, meetingId);
    }

    @Override
    public Complaint getComplaintById(int id) {
        return this.complaintDAO.getComplaintById(id);
    }

    @Override
    public Set<Complaint> getAllComplaints(Complaint.ComplaintType cType) {
        return this.complaintDAO.getAllComplaints(cType);
    }

    @Override
    public Set<Complaint> getAllComplaintsByPriority(Complaint.ComplaintPriority priority, Complaint.ComplaintType cType) {
        return this.complaintDAO.getAllComplaintsByPriority(priority, cType);
    }

    @Override
    public Set<Complaint> getAllComplaintsByMeeting(int meetingId, Complaint.ComplaintType cType) {
        return this.complaintDAO.getAllComplaintsByMeeting(meetingId, cType);
    }
}
