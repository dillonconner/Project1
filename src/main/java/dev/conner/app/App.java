package dev.conner.app;

import dev.conner.controllers.ComplaintController;
import dev.conner.doas.ComplaintDOA;
import dev.conner.doas.ComplaintDoaImpl;
import dev.conner.services.ComplaintService;
import dev.conner.services.ComplaintServiceImpl;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        ComplaintDOA cD = new ComplaintDoaImpl();
        ComplaintService cS = new ComplaintServiceImpl(cD);
        ComplaintController cC = new ComplaintController(cS);

        app.post("/complaints", cC.createComplaint);

        app.start();
    }
}
