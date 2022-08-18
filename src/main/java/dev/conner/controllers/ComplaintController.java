package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Complaint;
import dev.conner.services.ComplaintService;
import io.javalin.http.Handler;

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


}
