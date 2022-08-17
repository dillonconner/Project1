package dev.conner.doas;

import dev.conner.entities.Complaint;

import java.util.Set;

public interface ComplaintDOA {

    Complaint createComplaint(Complaint complaint);

    Complaint updateComplaint(Complaint complaint);

    Set<Complaint> getAllComplaints();

}
