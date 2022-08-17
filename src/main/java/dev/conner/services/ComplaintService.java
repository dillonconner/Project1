package dev.conner.services;

import dev.conner.entities.Complaint;

import java.util.Set;

public interface ComplaintService {

    Complaint createComplaint(Complaint complaint);

    Complaint updateComplaint(Complaint complaint);

    Set<Complaint> getAllComplaints();
}
