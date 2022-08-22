package dev.conner.doas;

import dev.conner.entities.Meeting;
import dev.conner.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class MeetingDAOImpl implements MeetingDAO {
    @Override
    public Meeting createMeeting(Meeting meeting) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into meeting values (default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, meeting.getDate());
            ps.setString(2, meeting.getAddress());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("id");
            meeting.setId(key);
            return meeting;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Meeting> getAllMeetings() {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from meeting order by meet_date";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            Set<Meeting> meetings = new HashSet<>();
            while(rs.next()){
                Meeting m = new Meeting();
                m.setId(rs.getInt("id"));
                m.setDate(rs.getLong("meet_date"));
                m.setAddress(rs.getString("address"));
                meetings.add(m);
            }
            return meetings;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Meeting updateMeeting(Meeting meeting) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update meeting set meet_date = ?, address = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, meeting.getDate());
            ps.setString(2, meeting.getAddress());
            ps.setInt(3, meeting.getId());

            int updateCount = ps.executeUpdate();
            if(updateCount > 0) return meeting;
            else return null;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
