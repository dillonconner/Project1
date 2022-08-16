package dev.conner.entities;

public class user {

    enum UserType {CONSTITUENT, COUNCIL}

    private int id;
    private String username;
    private UserType userType;

    public user() {
    }

    public user(int id, String username, UserType userType) {
        this.id = id;
        this.username = username;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userType=" + userType +
                '}';
    }
}
