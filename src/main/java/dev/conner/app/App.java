package dev.conner.app;

import dev.conner.controllers.ComplaintController;
import dev.conner.controllers.MeetingController;
import dev.conner.doas.ComplaintDOA;
import dev.conner.doas.ComplaintDOAImpl;
import dev.conner.doas.MeetingDOA;
import dev.conner.doas.MeetingDOAImpl;
import dev.conner.services.ComplaintService;
import dev.conner.services.ComplaintServiceImpl;
import dev.conner.services.MeetingService;
import dev.conner.services.MeetingServiceImpl;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        ComplaintDOA cD = new ComplaintDOAImpl();
        ComplaintService cS = new ComplaintServiceImpl(cD);
        ComplaintController cC = new ComplaintController(cS);

        MeetingDOA mD = new MeetingDOAImpl();
        MeetingService mS = new MeetingServiceImpl(mD);
        MeetingController mC = new MeetingController(mS);

        /////////////////          COMPLAINTS       ////////////////////////////
        app.post("/complaints", cC.createComplaint);
        //set priority for complaints
        app.patch("/complaints/{complaintId}/setHigh", cC.setPriorityHigh);
        app.patch("/complaints/{complaintId}/setLow", cC.setPriorityLow);
        app.patch("/complaints/{complaintId}/setIgnored", cC.setPriorityIgnored);
        app.patch("/complaints/{complaintId}/setAddressed", cC.setPriorityAddressed);

        app.get("/complaints/{complaintId}", cC.getComplaintById);
        //get all complaints sorted
        app.get("/complaints", cC.getAllComplaints);
        //get all complaints by query
        app.get("/complaints/{priority}", cC.getAllComplaintsByPriority);

        /////////////////          MEETINGS       ////////////////////////////
        app.post("/meetings", mC.createMeeting);
        app.get("/meetings", mC.getAllMeetings);
        app.put("/meetings/{meetingId}", mC.updateMeeting);

        /////////////////          USERS       ////////////////////////////



        app.start();
    }
}
