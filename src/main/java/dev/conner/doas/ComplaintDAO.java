package dev.conner.doas;

import dev.conner.entities.Complaint;

import java.util.Set;

public interface ComplaintDAO {

    Complaint createComplaint(Complaint complaint);

    boolean updateComplaintPriority(int id, Complaint.ComplaintPriority priority);

    Complaint getComplaintById(int id);

    Set<Complaint> getAllComplaints();

    Set<Complaint> getAllComplaintsByPriority(Complaint.ComplaintPriority priority);

}