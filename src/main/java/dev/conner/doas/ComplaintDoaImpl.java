package dev.conner.doas;

import dev.conner.entities.Complaint;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.util.Set;

public class ComplaintDoaImpl implements ComplaintDOA{
    @Override
    public Complaint createComplaint(Complaint complaint) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into complaint values (default, ?, ?, ?, ?, default)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, complaint.getcType().toString());
            ps.setString(2, complaint.getDescription());
            ps.setString(3,complaint.getcPriority().toString());
            ps.setLong(4,complaint.getDate());


            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int key = rs.getInt("id");
            complaint.setId(key);
            //key = rs.getInt("meetingId");
            //complaint.setMeetingId(key);

            return complaint;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        return null;
    }

    @Override
    public Set<Complaint> getAllComplaints() {
        return null;
    }
}
