package dev.conner.app;

import dev.conner.controllers.ComplaintController;
import dev.conner.doas.ComplaintDOA;
import dev.conner.doas.ComplaintDoaImpl;
import dev.conner.services.ComplaintService;
import dev.conner.services.ComplaintServiceImpl;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config->{
            config.enableDevLogging();
            config.enableCorsForAllOrigins();
        });

        ComplaintDOA cD = new ComplaintDoaImpl();
        ComplaintService cS = new ComplaintServiceImpl(cD);
        ComplaintController cC = new ComplaintController(cS);

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



        app.start();
    }
}
