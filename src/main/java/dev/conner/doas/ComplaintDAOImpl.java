package dev.conner.doas;

import dev.conner.entities.Complaint;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ComplaintDAOImpl implements ComplaintDAO {
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
            key = rs.getInt("meetingId");
            complaint.setMeetingId(key);

            return complaint;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateComplaintPriority(int id, Complaint.ComplaintPriority priority) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update complaint set priority = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, priority.toString());
            ps.setInt(2, id);

            int updateCount = ps.executeUpdate();
            if(updateCount > 0) return true; //check if something was updated
            else return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateComplaintMeeting(int id, int meetingId) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update complaint set meetingId = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, meetingId);
            ps.setInt(2, id);

            int updateCount = ps.executeUpdate();
            if(updateCount > 0) return true; //check if something was updated
            else return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Complaint getComplaintById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from complaint where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Complaint c = new Complaint();
            c.setId(rs.getInt("id"));
            c.setcType(Complaint.ComplaintType.valueOf(rs.getString("complaint_type")));
            c.setDescription(rs.getString("description"));
            c.setcPriority(Complaint.ComplaintPriority.valueOf(rs.getString("priority")));
            c.setDate(rs.getLong("report_date"));
            c.setMeetingId(rs.getInt("meetingId"));

            return c;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Complaint> getAllComplaints(Complaint.ComplaintType cType) {
        try(Connection conn = ConnectionUtil.createConnection()){
            PreparedStatement ps;
            if(cType != null){
                String sql = "select * from complaint where complaint_type = ? order by priority";
                ps = conn.prepareStatement(sql);
                ps.setString(1, cType.toString());
            }else{
                String sql = "select * from complaint order by priority";
                ps = conn.prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();

            Set<Complaint> complaints = new HashSet<>();
            while(rs.next()){
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setcType(Complaint.ComplaintType.valueOf(rs.getString("complaint_type")));
                c.setDescription(rs.getString("description"));
                c.setcPriority(Complaint.ComplaintPriority.valueOf(rs.getString("priority")));
                c.setDate(rs.getLong("report_date"));
                c.setMeetingId(rs.getInt("meetingId"));

                complaints.add(c);
            }
            return complaints;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Complaint> getAllComplaintsByPriority(Complaint.ComplaintPriority priority, Complaint.ComplaintType cType) {
        try(Connection conn = ConnectionUtil.createConnection()){
            PreparedStatement ps;
            if(cType != null){
                String sql = "select * from complaint where priority = ? and complaint_type = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, priority.toString());
                ps.setString(2, cType.toString());
            }else {
                String sql = "select * from complaint where priority = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, priority.toString());
            }

            ResultSet rs = ps.executeQuery();

            Set<Complaint> complaints = new HashSet<>();
            while(rs.next()){
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setcType(Complaint.ComplaintType.valueOf(rs.getString("complaint_type")));
                c.setDescription(rs.getString("description"));
                c.setcPriority(Complaint.ComplaintPriority.valueOf(rs.getString("priority")));
                c.setDate(rs.getLong("report_date"));
                c.setMeetingId(rs.getInt("meetingId"));

                complaints.add(c);
            }
            return complaints;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Complaint> getAllComplaintsByMeeting(int meetingId, Complaint.ComplaintType cType) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from complaint where meetingId = ? order by priority";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,meetingId);

            ResultSet rs = ps.executeQuery();

            Set<Complaint> complaints = new HashSet<>();
            while(rs.next()){
                Complaint c = new Complaint();
                c.setId(rs.getInt("id"));
                c.setcType(Complaint.ComplaintType.valueOf(rs.getString("complaint_type")));
                c.setDescription(rs.getString("description"));
                c.setcPriority(Complaint.ComplaintPriority.valueOf(rs.getString("priority")));
                c.setDate(rs.getLong("report_date"));
                c.setMeetingId(rs.getInt("meetingId"));

                complaints.add(c);
            }
            return complaints;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
