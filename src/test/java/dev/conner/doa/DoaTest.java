package dev.conner.doa;

import dev.conner.doas.ComplaintDOA;
import dev.conner.doas.ComplaintDoaImpl;
import dev.conner.entities.Complaint;
import dev.conner.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoaTest {

    static ComplaintDOA complaintDOA = new ComplaintDoaImpl();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            Statement s = conn.createStatement();

            String first = "create table meeting(\n" +
                            "id serial primary key,\n" +
                            "meet_date int not NULL,\n" +
                            "address varchar(20)\n" +
                            ");\n";
            String second = "insert into meeting values(-1, 0, 'NOT REAL MEETING');\n";

            String third = "create table complaint(\n" +
                    "id serial primary key,\n" +
                    "complaint_type varchar(100) not NULL,\n" +
                    "description varchar(100) not NULL,\n" +
                    "priority varchar(15) not NULL,  -- unreviewed, high, low, ignored\n" +
                    "report_date int,\n" +
                    "meetingId int references meeting(id) default -1\n" +
                    ");\n";



            s.execute(first);
            s.execute(second);
            s.execute(third);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_complaint_test(){
        Complaint c = new Complaint(0, Complaint.ComplaintType.A, "test", Complaint.ComplaintPriority.UNREVIEWED, 5, 0);
        Complaint savedC = complaintDOA.createComplaint(c);
        Assertions.assertNotEquals(0, savedC.getId());
        Assertions.assertNotEquals(0, savedC.getMeetingId());
    }



    @AfterAll
    static void teardown() {
        //System.out.println(complaintDOA.getAllComplaints());

        try (Connection connection = ConnectionUtil.createConnection()) {
            Statement statement = connection.createStatement();

            String sql = "drop table complaint";
            statement.execute(sql);

            String moreSQL = "drop table meeting";
            statement.execute(moreSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
