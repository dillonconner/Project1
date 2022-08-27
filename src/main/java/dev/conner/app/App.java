package dev.conner.app;

import com.google.gson.Gson;
import dev.conner.controllers.ComplaintController;
import dev.conner.controllers.MeetingController;
import dev.conner.controllers.UserController;
import dev.conner.doas.*;
import dev.conner.dtos.LoginCredentials;
import dev.conner.entities.User;
import dev.conner.services.*;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        ComplaintDAO cD = new ComplaintDAOImpl();
        ComplaintService cS = new ComplaintServiceImpl(cD);
        ComplaintController cC = new ComplaintController(cS);

        MeetingDAO mD = new MeetingDAOImpl();
        MeetingService mS = new MeetingServiceImpl(mD);
        MeetingController mC = new MeetingController(mS);

        UserDAO uD = new UserDAOImpl();
        UserService uS = new UserServiceImpl(uD);
        UserController uC = new UserController(uS);

        LoginService loginService = new LoginServiceImpl(uD);

        /////////////////          COMPLAINTS       ////////////////////////////
        app.post("/complaints", cC.createComplaint);
        //set priority for complaints
        app.patch("/complaints/{complaintId}/setHigh", cC.setPriorityHigh);
        app.patch("/complaints/{complaintId}/setLow", cC.setPriorityLow);
        app.patch("/complaints/{complaintId}/setIgnored", cC.setPriorityIgnored);
        app.patch("/complaints/{complaintId}/setAddressed", cC.setPriorityAddressed);
        //set meeting for complaints
        app.patch("/complaints/{complaintId}/setMeeting/{meetingId}", cC.setMeeting);

        app.get("/complaints/{complaintId}", cC.getComplaintById);
        //get all complaints sorted or by query of priority/meetingId
        app.get("/complaints", cC.getAllComplaints);

        /////////////////          MEETINGS       ////////////////////////////
        app.post("/meetings", mC.createMeeting);
        app.get("/meetings", mC.getAllMeetings);
        app.put("/meetings/{meetingId}", mC.updateMeeting);

        /////////////////          USERS       ////////////////////////////
        app.post("/users", uC.createUser);
        app.post("/login", ctx ->{
            String body = ctx.body();
            Gson gson = new Gson();
            LoginCredentials credentials = gson.fromJson(body, LoginCredentials.class);

            try{
                User user = loginService.validateUser(credentials.getUsername(), credentials.getPassword());
                String employeeJSON = gson.toJson(user);
                ctx.result(employeeJSON);
            }catch(RuntimeException e){
                ctx.status(400);
                ctx.result(e.getMessage());
            }
        });
        app.start();
    }
}
