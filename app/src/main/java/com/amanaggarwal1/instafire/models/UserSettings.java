package com.amanaggarwal1.instafire.models;

public class UserSettings {
    private User user;
    private UserAccountSettings userAccountSettings;

    public UserSettings(){

    }

    public UserSettings(User user, UserAccountSettings settings) {
        this.user = user;
        this.userAccountSettings = settings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAccountSettings getUserAccountSettings() {
        return userAccountSettings;
    }

    public void setUserAccountSettings(UserAccountSettings settings) {
        this.userAccountSettings = settings;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "user=" + user +
                ", userAccountSettings=" + userAccountSettings +
                '}';
    }
}
