package dev.conner.doas;

import dev.conner.entities.Complaint;

import java.util.Set;

public interface ComplaintDOA {

    Complaint createComplaint(Complaint complaint);

    boolean updateComplaintPriority(int id, Complaint.ComplaintPriority priority);

    Set<Complaint> getAllComplaints();

}
