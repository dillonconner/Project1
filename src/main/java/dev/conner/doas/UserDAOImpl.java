package dev.conner.doas;

import dev.conner.entities.User;
import dev.conner.utils.ConnectionUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDAOImpl implements UserDAO{
    @Override
    public User createUser(User user) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into users values (default, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUserType().toString());
            ps.setString(3, user.getPassword());

            //stole all the hashing stuff
            // credit: https://howtodoinjava.com/java/java-security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(user.getPassword().getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            String generatedPassword = sb.toString();
            System.out.println(generatedPassword);
            ps.setString(3, generatedPassword);

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int key = rs.getInt("id");
            user.setId(key);
            return user;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByName(String username) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            rs.next();
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setUserType(User.UserType.valueOf(rs.getString("user_type")));
            u.setPassword(rs.getString("user_password"));

            return u;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
