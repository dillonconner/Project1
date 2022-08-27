package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Complaint;
import dev.conner.services.ComplaintService;
import io.javalin.http.Handler;

import java.util.Set;

public class ComplaintController {

    private Gson gson = new Gson();
    private ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) { this.complaintService = complaintService;}

    public Handler createComplaint = (ctx) -> {
        Complaint c = this.gson.fromJson(ctx.body(), Complaint.class);

        try{
            c = this.complaintService.createComplaint(c);
            ctx.status(201);
            ctx.result(gson.toJson(c));
        }catch (RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

    public Handler setPriorityHigh = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        boolean res = this.complaintService.updateComplaintPriority(id, Complaint.ComplaintPriority.HIGH);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler setPriorityLow = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        boolean res = this.complaintService.updateComplaintPriority(id, Complaint.ComplaintPriority.LOW);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler setPriorityIgnored = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        boolean res = this.complaintService.updateComplaintPriority(id, Complaint.ComplaintPriority.IGNORED);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler setPriorityAddressed = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        boolean res = this.complaintService.updateComplaintPriority(id, Complaint.ComplaintPriority.ADDRESSED);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler setMeeting = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        int meetingId = Integer.parseInt(ctx.pathParam("meetingId"));
        boolean res = this.complaintService.updateComplaintMeeting(id, meetingId);
        if(res){
            ctx.status(200);
            ctx.result("Expense Approved");
        }else{
            ctx.status(404);
            ctx.result("Expense with Id: " + Integer.toString(id) + " not found");
        }
    };

    public Handler getComplaintById = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("complaintId"));
        Complaint c = this.complaintService.getComplaintById(id);
        if(c == null){
            ctx.status(404);
            ctx.result("Complaint with Id: " + Integer.toString(id) + " not found");
        }else{
            String json = this.gson.toJson(c);
            ctx.status(200);
            ctx.result(json);
        }
    };

    public Handler getAllComplaints = (ctx) -> {
        String priority = ctx.queryParam("priority");
        String meeting = ctx.queryParam("meetingId");
        String typeParam = ctx.queryParam("cType");

        Complaint.ComplaintType type = null;
        if(typeParam != null) type = Complaint.ComplaintType.valueOf(typeParam);

        Set<Complaint> complaints;
        if (priority != null) {
            Complaint.ComplaintPriority p = Complaint.ComplaintPriority.valueOf(priority);
            complaints = this.complaintService.getAllComplaintsByPriority(p, type);

        } else if (meeting != null) {
            int meetingId = Integer.parseInt(meeting);
            complaints = this.complaintService.getAllComplaintsByMeeting(meetingId, type);

        } else {
            complaints = this.complaintService.getAllComplaints(type);
        }
        String json = this.gson.toJson(complaints);
        ctx.status(200);
        ctx.result(json);
    };
}
