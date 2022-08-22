package dev.conner.doa;

import dev.conner.doas.ComplaintDAO;
import dev.conner.doas.ComplaintDAOImpl;
import dev.conner.entities.Complaint;
import dev.conner.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintDoaTests {

    static ComplaintDAO complaintDAO = new ComplaintDAOImpl();

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
        Complaint savedC = complaintDAO.createComplaint(c);
        Assertions.assertNotEquals(0, savedC.getId());
        Assertions.assertNotEquals(0, savedC.getMeetingId());
    }

    @Test
    @Order(2)
    void get_complaint_test(){
        Complaint savedC = complaintDAO.getComplaintById(1);
        Assertions.assertEquals(1, savedC.getId());
        Assertions.assertEquals("test", savedC.getDescription());
    }

    @Test
    @Order(3)
    void update_complaint_priority_test(){
        boolean ret = complaintDAO.updateComplaintPriority(1, Complaint.ComplaintPriority.HIGH);
        Complaint c = complaintDAO.getComplaintById(1);
        Assertions.assertEquals(true, ret);
        Assertions.assertEquals(Complaint.ComplaintPriority.HIGH, c.getcPriority());
    }

    @Test
    @Order(4)
    void get_all_complaints_test(){
        Complaint c = new Complaint(0, Complaint.ComplaintType.A, "test", Complaint.ComplaintPriority.HIGH, 5, 0);
        Complaint c1 = new Complaint(0, Complaint.ComplaintType.A, "test", Complaint.ComplaintPriority.LOW, 5, 0);
        Complaint c2 = new Complaint(0, Complaint.ComplaintType.A, "test", Complaint.ComplaintPriority.LOW, 5, 0);
        Complaint c3 = new Complaint(0, Complaint.ComplaintType.A, "test", Complaint.ComplaintPriority.UNREVIEWED, 5, 0);
        complaintDAO.createComplaint(c);
        complaintDAO.createComplaint(c1);
        complaintDAO.createComplaint(c2);
        complaintDAO.createComplaint(c3);

        Set<Complaint> complaintSet = complaintDAO.getAllComplaints();

        Assertions.assertEquals(5, complaintSet.size());
    }

    @Test
    @Order(5)
    void get_all_by_priority_test(){
        Set<Complaint> complaintSet = complaintDAO.getAllComplaintsByPriority(Complaint.ComplaintPriority.HIGH);
        Assertions.assertEquals(2,complaintSet.size());
        complaintSet = complaintDAO.getAllComplaintsByPriority(Complaint.ComplaintPriority.LOW);
        Assertions.assertEquals(2,complaintSet.size());
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
