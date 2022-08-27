package dev.conner.services;

import dev.conner.doas.UserDAO;
import dev.conner.entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginServiceImpl implements LoginService{
    private UserDAO userDAO;

    public LoginServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User validateUser(String username, String password) {
        User u = this.userDAO.getUserByName(username);
        System.out.println(u);
        String generatedPassword = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        if(u == null) throw new RuntimeException("No user found");
        if(!u.getPassword().equals(generatedPassword)) throw new RuntimeException("password doesnt match");

        return u;
    }
}
