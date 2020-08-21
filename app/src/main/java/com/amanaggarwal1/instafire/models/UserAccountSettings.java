package com.amanaggarwal1.instafire.models;


public class UserAccountSettings {
    private String username;
    private String display_name;
    private String description;
    private String website;
    private String profile_photo;
    private long posts;
    private long followers;
    private long following;

    public UserAccountSettings(){ }

    public UserAccountSettings(String username, String display_name, String description, String website, String profile_photo, long posts, long followers, long following) {
        this.username = username;
        this.display_name = display_name;
        this.description = description;
        this.website = website;
        this.profile_photo = profile_photo;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "username='" + username + '\'' +
                ", displayName='" + display_name + '\'' +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                ", profilePhoto='" + profile_photo + '\'' +
                ", posts=" + posts +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}
