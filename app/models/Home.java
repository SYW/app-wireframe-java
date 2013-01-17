package models;

import shopyourway.domain.user.User;
import shopyourway.platform.client.configurations.ApplicationCanvases;

import java.util.List;

public class Home {
    private boolean displayProperConfigurationMessage;
    private User user;
    private String Id;
    private List<User> followers;

    public Home(User user, List<User> followers) {

        this.user = user;
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public String getId() {
        return user.getName();
    }

    public String getProfileUrl() {
        return user.getProfileUrl();
    }

    public String getImageUrl() {
        return user.getImageUrl();
    }

    public String getName() {
        return user.getName();
    }

    public boolean isDisplayProperConfigurationMessage() {
        return displayProperConfigurationMessage;
    }

    public void setDisplayProperConfigurationMessage(boolean displayProperConfigurationMessage) {
        this.displayProperConfigurationMessage = displayProperConfigurationMessage;
    }

    public String getCanvasPageUrl() {
        return ApplicationCanvases.getCanvasPageUrl();
    }
}
