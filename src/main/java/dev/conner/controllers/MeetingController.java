package dev.conner.controllers;

import com.google.gson.Gson;
import dev.conner.entities.Meeting;
import dev.conner.services.MeetingService;
import io.javalin.http.Handler;

import java.util.Set;

public class MeetingController {

    private Gson gson = new Gson();
    private MeetingService meetingService;

    public MeetingController(MeetingService meetingService){this.meetingService = meetingService;}

    public Handler createMeeting = (ctx) -> {
        Meeting m = this.gson.fromJson(ctx.body(), Meeting.class);

        try {
            m = this.meetingService.createMeeting(m);
            ctx.status(201);
            ctx.result(gson.toJson(m));

        }catch(RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

    public Handler getAllMeetings = (ctx) -> {
        Set<Meeting> meetings = this.meetingService.getAllMeetings();
        String json = this.gson.toJson(meetings);
        ctx.status(200);
        ctx.result(json);
    };

    public Handler updateMeeting = (ctx) -> {
        int meetingId = Integer.parseInt(ctx.pathParam("meetingId"));
        Meeting m = this.gson.fromJson(ctx.body(), Meeting.class);
        m.setId(meetingId);
        try{
            m = this.meetingService.updateMeeting(m);
            if(m == null){
                ctx.status(404);
                ctx.result("Meeting not found");
                return;
            }
            ctx.status(200);
            ctx.result(this.gson.toJson(m));

        }catch (RuntimeException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
    };

}
