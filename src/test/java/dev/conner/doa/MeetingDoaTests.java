package dev.conner.doa;

import dev.conner.doas.MeetingDOA;
import dev.conner.doas.MeetingDOAImpl;
import dev.conner.entities.Complaint;
import dev.conner.entities.Meeting;
import dev.conner.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingDoaTests {

    static MeetingDOA meetingDOA = new MeetingDOAImpl();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            Statement s = conn.createStatement();

            String first = "create table meeting(\n" +
                    "id serial primary key,\n" +
                    "meet_date int not NULL,\n" +
                    "address varchar(20)\n" +
                    ");\n";

            s.execute(first);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_meeting_test(){
        Meeting m = new Meeting(0, 1661120387, "meeting Place");
        Meeting savedM = meetingDOA.createMeeting(m);
        Assertions.assertNotEquals(0, savedM.getId());
    }

    @Test
    @Order(2)
    void update_meeting_test(){
        Meeting m = new Meeting(1, 1661120387, "different Place");
        Meeting savedM = meetingDOA.updateMeeting(m);
        Assertions.assertEquals("different Place", savedM.getAddress());
    }

    @Test
    @Order(3)
    void get_all_meetings_test(){
        Meeting m = new Meeting(0, 1661120387, "A Place");
        m = meetingDOA.createMeeting(m);
        m = meetingDOA.createMeeting(m);
        Set<Meeting> meetingSet = meetingDOA.getAllMeetings();
        Assertions.assertEquals(3,meetingSet.size());
    }



    @AfterAll
    static void teardown() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            Statement statement = connection.createStatement();

            String sql = "drop table meeting";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
